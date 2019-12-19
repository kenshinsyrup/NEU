package assignment6ConcurrentSolution.producer;

import com.opencsv.CSVReader;
import assignment6ConcurrentSolution.data.IData;
import assignment6ConcurrentSolution.data.StudentVle;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class StudentVleProducer extends Producer implements Runnable {

  private final String CODE_MODULE = "code_module";
  private final String CODE_PRESENTATION = "code_presentation";
  private final String DATE = "date";
  private final String SUM_CLICK = "sum_click";

  public StudentVleProducer(BlockingQueue<IData> dataBQ,
      CSVReader csvReader) {
    super(dataBQ, csvReader);
  }

  @Override
  public void run() {
    CSVReader studentVleReader = getCsvReader();

    // skip the header line
    String[] header = new String[0];
    try {
      header = studentVleReader.readNext();
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
        nextRecord = studentVleReader.readNext();
        if (nextRecord == null) {
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      StudentVle studentVle = new StudentVle(
          nextRecord[codeModuleIdx] + "_" + nextRecord[codePresentationIdx],
          nextRecord[sumClickIdx], nextRecord[dateIdx]);

      System.out.println("read studentVle: " + studentVle.getCourseId()+" date: "+studentVle.getDate());

      produce(studentVle);
    }

    System.out.println("Reading studentVle done.");

  }
}
