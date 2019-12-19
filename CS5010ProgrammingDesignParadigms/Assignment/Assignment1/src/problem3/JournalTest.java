package problem3;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests Journal.
 */
public class JournalTest {

  private Journal journal;

  @Before
  public void setUp() throws Exception {
    List<String> authors = new ArrayList<>();
    authors.add("author");
    List<String> editors = new ArrayList<>();
    editors.add("editor");
    this.journal = new Journal("journal", authors, 2000, 200, "publisher", editors);
  }

  @Test
  public void getPublisher() {
    assertTrue(this.journal.getPublisher().equals("publisher"));
  }

  @Test
  public void setPublisher() {
    this.journal.setPublisher("publisher1");
    assertTrue(this.journal.getPublisher().equals("publisher1"));
  }

  @Test
  public void getEditors() {
    List<String> editors = new ArrayList<>();
    editors.add("editor");
    assertTrue(this.journal.getEditors().equals(editors));
  }

  @Test
  public void setEditors() {
    List<String> editors = new ArrayList<>();
    editors.add("editor2");
    this.journal.setEditors(editors);
    assertTrue(this.journal.getEditors().equals(editors));
  }
}