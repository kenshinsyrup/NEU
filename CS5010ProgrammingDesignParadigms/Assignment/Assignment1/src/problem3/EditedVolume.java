package problem3;

import java.util.List;

/**
 * Represents a edited volume.
 */
public class EditedVolume extends Book {

  /**
   * Inits a edited volume.
   *
   * @param title             is given title.
   * @param authors           is given authors.
   * @param publishingYear    is given publishing year.
   * @param numberOfCitations is given number of citations.
   * @param publishingCompany is given publishing company.
   * @param numberOfPages     is given number of pages.
   */
  public EditedVolume(String title, List<String> authors, Integer publishingYear,
      Integer numberOfCitations, String publishingCompany, Integer numberOfPages) {
    setTitle(title);
    setAuthors(authors);
    setPublishingYear(publishingYear);
    setNumberOfCitations(numberOfCitations);
    setPublishingCompany(publishingCompany);
    setNumberOfPages(numberOfPages);
  }
}
