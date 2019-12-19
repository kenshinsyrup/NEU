package assignment6ConcurrentSolution;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CSVProcessorTest {
  private CSVProcessor csvProcessor;

  @Before
  public void setUp() {
    csvProcessor = new CSVProcessor();
  }

  @Test
  public void outputCSVs() {
    String[] args = {"courses.csv", "studentVle.csv", "assessments.csv", "11000"};

    try {
      csvProcessor.outputCSVs(args);
    }  catch (Exception e) {
      assertNull(e);
    }
  }
}