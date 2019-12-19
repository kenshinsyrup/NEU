package thresholdConcurrentSolution;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
  private final int CAPACITY = 5;

  private String coursesPath;
  private String studentVlePath;
  private int thresholdNum;

  public void outputCSVs(String[] paths) throws Exception {
    if (paths == null || paths.length != PATH_NUM) {
      return;
    }

    // parse paths
    this.coursesPath = paths[0];
    this.studentVlePath = paths[1];
    this.thresholdNum = Integer.parseInt(paths[2]);

    // parse courses.csv and studentVle.csv
    CSVReader courseReader = readCSV(this.coursesPath);
    Set<String> courseKeySet = getCourseKeySet(courseReader);

    // ConcurrentMap between consumer and consumer
    ConcurrentMap<String, Map<String, String>> courseThresholdMap = new ConcurrentHashMap<>();

    // BlockingQueue between producer and consumers
    Producer producer = new Producer();
    List<Consumer> consumerList = new ArrayList<>();
    for (String courseKey : courseKeySet) {
      BlockingQueue<String[]> dataBQ = new LinkedBlockingDeque<>(CAPACITY);
      producer.getCourseDataMap().put(courseKey, dataBQ);
      Consumer consumer = new Consumer(courseKey, dataBQ, this, courseThresholdMap,
          this.thresholdNum);
      consumerList.add(consumer);
    }

    // produce: read studentVle line by line, then stop produce
    CSVReader studentVlereader = readCSV(this.studentVlePath);
    outputSummaryFiles(studentVlereader, courseKeySet, producer, consumerList);
    producer.stopProduce();

    // after all consumers done with threshold analysis, output
    outputActivityThreshold(courseThresholdMap, this.thresholdNum);
  }

  private void outputActivityThreshold(ConcurrentMap<String, Map<String, String>> courseActivityMap,
      int thresholdNum) throws IOException {
    if (courseActivityMap == null) {
      return;
    }

    String fileName = "activity-" + thresholdNum + ".csv";
    CSVWriter writer = new CSVWriter(new FileWriter(fileName));

    //Create record
    // first write header line
    String[] record = new String[3];
    record[0] = "module_presentation";
    record[1] = "date";
    record[2] = "total_clicks";
    writer.writeNext(record);

    // then write all data rows
    for (String courseKey : courseActivityMap.keySet()) {
      record[0] = courseKey;
      for (String date : courseActivityMap.get(courseKey).keySet()) {
        record[1] = date;
        record[2] = courseActivityMap.get(courseKey).get(date);
        writer.writeNext(record);
      }
    }

    //close the writer
    writer.close();
  }

  private void outputSummaryFiles(CSVReader studentVlereader, Set<String> courseKeySet,
      Producer producer,
      List<Consumer> consumerList) throws Exception {
    if (studentVlereader == null) {
      return;
    }

    // skip the header line
    String[] header = studentVlereader.readNext();
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

    // start consumers
    for (Consumer consumer : consumerList) {
      new Thread(consumer).start();
    }

    // Reading Records One by One in a String array
    String[] nextRecord;
    while ((nextRecord = studentVlereader.readNext()) != null) {
      // if this course do not exist in courses.csv, skip it
      String courseKey = nextRecord[codeModuleIdx] + "_" + nextRecord[codePresentationIdx];
      if (!courseKeySet.contains(courseKey)) {
        continue;
      }

      producer.getCourseDataMap().get(courseKey)
          .put(new String[]{nextRecord[dateIdx], nextRecord[sumClickIdx]});
    }

  }


  private CSVReader readCSV(String path) throws IOException {
    Reader reader = Files.newBufferedReader(Paths.get(path));
    return new CSVReader(reader);
  }

  /**
   * Gets all course keys with code_module and code_presentation.
   *
   * @param csvReader
   * @return
   * @throws IOException
   */
  private Set<String> getCourseKeySet(CSVReader csvReader) throws IOException {
    if (csvReader == null) {
      return null;
    }

    Set<String> courseKeySet = new HashSet<>();
    // get the idx of CODE_MODULE and CODE_PRESENTATION in the header line
    String[] header = csvReader.readNext();
    if (header == null || header.length == 0) {
      return null;
    }
    int codeModuleIdx = -1;
    int codePresentationIdx = -1;
    for (int i = 0; i < header.length; i++) {
      if (header[i].equals(CODE_MODULE)) {
        codeModuleIdx = i;
      } else if (header[i].equals(CODE_PRESENTATION)) {
        codePresentationIdx = i;
      }
    }
    if (codeModuleIdx == -1 || codePresentationIdx == -1) {
      return null;
    }

    // Reading Records One by One in a String array
    String[] nextRecord;
    while ((nextRecord = csvReader.readNext()) != null) {
      courseKeySet.add(nextRecord[codeModuleIdx] + "_" + nextRecord[codePresentationIdx]);
    }

    return courseKeySet;
  }


  /**
   * Outputs a csv file.
   *
   * @param courseKey
   * @param dateClickMap
   * @throws IOException
   */
  public void outputCSV(String courseKey, Map<String, String> dateClickMap) throws IOException {
    // do not want to output summary files without any date:total_click pair
    if (courseKey == null || courseKey.length() == 0 || dateClickMap == null
        || dateClickMap.keySet().size() == 0) {
      return;
    }

    String fileName = courseKey + ".csv";
    CSVWriter writer = new CSVWriter(new FileWriter(fileName));

    //Create record
    // first write header line
    String[] record = new String[2];
    record[0] = "date";
    record[1] = "total_clicks";
    writer.writeNext(record);

    // then write all data rows
    for (String date : dateClickMap.keySet()) {
      record[0] = date;
      record[1] = dateClickMap.get(date);
      writer.writeNext(record);
    }

    //close the writer
    writer.close();

  }
}
