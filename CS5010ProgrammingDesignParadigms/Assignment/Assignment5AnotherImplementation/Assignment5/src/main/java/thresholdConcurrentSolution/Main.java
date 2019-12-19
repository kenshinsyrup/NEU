package thresholdConcurrentSolution;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello thresholdConcurrentSolution!");

    try {
      CSVProcessor csvProcessor = new CSVProcessor();
      csvProcessor.outputCSVs(args);
    } catch (Exception exception) {
      System.out.println(exception);
    }

  }
}

