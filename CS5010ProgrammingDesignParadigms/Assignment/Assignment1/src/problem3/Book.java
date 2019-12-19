package problem3;

/**
 * Represents a book.
 */
public abstract class Book extends Publication {

  private String publishingCompany;
  private Integer numberOfPages;

  /**
   * Gets estimate impact of book.
   *
   * @return
   */
  @Override
  public Double estimateImpact() {
    return baseImpact() * 4.0 + (isNewerPublication() ? 100 : 0);
  }

  /**
   * Gets publishingCompany.
   *
   * @return
   */
  public String getPublishingCompany() {
    return publishingCompany;
  }

  /**
   * Sets publishingCompany.
   *
   * @param publishingCompany is given publishingCompany.
   */
  public void setPublishingCompany(String publishingCompany) {
    if (isValidPublishingCompany(publishingCompany)) {
      this.publishingCompany = publishingCompany;
    }
  }

  /**
   * Gets numberOfPages.
   *
   * @return
   */
  public Integer getNumberOfPages() {
    return numberOfPages;
  }

  /**
   * Sets numberOfPages.
   *
   * @param numberOfPages is given numberOfPages.
   */
  public void setNumberOfPages(Integer numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  // Checks if given publishingCompany is valid.
  private boolean isValidPublishingCompany(String publishingCompany) {
    return !(publishingCompany == null || publishingCompany.length() == 0);
  }
}
