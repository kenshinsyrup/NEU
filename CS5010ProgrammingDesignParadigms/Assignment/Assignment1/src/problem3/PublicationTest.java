package problem3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests Publication.
 */
public class PublicationTest {

  private Textbook textBook;
  private Journal journal;

  @Before
  public void setUp() throws Exception {
    List<String> authors = new ArrayList<>();
    authors.add("author");

    this.textBook = new Textbook("textbook", authors, 2019, 100, "company", 100);

    List<String> editors = new ArrayList<>();
    editors.add("editor");

    this.journal = new Journal("journal", authors, 2000, 200, "publisher", editors);
  }

  @Test
  public void estimateImpact() {
    assertTrue(this.textBook.estimateImpact().equals(500.0));

    assertTrue(this.journal.estimateImpact().equals(20.0));
  }

  @Test
  public void baseImpact() {
    assertTrue(this.textBook.baseImpact().equals(100.0));

    assertTrue(this.journal.baseImpact().equals(10.0));
  }

  @Test
  public void isNewerPublication() {
    assertTrue(this.textBook.isNewerPublication());

    assertFalse(this.journal.isNewerPublication());
  }

  @Test
  public void getTitle() {
    assertEquals("textbook", this.textBook.getTitle());

    assertEquals("journal", this.journal.getTitle());
  }

  @Test
  public void setTitle() {
    this.textBook.setTitle("textbook2");
    assertEquals("textbook2", this.textBook.getTitle());

    this.journal.setTitle("journal2");
    assertEquals("journal2", this.journal.getTitle());
  }

  @Test
  public void getAuthors() {
    List<String> authors = new ArrayList<>();
    authors.add("author");

    assertEquals(authors, this.textBook.getAuthors());

    assertEquals(authors, this.journal.getAuthors());
  }

  @Test
  public void setAuthors() {
    List<String> authors = new ArrayList<>();
    authors.add("author2");

    this.textBook.setAuthors(authors);
    assertEquals(authors, this.textBook.getAuthors());

    this.journal.setAuthors(authors);
    assertEquals(authors, this.journal.getAuthors());
  }

  @Test
  public void getPublishingYear() {
    assertTrue(this.textBook.getPublishingYear().equals(2019));

    assertTrue(this.journal.getPublishingYear().equals(2000));
  }

  @Test
  public void setPublishingYear() {
    this.textBook.setPublishingYear(2018);
    assertTrue(this.textBook.getPublishingYear().equals(2018));

    this.journal.setPublishingYear(2009);
    assertTrue(this.journal.getPublishingYear().equals(2009));
  }

  @Test
  public void getNumberOfCitations() {
    assertTrue(this.textBook.getNumberOfCitations().equals(100));

    assertTrue(this.journal.getNumberOfCitations().equals(200));
  }

  @Test
  public void setNumberOfCitations() {
    this.textBook.setNumberOfCitations(300);
    assertTrue(this.textBook.getNumberOfCitations().equals(300));

    this.journal.setNumberOfCitations(400);
    assertTrue(this.journal.getNumberOfCitations().equals(400));
  }
}