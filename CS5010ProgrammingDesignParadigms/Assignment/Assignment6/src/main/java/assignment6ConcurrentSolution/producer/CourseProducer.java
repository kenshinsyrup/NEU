package assignment6ConcurrentSolution.producer;

import com.opencsv.CSVReader;
import assignment6ConcurrentSolution.data.Course;
import assignment6ConcurrentSolution.data.IData;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class CourseProducer extends Producer implements Runnable {

  private final String CODE_MODULE = "code_module";
  private final String CODE_PRESENTATION = "code_presentation";
  private final String MODULE_PRESENTATION_LENGTH = "module_presentation_length";

  public CourseProducer(BlockingQueue<IData> dataBQ, CSVReader courseReader) {
    super(dataBQ, courseReader);
  }

  @Override
  public void run() {
    System.out.println("Reading course...");
    BlockingQueue<IData> dataBQ = getDataBQ();
    CSVReader courseReader = getCsvReader();

    // skip the header line
    String[] header = new String[0];
    try {
      header = courseReader.readNext();
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
        nextRecord = courseReader.readNext();
        if (nextRecord == null) {
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      Course course = new Course(nextRecord[codeModuleIdx] + "_" + nextRecord[codePresentationIdx],
          nextRecord[modulePresentationLengthIdx]);

      System.out.println("read course: " + course.getCourseId() +" len: "+ course.getCourseLength());

      produce(course);
    }

    System.out.println("Reading course done.");
  }
}
