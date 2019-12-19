package concurrentSolution;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

public class Consumer implements Runnable {

  private final static String EXIT = "EXIT";

  // stores [date, sum_click], shared by producer and customer
  private BlockingQueue<String[]> dataBQ;

  // helps to write output summary csv file
  private CSVProcessor csvProcessor;

  private ConcurrentMap<String, Map<String, String>> courseDateClickMap;

  Set<String> courseKeySet;

  public Consumer(Set<String> courseKeySet, BlockingQueue<String[]> dataBQ,
      ConcurrentMap<String, Map<String, String>> courseDateClickMap) {
    this.courseKeySet = courseKeySet;
    this.dataBQ = dataBQ;
    this.courseDateClickMap = courseDateClickMap;
  }

  @Override
  public void run() {

    // run until receive EXIT signal
    while (true) {
      String[] data = new String[0];
      try {
        data = this.dataBQ.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      // if receive EXIT signal, stop running
      if (data[0].equals(EXIT)) {
        break;
      }

      // process course_id:date:sum_click pair
      String courseKey = data[0] + "_" + data[1];
      if (!this.courseKeySet.contains(courseKey)) {
        continue;
      }
      String date = data[2];
      Map<String, String> dateClickMap = this.courseDateClickMap
          .getOrDefault(courseKey, new HashMap<>());
      String previousClicks = dateClickMap.getOrDefault(date, "0");
      dateClickMap
          .put(date, String
              .valueOf(Long.parseLong(data[3]) + Long.parseLong(previousClicks)));
      this.courseDateClickMap.put(courseKey, dateClickMap);
    }

  }

}
