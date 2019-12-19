package assignment6ConcurrentSolution;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents summary of a course for output.
 */
public class SummaryData {

  // summary file for a course
  private String courseId; // code_module + code_presentation
  private String courseLength; // course length in courses.csv
  private Map<String, String> dateTotalclickMap;
  private Map<String, String> dateAssessmentMap;

  /**
   * Constructs a Summary.
   *
   * @param courseId is course ID.
   */
  public SummaryData(String courseId) {
    this.courseId = courseId;
    this.dateTotalclickMap = new HashMap<>();
    this.dateAssessmentMap = new HashMap<>();
  }

  /**
   * Gets course ID.
   *
   * @return course ID.
   */
  public String getCourseId() {
    return courseId;
  }

  /**
   * Gets course length.
   *
   * @return course length.
   */
  public String getCourseLength() {
    return courseLength;
  }

  /**
   * Gets dateTotalclickMap.
   *
   * @return dateTotalclickMap.
   */
  public Map<String, String> getDateTotalclickMap() {
    return dateTotalclickMap;
  }

  /**
   * Gets dateAssessmentMap.
   *
   * @return dateAssessmentMap.
   */
  public Map<String, String> getDateAssessmentMap() {
    return dateAssessmentMap;
  }

  /**
   * Sets course ID.
   *
   * @param courseId is given course ID.
   */
  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  /**
   * Sets course length.
   *
   * @param courseLength is given course length.
   */
  public void setCourseLength(String courseLength) {
    this.courseLength = courseLength;
  }

  /**
   * Sets dateTotalclickMap.
   *
   * @param dateTotalclickMap is given dateTotalclickMap.
   */
  public void setDateTotalclickMap(Map<String, String> dateTotalclickMap) {
    this.dateTotalclickMap = dateTotalclickMap;
  }

  /**
   * Sets dateAssessmentMap.
   *
   * @param dateAssessmentMap is given dateAssessmentMap.
   */
  public void setDateAssessmentMap(Map<String, String> dateAssessmentMap) {
    this.dateAssessmentMap = dateAssessmentMap;
  }
}
