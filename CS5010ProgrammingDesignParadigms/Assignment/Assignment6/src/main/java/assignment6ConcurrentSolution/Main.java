package assignment6ConcurrentSolution;

public class Main {

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

