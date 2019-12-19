package assignment6ConcurrentSolution.data;

public class Course implements IData {

  private String courseId;
  private String courseLength;

  public Course(String courseId, String courseLength) {
    this.courseId = courseId;
    this.courseLength = courseLength;
  }

  public String getCourseId() {
    return courseId;
  }

  public String getCourseLength() {
    return courseLength;
  }
}
