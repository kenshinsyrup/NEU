package assignment6ConcurrentSolution.producer;

import com.opencsv.CSVReader;
import assignment6ConcurrentSolution.data.Assessment;
import assignment6ConcurrentSolution.data.IData;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class AssessmentProducer extends Producer implements Runnable {

  private final static String CODE_MODULE = "code_module";
  private final static String CODE_PRESENTATION = "code_presentation";
  private final static String DATE = "date";
  private final static String ASSESSMENT_TYPE = "assessment_type";

  public AssessmentProducer(BlockingQueue<IData> dataBQ,
      CSVReader csvReader) {
    super(dataBQ, csvReader);
  }

  @Override
  public void run() {
    CSVReader assessmentReader = getCsvReader();

    // skip the header line
    String[] header = new String[0];
    try {
      header = assessmentReader.readNext();
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
        nextRecord = assessmentReader.readNext();
        if (nextRecord == null) {
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      Assessment assessment = new Assessment(
          nextRecord[codeModuleIdx] + "_" + nextRecord[codePresentationIdx],
          nextRecord[assessmentTypeIdx], nextRecord[dateIdx]);

      System.out.println("read assessment: " + assessment.getCourseId() + " type: " + assessment
          .getAssessmentType() + " assessment_date: " + assessment.getDate());

      produce(assessment);
    }

    System.out.println("Reading assessment done.");

  }
}
