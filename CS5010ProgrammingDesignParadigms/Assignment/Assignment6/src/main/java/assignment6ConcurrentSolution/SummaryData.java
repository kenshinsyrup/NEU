package assignment6ConcurrentSolution;

public class SummaryData {

  /*
   * relative_clicks: (current date total_clicks) / (daily average number)
   * daily average number: (all total_clicks of current course) / (active period)
   * active period: (course length) - (first date of click recorded)
   * */

  // columns in summary file
  private String date;
  private String normalizedDate;
  private String totalClicks;
  private String relativeClicks;
  private String assessmentType;

  private String courseId; // code_module + code_presentation
  private String courseLength; // course length in courses.csv
  private String firstClickDate; // first date of click record
  private String assessmentDate; // need assessment date

  public SummaryData() {
    this.totalClicks = "0";
    this.assessmentType = "";
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDate() {
    return date;
  }

  public void setNormalizedDate(String normalizedDate) {
    this.normalizedDate = normalizedDate;
  }

  public void setTotalClicks(String totalClicks) {
    this.totalClicks = totalClicks;
  }

  public String getTotalClicks() {
    return totalClicks;
  }

  public void setRelativeClicks(String relativeClicks) {
    this.relativeClicks = relativeClicks;
  }

  public void setAssessmentType(String assessmentType) {
    this.assessmentType = assessmentType;
  }

  public String getAssessmentType() {
    return assessmentType;
  }

  public void setFirstClickDate(String firstClickDate) {
    this.firstClickDate = firstClickDate;
  }

  public void setAssessmentDate(String assessmentDate) {
    this.assessmentDate = assessmentDate;
  }

  public void setCourseLength(String courseLength) {
    this.courseLength = courseLength;
  }

  public String getCourseLength() {
    return courseLength;
  }
}
