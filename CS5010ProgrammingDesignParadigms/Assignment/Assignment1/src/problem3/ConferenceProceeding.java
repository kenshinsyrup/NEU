package problem3;

import java.util.List;

/**
 * Represents a conference proceeding.
 */
public class ConferenceProceeding extends Article {

  private String conferenceName;
  private String conferenceLocation;

  /**
   * Inits a conference proceeding.
   *
   * @param title              is given title.
   * @param authors            is given authors.
   * @param publishingYear     is given publishing year.
   * @param numberOfCitations  is given number of citations.
   * @param conferenceName     is given conference name.
   * @param conferenceLocation is given conference location.
   */
  public ConferenceProceeding(String title, List<String> authors, Integer publishingYear,
      Integer numberOfCitations, String conferenceName, String conferenceLocation) {
    setTitle(title);
    setAuthors(authors);
    setPublishingYear(publishingYear);
    setNumberOfCitations(numberOfCitations);
    setConferenceName(conferenceName);
    setConferenceLocation(conferenceLocation);
  }

  /**
   * Gets estimate impact of a conference proceeding.
   *
   * @return
   */
  @Override
  public Double estimateImpact() {
    return baseImpact() + (isNewerPublication() ? 100 : 0);
  }

  /**
   * Gets conferenceName.
   *
   * @return
   */
  public String getConferenceName() {
    return conferenceName;
  }

  /**
   * Sets conferenceName.
   *
   * @param conferenceName is given conferenceName.
   */
  public void setConferenceName(String conferenceName) {
    if (isValidString(conferenceName)) {
      this.conferenceName = conferenceName;
    }
  }

  /**
   * Gets conferenceLocation.
   *
   * @return
   */
  public String getConferenceLocation() {
    return conferenceLocation;
  }

  /**
   * Sets conferenceLocation.
   *
   * @param conferenceLocation is given conferenceLocation.
   */
  public void setConferenceLocation(String conferenceLocation) {
    if (isValidString(conferenceLocation)) {
      this.conferenceLocation = conferenceLocation;
    }
  }

  // Checks if given str is valid.
  private boolean isValidString(String str) {
    return !(str == null || str.length() == 0);
  }
}
