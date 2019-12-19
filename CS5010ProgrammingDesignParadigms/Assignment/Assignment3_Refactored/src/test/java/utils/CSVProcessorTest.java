package utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CSVProcessorTest {

  private CSVProcessor csvProcessor;
  private CSVProcessor csvProcessor1;

  @Before
  public void setUp() throws Exception {
    String csvPath = "src/main/java/static_files/insurancecompanymembers.csv";
    this.csvProcessor = new CSVProcessor(csvPath);
    this.csvProcessor1 = new CSVProcessor(csvPath);
  }

  @Test
  public void parseData() {
    try {
      assertNotNull(this.csvProcessor.parseData());
    } catch (Exception e) {
      assertNull(e);
    }

  }

  @Test
  public void hashcode() {
    assertTrue(this.csvProcessor.hashcode() == 17);
  }

  @Test
  public void testEquals() {
    assertTrue(this.csvProcessor.equals(this.csvProcessor1));
  }

  @Test
  public void testToString() {
    assertTrue(this.csvProcessor.toString().equals("CSVProcessor"));
  }
}