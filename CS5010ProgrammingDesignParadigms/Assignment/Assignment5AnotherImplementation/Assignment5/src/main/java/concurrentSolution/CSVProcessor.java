package concurrentSolution;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;

public class CSVProcessor {

  private final static int PATH_NUM = 2;
  private final static String CODE_MODULE = "code_module";
  private final static String CODE_PRESENTATION = "code_presentation";
  private final static String DATE = "date";
  private final static String SUM_CLICK = "sum_click";
  private final static int THREAD_NUM = 5;

  public void outputCSVs(String[] paths) throws Exception {
    if (paths == null || paths.length != PATH_NUM) {
      return;
    }

    // parse courses.csv and studentVle.csv
    CSVReader courseReader = readCSV(paths[0]);
    Set<String> courseKeySet = getCourseKeySet(courseReader);

    // consumers
    ConcurrentMap<String, Map<String, String>> courseDateClickMap = new ConcurrentHashMap<>();
    BlockingQueue<String[]> dataBQ = new LinkedBlockingDeque<>(100);
    for (int i = 0; i < THREAD_NUM; i++) {
      Consumer consumer = new Consumer(courseKeySet, dataBQ, courseDateClickMap);
      new Thread(consumer).start();
    }

    // producer
    CSVReader studentVlereader = readCSV(paths[1]);
    Producer producer = new Producer(dataBQ, studentVlereader);
    getCourseDateClicks(studentVlereader, courseKeySet, producer);
    producer.stopProduce(THREAD_NUM);

    // output CSVs
    for (String courseKey : courseDateClickMap.keySet()) {
      outputCSV(courseKey, courseDateClickMap.get(courseKey));
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

  private void getCourseDateClicks(CSVReader csvReader,
      Set<String> courseKeySet, Producer producer) throws Exception {
    if (csvReader == null) {
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


}
