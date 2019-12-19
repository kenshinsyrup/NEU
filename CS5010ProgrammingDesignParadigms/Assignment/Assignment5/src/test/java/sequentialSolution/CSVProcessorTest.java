package sequentialSolution;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class CSVProcessorTest {
  private CSVProcessor csvProcessor;

  @Before
  public void setUp() throws Exception {
    csvProcessor = new CSVProcessor();
  }

  @Test
  public void outputSummaryFiles(){
    String[] paths = {"courses.csv", "studentVle_for_test.csv"};

    try {
      csvProcessor.outputSummaryFiles(paths);
    }  catch (Exception e) {
      assertNull(e);
    }
  }
}