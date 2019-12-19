package concurrentSolution;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Producer {

  private final static String EXIT = "EXIT";

  private Map<String, BlockingQueue<String[]>> courseDataMap;

  public Producer() {
    this.courseDataMap = new HashMap<>();
  }

  /**
   * Sends EXIT msg to all BlockingQueue
   */
  public void stopProduce() throws InterruptedException {
    for (String key : this.courseDataMap.keySet()) {
      this.courseDataMap.get(key).put(new String[]{EXIT, ""});
    }
  }

  public Map<String, BlockingQueue<String[]>> getCourseDataMap() {
    return courseDataMap;
  }
}
