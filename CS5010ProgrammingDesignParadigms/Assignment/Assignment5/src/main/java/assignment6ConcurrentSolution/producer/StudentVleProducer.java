package assignment6ConcurrentSolution.producer;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Represents a StudentVleProducer.
 */
public class StudentVleProducer extends Producer implements Runnable {

  private final String CODE_MODULE = "code_module";
  private final String CODE_PRESENTATION = "code_presentation";
  private final String DATE = "date";
  private final String SUM_CLICK = "sum_click";
  private final String STUDENT_VLE = "studentVle";

  /**
   * Constructs a StudentVleProducer.
   *
   * @param dataBQ    is given dataBQ.
   * @param csvReader is given csvReader.
   */
  public StudentVleProducer(BlockingQueue<String[]> dataBQ,
      CSVReader csvReader) {
    super(dataBQ, csvReader);
  }

  @Override
  public void run() {
    CSVReader csvReader = getCsvReader();
    BlockingQueue<String[]> dataBQ = getDataBQ();

    // skip the header line
    String[] header = new String[0];
    try {
      header = csvReader.readNext();
    } catch (IOException e) {
      e.printStackTrace();
    }
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
    String[] nextRecord = null;
    while (true) {
      try {
        nextRecord = csvReader.readNext();
        if (nextRecord == null) {
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
        return;
      }

      try {
        dataBQ.put(
            new String[]{STUDENT_VLE,
                nextRecord[codeModuleIdx] + "_" + nextRecord[codePresentationIdx],
                nextRecord[sumClickIdx], nextRecord[dateIdx]});
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
