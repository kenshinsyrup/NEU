package assignment6ConcurrentSolution;

import assignment6ConcurrentSolution.consumer.Consumer;
import assignment6ConcurrentSolution.producer.AssessmentProducer;
import assignment6ConcurrentSolution.producer.CourseProducer;
import assignment6ConcurrentSolution.producer.Producer;
import assignment6ConcurrentSolution.producer.StudentVleProducer;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Represents a CSVProcessor.
 */
public class CSVProcessor {

  private final int ARG_NUM = 4;
  private final String NORMALIZED_DATE = "normalized_date";
  private final String TOTAL_CLICKS = "total_clicks";
  private final String RELATIVE_CLICKS = "relative_clicks";
  private final String DATE = "date";
  private final String ASSESSMENT_TYPE = "assessment_type";
  private final String MODULE_PRESENTATION = "module_presentation";
  private final int THREAD_NUM = 5;
  private final int CONSUMER_NUM = 10;
  private final int CAPACITY = 10;
  private final static String EXIT = "EXIT";

  /**
   * Outputs CSV files.
   *
   * @param args is given files and threshold.
   * @throws Exception exceptions.
   */
  public void outputCSVs(String[] args) throws Exception {
    if (args == null || args.length != ARG_NUM) {
      return;
    }

    // paths and threshold
    String coursePath = args[0];
    String studentVlePath = args[1];
    String assessmentPath = args[2];
    String threshold = args[3];

    // shared storage
    ConcurrentMap<String, SummaryData> courseSummaryMap = new ConcurrentHashMap<>(); // one SummaryData for one row
    BlockingQueue<String[]> dataBQ = new LinkedBlockingDeque<>(CAPACITY); // producer -> consumer

    // construct producers
    List<Producer> producers = new ArrayList<>();
    producers.add(new CourseProducer(dataBQ, readCSV(coursePath)));
    producers.add(new AssessmentProducer(dataBQ, readCSV(assessmentPath)));
    producers.add(new StudentVleProducer(dataBQ, readCSV(studentVlePath)));

    // start all consumers and producers threads simultaneously
    ExecutorService consumerES = Executors.newFixedThreadPool(THREAD_NUM);
    for (int i = 0; i < CONSUMER_NUM; i++) {
      consumerES.execute(new Consumer(dataBQ, courseSummaryMap));
    }
    ExecutorService producerES = Executors.newCachedThreadPool();
    for (Producer producer : producers) {
      producerES.execute(producer);
    }

    // start timestamp
    long start = System.currentTimeMillis();

    // wait for producers done
    producerES.shutdown();
    while (!producerES.isTerminated()) {
    }

    // when producers done, send EXIT signal to consumers
    Producer.stopProduce(dataBQ, CONSUMER_NUM);
    // shutdown consumers
    consumerES.shutdown();

    // output CSVs
    for (String courseKey : courseSummaryMap.keySet()) {
      outputCSV(courseKey, courseSummaryMap.get(courseKey));
    }

    // output activity
    outputActivityThreshold(courseSummaryMap, Integer.parseInt(threshold));

    // end timestamp
    long end = System.currentTimeMillis();
    System.out.println("Running time in seconds: " + ((end - start) / 1000F));

  }

  //Outputs a CSV file by course ID and summary data.
  private void outputCSV(String courseKey, SummaryData summaryData) {
    // do not want to output summary files without any date:total_click pair
    if (courseKey == null || courseKey.length() == 0) {
      return;
    }
    if (summaryData == null || summaryData.getCourseId() == null
        || summaryData.getCourseLength() == null
        || summaryData.getCourseLength().length() == 0
        || summaryData.getDateTotalclickMap().keySet().size() == 0) {
      return;
    }

    String fileName = courseKey + ".csv";
    CSVWriter writer = null;
    try {
      writer = new CSVWriter(new FileWriter(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

    //Create record
    String[] record = new String[5];
    record[0] = DATE;
    record[1] = NORMALIZED_DATE;
    record[2] = TOTAL_CLICKS;
    record[3] = RELATIVE_CLICKS;
    record[4] = ASSESSMENT_TYPE;
    writer.writeNext(record);

    // get firstClickDate and allTotalClicks
    int firstClickDate = Integer.MAX_VALUE;
    int allTotalClicks = 0;
    Map<String, String> dateTotalClickMap = summaryData.getDateTotalclickMap();
    for (String date : dateTotalClickMap.keySet()) {
      try {
        firstClickDate = Math
            .min(firstClickDate, Integer.parseInt(date));
        allTotalClicks += Integer.parseInt(dateTotalClickMap.get(date));
      } catch (NumberFormatException nfe) {
        return;
      }
    }

    int courseLength = Integer.parseInt(summaryData.getCourseLength());
    for (String date : dateTotalClickMap.keySet()) {
      try {
        record[0] = date;

        // normalized_date: date / course_length
        record[1] = String.format("%.2f",
            (double) Integer.parseInt(date) / (double) courseLength);

        record[2] = dateTotalClickMap.get(date);

        // relative_clicks: (total_clicks) / (daily average number)
        // daily average number: (all total_clicks) / (active period)
        // active period: course_length - (first date of click record)
        int activePeriod = courseLength - firstClickDate;
        double dailyAverage = (double) allTotalClicks / (double) activePeriod;
        double relativeClicks =
            (double) Integer.parseInt(dateTotalClickMap.get(date)) / dailyAverage;
        record[3] = String.format("%.2f", relativeClicks);

        record[4] = summaryData.getDateAssessmentMap().getOrDefault(date, "");
      } catch (NumberFormatException nfe) {
        return;
      }

      writer.writeNext(record);
    }

    //close the writer
    try {
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Outputs an activity file.
   *
   * @param courseSummaryMap is given courseSummaryMap.
   * @param thresholdNum     is given threshold.
   * @throws IOException from parse.
   */
  private void outputActivityThreshold(ConcurrentMap<String, SummaryData> courseSummaryMap,
      int thresholdNum) throws IOException {
    if (courseSummaryMap == null || courseSummaryMap.size() == 0) {
      return;
    }

    String fileName = "activity-" + thresholdNum + ".csv";
    CSVWriter writer = new CSVWriter(new FileWriter(fileName));

    // first write header line
    String[] record = new String[3];
    record[0] = MODULE_PRESENTATION;
    record[1] = DATE;
    record[2] = TOTAL_CLICKS;
    writer.writeNext(record);

    // then write all data rows
    for (String courseKey : courseSummaryMap.keySet()) {
      SummaryData summaryData = courseSummaryMap.get(courseKey);

      for (String date : summaryData.getDateTotalclickMap().keySet()) {
        int totalClicks = Integer.MAX_VALUE;
        try {
          totalClicks = Integer.parseInt(summaryData.getDateTotalclickMap().get(date));
          if (totalClicks < thresholdNum) {
            continue;
          }
        } catch (NumberFormatException e) {
          continue;
        }

        record[0] = courseKey;
        record[1] = date;
        record[2] = String.valueOf(totalClicks);

        writer.writeNext(record);
      }
    }

    //close the writer
    writer.close();
  }

  // returns a CSVReader for given file.
  private CSVReader readCSV(String path) throws IOException {
    Reader reader = Files.newBufferedReader(Paths.get(path));
    return new CSVReader(reader);
  }
}
