package problem3;

import java.util.List;

/**
 * Represents a journal.
 */
public class Journal extends Article {

  private String publisher;
  private List<String> editors;

  /**
   * Inits a journal.
   *
   * @param title             is given title.
   * @param authors           is given authors.
   * @param publishingYear    is given publishing year.
   * @param numberOfCitations is given number of citations.
   * @param publisher         is given publisher.
   * @param editors           is given editors.
   */
  public Journal(String title, List<String> authors, Integer publishingYear,
      Integer numberOfCitations, String publisher, List<String> editors) {
    setTitle(title);
    setAuthors(authors);
    setPublishingYear(publishingYear);
    setNumberOfCitations(numberOfCitations);
    setPublisher(publisher);
    setEditors(editors);
  }

  /**
   * Gets estimate impact of journal.
   *
   * @return
   */
  @Override
  public Double estimateImpact() {
    return baseImpact() * 2.0 + (isNewerPublication() ? 100 : 0);
  }

  /**
   * Gets publisher.
   *
   * @return
   */
  public String getPublisher() {
    return publisher;
  }

  /**
   * Sets publisher.
   *
   * @param publisher is given publisher.
   */
  public void setPublisher(String publisher) {
    if (isValidPublisher(publisher)) {
      this.publisher = publisher;
    }
  }

  /**
   * Gets editors.
   *
   * @return
   */
  public List<String> getEditors() {
    return editors;
  }

  /**
   * Sets editors.
   *
   * @param editors is given editors.
   */
  public void setEditors(List<String> editors) {
    if (isValidEditors(editors)) {
      this.editors = editors;
    }
  }

  // Checks if given publisher is valid.
  private boolean isValidPublisher(String publisher) {
    return !(publisher == null || publisher.length() == 0);
  }

  // Checks if given editors is valid.
  private boolean isValidEditors(List<String> editors) {
    return !(editors == null || editors.size() == 0);
  }

}
