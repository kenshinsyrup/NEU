package assignment6ConcurrentSolution.producer;

import com.opencsv.CSVReader;
import java.util.concurrent.BlockingQueue;

/**
 * Represents a Producer.
 */
public class Producer implements Runnable {

  private final static String EXIT = "EXIT";

  private BlockingQueue<String[]> dataBQ;
  private CSVReader csvReader;

  /**
   * Constructs a Producer.
   *
   * @param dataBQ    is given dataBQ.
   * @param csvReader is given csvReader.
   */
  public Producer(BlockingQueue<String[]> dataBQ, CSVReader csvReader) {
    this.dataBQ = dataBQ;
    this.csvReader = csvReader;
  }

  /**
   * Gets dataBQ.
   *
   * @return dataBQ.
   */
  public BlockingQueue<String[]> getDataBQ() {
    return dataBQ;
  }

  /**
   * Gets csvReader.
   *
   * @return csvReader.
   */
  public CSVReader getCsvReader() {
    return csvReader;
  }

  /**
   * Sends EXIT msg to all BlockingQueue
   */
  public static void stopProduce(BlockingQueue<String[]> dataBQ, int consumerNum)
      throws InterruptedException {
    for (int i = 0; i < consumerNum; i++) {
      dataBQ.put(new String[]{EXIT});
    }
  }

  @Override
  public void run() {

  }
}
