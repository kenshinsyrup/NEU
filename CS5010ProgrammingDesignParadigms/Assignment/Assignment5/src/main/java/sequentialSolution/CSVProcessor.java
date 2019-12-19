package sequentialSolution;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CSVProcessor {

  private final static int PATH_NUM = 2;
  private final static String CODE_MODULE = "code_module";
  private final static String CODE_PRESENTATION = "code_presentation";
  private final static String DATE = "date";
  private final static String SUM_CLICK = "sum_click";


  public void outputSummaryFiles(String[] paths) throws IOException {
    if (paths == null || paths.length != PATH_NUM) {
      return;
    }

    // parse courses.csv and studentVle.csv
    CSVReader courseReader = readCSV(paths[0]);
    CSVReader studentVlereader = readCSV(paths[1]);
    Set<String> courseKeySet = getCourseKeySet(courseReader);
    Map<String, Map<String, String>> courseDateClickMap = getCourseDateClicks(studentVlereader,
        courseKeySet);
    if (courseDateClickMap == null) {
      return;
    }

    // output CSV
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
   * Gets total clicks in every date for every course.
   *
   * @param csvReader
   * @param courseKeySet
   * @return
   * @throws IOException
   */
  private Map<String, Map<String, String>> getCourseDateClicks(CSVReader csvReader,
      Set<String> courseKeySet) throws IOException {
    if (csvReader == null) {
      return null;
    }

    Map<String, Map<String, String>> courseDateClickMap = new HashMap<>();
    // skip the header line
    String[] header = csvReader.readNext();
    if (header == null || header.length == 0) {
      return null;
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
      return null;
    }

    // Reading Records One by One in a String array
    String[] nextRecord;
    while ((nextRecord = csvReader.readNext()) != null) {
      // if this course do not exist in courses.csv, skip it
      String courseKey = nextRecord[codeModuleIdx] + "_" + nextRecord[codePresentationIdx];
      if (!courseKeySet.contains(courseKey)) {
        continue;
      }

      // update the date:click pair in courseDateClickMap
      String date = nextRecord[dateIdx];
      Map<String, String> dateClickMap = courseDateClickMap
          .getOrDefault(courseKey, new HashMap<>());
      String previousClicks = dateClickMap.getOrDefault(date, "0");
      dateClickMap
          .put(date, String
              .valueOf(Long.parseLong(nextRecord[sumClickIdx]) + Long.parseLong(previousClicks)));
      courseDateClickMap.put(courseKey, dateClickMap);
    }

    return courseDateClickMap;

  }


  /**
   * Outputs a csv file.
   *
   * @param courseKey
   * @param dateClickMap
   * @throws IOException
   */
  private void outputCSV(String courseKey, Map<String, String> dateClickMap) throws IOException {
    if (courseKey == null || courseKey.length() == 0) {
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
