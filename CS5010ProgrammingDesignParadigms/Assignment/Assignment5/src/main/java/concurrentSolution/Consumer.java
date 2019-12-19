package concurrentSolution;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

  private final static String EXIT = "EXIT";

  // the course key used as consumer ID
  private String courseKey;

  // stores [date, sum_click], shared by producer and customer
  private BlockingQueue<String[]> dataBQ;

  // stores the date:click pair of course key csv for summary
  private Map<String, String> dateClickMap;

  // helps to write output summary csv file
  private CSVProcessor csvProcessor;

  public Consumer(String courseKey, BlockingQueue<String[]> dataBQ, CSVProcessor csvProcessor) {
    this.courseKey = courseKey;
    this.dataBQ = dataBQ;
    this.dateClickMap = new HashMap<>();
    this.csvProcessor = csvProcessor;
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

      // process date:sum_click pair
      String date = data[0];
      String previousClicks = this.dateClickMap.getOrDefault(date, "0");
      this.dateClickMap
          .put(date, String
              .valueOf(Long.parseLong(data[1]) + Long.parseLong(previousClicks)));
    }

    try {
      this.csvProcessor.outputCSV(this.courseKey, this.dateClickMap);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
