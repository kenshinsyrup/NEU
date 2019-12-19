package assignment6ConcurrentSolution.producer;

import com.opencsv.CSVReader;
import assignment6ConcurrentSolution.data.ExitData;
import assignment6ConcurrentSolution.data.IData;
import java.util.concurrent.BlockingQueue;

public class Producer{

  private BlockingQueue<IData> dataBQ;
  private CSVReader csvReader;

  public Producer(BlockingQueue<IData> dataBQ, CSVReader csvReader) {
    this.dataBQ = dataBQ;
    this.csvReader = csvReader;
  }

  public BlockingQueue<IData> getDataBQ() {
    return dataBQ;
  }

  public CSVReader getCsvReader() {
    return csvReader;
  }

  public void produce(IData data){
    try {
      this.dataBQ.put(data);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sends EXIT msg to all BlockingQueue
   */
  public static void stopProduce(BlockingQueue<IData> dataBQ, int threadNum) throws InterruptedException {
    for (int i = 0; i < threadNum; i++) {
      dataBQ.put(new ExitData());
    }
  }

}
