package assignment6ConcurrentSolution.producer;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Represents a CourseProducer.
 */
public class CourseProducer extends Producer implements Runnable {

  private final String CODE_MODULE = "code_module";
  private final String CODE_PRESENTATION = "code_presentation";
  private final String MODULE_PRESENTATION_LENGTH = "module_presentation_length";
  private final String COURSE = "course";

  /**
   * Constructs a CourseProducer.
   *
   * @param dataBQ       is given dataBQ.
   * @param courseReader is given courseReader.
   */
  public CourseProducer(BlockingQueue<String[]> dataBQ, CSVReader courseReader) {
    super(dataBQ, courseReader);
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
    int modulePresentationLengthIdx = -1;
    for (int i = 0; i < header.length; i++) {
      switch (header[i]) {
        case CODE_MODULE:
          codeModuleIdx = i;
        case CODE_PRESENTATION:
          codePresentationIdx = i;
        case MODULE_PRESENTATION_LENGTH:
          modulePresentationLengthIdx = i;
        default:
      }
    }
    if (codeModuleIdx == -1 || codePresentationIdx == -1 || modulePresentationLengthIdx == -1) {
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
            new String[]{COURSE, nextRecord[codeModuleIdx] + "_" + nextRecord[codePresentationIdx],
                nextRecord[modulePresentationLengthIdx]});
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
