package problem3;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests Book.
 */
public class BookTest {

  private Book textbook;

  @Before
  public void setUp() throws Exception {
    List<String> authors = new ArrayList<>();
    authors.add("author");

    this.textbook = new Textbook("textbook", authors, 2019, 100, "company", 100);
  }

  @Test
  public void getPublishingCompany() {
    assertTrue(this.textbook.getPublishingCompany().equals("company"));
  }

  @Test
  public void setPublishingCompany() {
    this.textbook.setPublishingCompany("company1");
    assertTrue(this.textbook.getPublishingCompany().equals("company1"));
  }

  @Test
  public void getNumberOfPages() {
    assertTrue(this.textbook.getNumberOfPages().equals(100));
  }

  @Test
  public void setNumberOfPages() {
    this.textbook.setNumberOfPages(300);
    assertTrue(this.textbook.getNumberOfPages().equals(300));
  }

}