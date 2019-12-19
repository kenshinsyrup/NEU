package problem3;

import java.util.List;

/**
 * Represents a text book.
 */
public class Textbook extends Book {

  /**
   * Inits a text book.
   *
   * @param title             is given title.
   * @param authors           is given authors.
   * @param publishingYear    is given publishing year.
   * @param numberOfCitations is given number of citations.
   * @param publishingCompany is given publishing company.
   * @param numberOfPages     is given number of pages.
   */
  public Textbook(String title, List<String> authors, Integer publishingYear,
      Integer numberOfCitations, String publishingCompany, Integer numberOfPages) {
    setTitle(title);
    setAuthors(authors);
    setPublishingYear(publishingYear);
    setNumberOfCitations(numberOfCitations);
    setPublishingCompany(publishingCompany);
    setNumberOfPages(numberOfPages);
  }
}
