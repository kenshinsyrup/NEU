package utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ArgParserTest {

  private ArgParser argParser;

  @Before
  public void setUp() throws Exception {
    this.argParser = new ArgParser();
  }

  @Test
  public void parse() {
    assertNotNull(this.argParser.parse(
        new String[]{"--email", "--email-template", "src/main/java/static_files/emailtemplate.txt",
            "--output-dir", "src/output", "--csv-file",
            "src/main/java/static_files/insurancecompanymembers.csv"}));
  }

  @Test
  public void hashcode() {
    assertTrue(this.argParser.hashcode() == 17);
  }

  @Test
  public void testEquals() {
    assertTrue(this.argParser.equals(new ArgParser()));
  }

  @Test
  public void testToString() {
    assertTrue(this.argParser.toString().equals("ArgParser"));
  }
}