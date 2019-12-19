package utils;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a CSVProcessor.
 */
public class CSVProcessor {

  private CSVReader csvReader;

  /**
   * Inits a CSVProcessor
   *
   * @param path is given csv path.
   * @throws IOException
   */
  public CSVProcessor(String path) throws IOException {
    Reader reader = Files.newBufferedReader(Paths.get(path));
    this.csvReader = new CSVReader(reader);
  }

  /**
   * Parses csv files.
   *
   * @return csv rows in list.
   * @throws Exception
   */
  public List<String[]> parseData() throws Exception {
    List<String[]> dataList = new ArrayList<>();
    String[] nextRecord;
    while ((nextRecord = this.csvReader.readNext()) != null) {
      // String[] data length is 12, same as the number of User fields.
      String[] data = new String[12];
      for (int i = 0; i < nextRecord.length; i++) {
        data[i] = nextRecord[i];
      }
      dataList.add(data);
    }

    return dataList;
  }


  /**
   * Hash code.
   *
   * @return hashcode.
   */
  public int hashcode() {
    return 17;
  }

  /**
   * Compares.
   *
   * @param csvProcessor is given csvProcessor.
   * @return true if equals.
   */
  public boolean equals(CSVProcessor csvProcessor) {
    return this.hashcode() == csvProcessor.hashcode();
  }

  /**
   * To string.
   *
   * @return String.
   */
  public String toString() {
    return "CSVProcessor";
  }


}
