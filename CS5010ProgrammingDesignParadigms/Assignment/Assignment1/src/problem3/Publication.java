package problem3;

import java.util.List;

/**
 * Represents a publication.
 */
public abstract class Publication {

  private final Integer CURRENT_YEAR = 2019;
  private final Integer MOST_AGE = 250;
  private final Integer NEWER_AGE = 3;

  private String title;
  private List<String> authors;
  private Integer publishingYear;
  private Integer numberOfCitations;

  public abstract Double estimateImpact();

  /**
   * Gets the base impact of publication.
   *
   * @return
   */
  public Double baseImpact() {
    int ages = 1;
    try {
      ages = this.CURRENT_YEAR - this.publishingYear + 1;
      if (ages > this.MOST_AGE) {
        throw new ImpactEstimationException(
            "Meaningless estimation of impact for publications older than 250 years.");
      }
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }

    return (this.numberOfCitations * 1.0) / ages;
  }

  /**
   * Check if the publication is newer.
   *
   * @return
   */
  public boolean isNewerPublication() {
    return this.CURRENT_YEAR - this.publishingYear + 1 < this.NEWER_AGE;
  }

  /**
   * Gets title.
   *
   * @return
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets title.
   *
   * @param title is given title.
   */
  public void setTitle(String title) {
    if (isValidTitle(title)) {
      this.title = title;
    }
  }

  /**
   * Gets authors.
   *
   * @return
   */
  public List<String> getAuthors() {
    return authors;
  }

  /**
   * Sets authors.
   *
   * @param authors is given authors.
   */
  public void setAuthors(List<String> authors) {
    if (isValidAuthors(authors)) {
      this.authors = authors;
    }
  }

  /**
   * Gets publishingYear.
   *
   * @return
   */
  public Integer getPublishingYear() {
    return publishingYear;
  }

  /**
   * Sets publishingYear.
   *
   * @param publishingYear is given publishingYear.
   */
  public void setPublishingYear(Integer publishingYear) {
    if (isValidPublishingYear(publishingYear)) {
      this.publishingYear = publishingYear;
    }
  }

  /**
   * Gets numberOfCitations.
   *
   * @return
   */
  public Integer getNumberOfCitations() {
    return numberOfCitations;
  }

  /**
   * Sets numberOfCitations.
   *
   * @param numberOfCitations is given numberOfCitations.
   */
  public void setNumberOfCitations(Integer numberOfCitations) {
    this.numberOfCitations = numberOfCitations;
  }

  // Checks if given title is valid.
  private boolean isValidTitle(String title) {
    return !(title == null || title.length() == 0);
  }

  // Checks if given authors is valid.
  private boolean isValidAuthors(List<String> authors) {
    return !(authors == null || authors.size() == 0);
  }

  // Checks if given publishingYear is valid.
  private boolean isValidPublishingYear(Integer publishingYear) {
    return !(publishingYear < 0 || publishingYear > this.CURRENT_YEAR);
  }
}
