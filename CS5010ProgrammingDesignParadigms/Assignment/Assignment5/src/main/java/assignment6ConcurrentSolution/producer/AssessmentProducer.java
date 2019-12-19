package assignment6ConcurrentSolution.producer;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Represents a AssessmentProducer.
 */
public class AssessmentProducer extends Producer implements Runnable {

  private final static String CODE_MODULE = "code_module";
  private final static String CODE_PRESENTATION = "code_presentation";
  private final static String DATE = "date";
  private final static String ASSESSMENT_TYPE = "assessment_type";
  private final String ASSESSMENT = "assessment";

  /**
   * Constructs a AssessmentProducer.
   *
   * @param dataBQ    is given dataBQ.
   * @param csvReader is given csvReader.
   */
  public AssessmentProducer(BlockingQueue<String[]> dataBQ,
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
    int assessmentTypeIdx = -1;
    int dateIdx = -1;
    for (int i = 0; i < header.length; i++) {
      switch (header[i]) {
        case CODE_MODULE:
          codeModuleIdx = i;
        case CODE_PRESENTATION:
          codePresentationIdx = i;
        case DATE:
          dateIdx = i;
        case ASSESSMENT_TYPE:
          assessmentTypeIdx = i;
        default:
      }
    }
    if (codeModuleIdx == -1 || codePresentationIdx == -1 || dateIdx == -1
        || assessmentTypeIdx == -1) {
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
        dataBQ.put(new String[]{ASSESSMENT,
            nextRecord[codeModuleIdx] + "_" + nextRecord[codePresentationIdx],
            nextRecord[assessmentTypeIdx], nextRecord[dateIdx]});
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
