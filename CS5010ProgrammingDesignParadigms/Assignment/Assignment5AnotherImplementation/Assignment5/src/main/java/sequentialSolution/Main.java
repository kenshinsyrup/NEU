package sequentialSolution;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello sequentialSolution!");

    CSVProcessor csvProcessor = new CSVProcessor();
    try {
      csvProcessor.outputSummaryFiles(args);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }

  }
}
