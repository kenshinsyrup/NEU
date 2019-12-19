package concurrentSolution;

import com.opencsv.CSVReader;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class Producer {

  private final static int PATH_NUM = 2;
  private final static String CODE_MODULE = "code_module";
  private final static String CODE_PRESENTATION = "code_presentation";
  private final static String DATE = "date";
  private final static String SUM_CLICK = "sum_click";

  private final static String EXIT = "EXIT";

  private BlockingQueue<String[]> dataBQ;

  private CSVReader studentVlereader;

  public Producer(BlockingQueue<String[]> dataBQ, CSVReader studentVlereader) {
    this.dataBQ = dataBQ;
    this.studentVlereader = studentVlereader;
  }

  public void produce(String[] record) throws Exception {
    this.dataBQ.put(record);
  }

  public void stopProduce(int threadNum) throws InterruptedException {
    for (int i = 0; i < threadNum; i++) {
      this.dataBQ.put(new String[]{EXIT, null, null});
    }
  }

  /**
   * Sends EXIT msg to all BlockingQueue
   */
//  public void stopProduce() throws InterruptedException {
//    for (String key : this.courseDataMap.keySet()) {
//      this.courseDataMap.get(key).put(new String[]{EXIT, ""});
//    }
//  }
//
//  public Map<String, BlockingQueue<String[]>> getCourseDataMap() {
//    return courseDataMap;
//  }
}
