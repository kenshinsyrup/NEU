package assignment6ConcurrentSolution;

/**
 * Represents entry main.
 */
public class Main {

  /**
   * Start entry.
   *
   * @param args is given arguments.
   */
  public static void main(String[] args) {
    System.out.println("Hello concurrentSolution!");

    try {
      CSVProcessor csvProcessor = new CSVProcessor();
      csvProcessor.outputCSVs(args);
    } catch (Exception exception) {
      System.out.println(exception);
    }

  }
}

