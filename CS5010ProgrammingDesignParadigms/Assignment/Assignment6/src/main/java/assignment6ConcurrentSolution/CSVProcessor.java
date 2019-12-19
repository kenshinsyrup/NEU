package assignment6ConcurrentSolution;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import assignment6ConcurrentSolution.consumer.Consumer;
import assignment6ConcurrentSolution.data.Course;
import assignment6ConcurrentSolution.data.IData;
import assignment6ConcurrentSolution.producer.AssessmentProducer;
import assignment6ConcurrentSolution.producer.CourseProducer;
import assignment6ConcurrentSolution.producer.Producer;
import assignment6ConcurrentSolution.producer.StudentVleProducer;
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
import java.util.concurrent.LinkedBlockingDeque;

public class CSVProcessor {

  private final static int PATH_NUM = 3;
  private final static String CODE_MODULE = "code_module";
  private final static String CODE_PRESENTATION = "code_presentation";
  private final static String DATE = "date";
  private final static String SUM_CLICK = "sum_click";
  private final static String ASSESSMENT_TYPE = "assessment_type";
  private final static int CONSUMER_NUM = 5;

  public void outputCSVs(String[] paths) throws Exception {
    if (paths == null || paths.length != PATH_NUM) {
      return;
    }

    // paths
    String coursePath = paths[0];
    String studentVlePath = paths[1];
    String assessmentPath = paths[2];

    // shared storage
    ConcurrentMap<String, Course> idCourseMap = new ConcurrentHashMap<>(); // store id and course info
    ConcurrentMap<String, Map<String, SummaryData>> courseDateSummaryMap = new ConcurrentHashMap<>(); // one SummaryData for one row
    BlockingQueue<IData> dataBQ = new LinkedBlockingDeque<>(100); // producer -> consumer

    // consumers
    for (int i = 0; i < CONSUMER_NUM; i++) {
      Consumer consumer = new Consumer(dataBQ, courseDateSummaryMap, idCourseMap);
      new Thread(consumer).start();
    }
    System.out.println("Consumers started.");

    // producers
    List<Producer> producerList = new ArrayList<>();
    CSVReader courseReader = readCSV(coursePath);
    CSVReader studentVleReader = readCSV(studentVlePath);
    CSVReader assessmentReader = readCSV(assessmentPath);
    CourseProducer courseProducer = new CourseProducer(dataBQ, courseReader);
    StudentVleProducer studentVleProducer = new StudentVleProducer(dataBQ, studentVleReader);
    AssessmentProducer assessmentProducer = new AssessmentProducer(dataBQ, assessmentReader);
    Thread courseThread = new Thread(courseProducer);
    Thread studentVleThread = new Thread(studentVleProducer);
    Thread assessmentThread = new Thread(assessmentProducer);
    courseThread.start();
    studentVleThread.start();
    assessmentThread.start();
//    producerList.add(courseProducer);
//    producerList.add(studentVleProducer);
//    producerList.add(assessmentProducer);
//    for (Producer producer : producerList) {
//      new Thread(producer).start();
//    }
    System.out.println("Producers started.");

    courseThread.join();
    studentVleThread.join();
    assessmentThread.join();
    System.out.println("Producers done.");

    // TODO: correctly observe the end of 3 producers and then stop consumers
    // here need block main thread until end produce and consume
//    ExecutorService es = Executors.newCachedThreadPool();
//    es.execute(courseProducer);
//    es.execute(studentVleProducer);
//    es.execute(assessmentProducer);
//    es.shutdown();
    Producer.stopProduce(dataBQ, CONSUMER_NUM);

    // output CSVs
    for (String courseKey : courseDateSummaryMap.keySet()) {
      System.out.println("output: " + courseKey);
      outputCSV(courseKey, courseDateSummaryMap.get(courseKey), idCourseMap);
    }

  }

  private CSVReader readCSV(String path) throws IOException {
    Reader reader = Files.newBufferedReader(Paths.get(path));
    return new CSVReader(reader);
  }

  public void outputCSV(String courseKey, Map<String, SummaryData> dateSummaryMap,
      Map<String, Course> idCourseMap)
      throws IOException {
    // do not want to output summary files without any date:total_click pair
    if (courseKey == null || courseKey.length() == 0 || dateSummaryMap == null
        || dateSummaryMap.keySet().size() == 0) {
      return;
    }

    String fileName = courseKey + ".csv";
    CSVWriter writer = new CSVWriter(new FileWriter(fileName));

    //Create record
//    date, normalized_date, total_clicks, relative_clicks, and assessment_type:
    // first write header line
    String[] record = new String[5];
    record[0] = "date";
    record[1] = "normalized_date";
    record[2] = "total_clicks";
    record[3] = "relative_clicks";
    record[4] = "assessment_type";
    writer.writeNext(record);

    // then write all data rows
    int firstClickDate = Integer.MAX_VALUE;
    int allTotalClicks = 0;
    for (String date : dateSummaryMap.keySet()) {
      System.out.println("date: " + date);
      SummaryData summaryData = dateSummaryMap.get(date);

      if (summaryData.getDate() != null) {
        System.out.println("summary date: " + summaryData.getDate());
        firstClickDate = Math
            .min(firstClickDate, Integer.parseInt(summaryData.getDate()));
      }
      if (summaryData.getTotalClicks() != null) {
        System.out.println("summary total_clicks: " + summaryData.getTotalClicks());
        allTotalClicks += Integer.parseInt(summaryData.getTotalClicks());
      }
    }
    System.out.println(
        "Course: " + courseKey + " firstClickDate: " + firstClickDate + " allTotalClicks: "
            + allTotalClicks);

    int courseLength = Integer.parseInt(idCourseMap.get(courseKey).getCourseLength());
    for (String date : dateSummaryMap.keySet()) {
      SummaryData summaryData = dateSummaryMap.get(date);

      System.out
          .println("record course: " + courseKey + " date: " + date + " " + summaryData.getDate());

      if (summaryData.getDate() == null || summaryData.getTotalClicks() == null) {
        continue;
      }

      record[0] = summaryData.getDate();
      // normalized_date: date / course_length
      record[1] = String.format("%.2f",
          (double) Integer.parseInt(summaryData.getDate()) / (double) courseLength);
      System.out.println("record[1]: " + record[1]);
      record[2] = summaryData.getTotalClicks();
      // relative_clicks: (total_clicks) / (daily average number)
      // daily average number: (all total_clicks) / (active period)
      // active period: course_length - (first date of click record)
      int activePeriod = courseLength - firstClickDate;
      double dailyAverage = (double) allTotalClicks / (double) activePeriod;
      double relativeClicks =
          (double) Integer.parseInt(summaryData.getTotalClicks()) / dailyAverage;
      record[3] = String.format("%.2f", relativeClicks);
      System.out.println("record[3]: " + record[3]);
      record[4] = summaryData.getAssessmentType();

      writer.writeNext(record);
    }

    //close the writer
    writer.close();

  }

  /*
  private void getSummaryData(Producer courseProducer, Producer studentVleProducer,
      Producer assessmentProducer) throws Exception {
    if (courseProducer == null || studentVleProducer == null || assessmentProducer == null) {
      return;
    }

    Map<String, Map<String, String>> courseDateClickMap = new HashMap<>();
    // skip the header line
    String[] header = csvReader.readNext();
    if (header == null || header.length == 0) {
      return;
    }
    int codeModuleIdx = -1;
    int codePresentationIdx = -1;
    int dateIdx = -1;
    int sumClickIdx = -1;
    for (int i = 0; i < header.length; i++) {
      switch (header[i]) {
        case CODE_MODULE:
          codeModuleIdx = i;
        case CODE_PRESENTATION:
          codePresentationIdx = i;
        case DATE:
          dateIdx = i;
        case SUM_CLICK:
          sumClickIdx = i;
        default:
      }
    }
    if (codeModuleIdx == -1 || codePresentationIdx == -1 || dateIdx == -1 || sumClickIdx == -1) {
      return;
    }

    // Reading Records One by One in a String array
    String[] nextRecord;
    while ((nextRecord = csvReader.readNext()) != null) {

      producer.produce(new String[]{nextRecord[codeModuleIdx], nextRecord[codePresentationIdx],
          nextRecord[dateIdx], nextRecord[sumClickIdx]});

    }

  }

*/
}
