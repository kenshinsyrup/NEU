package assignment6ConcurrentSolution.data;

public class Assessment implements IData {

  private String courseId;
  private String assessmentType;
  private String date;

  public Assessment(String courseId, String assessmentType, String date) {
    this.courseId = courseId;
    this.assessmentType = assessmentType;
    this.date = date;
  }

  public String getCourseId() {
    return courseId;
  }

  public String getAssessmentType() {
    return assessmentType;
  }

  public String getDate() {
    return date;
  }
}
