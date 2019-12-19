package assignment6ConcurrentSolution.data;

public class StudentVle implements IData {

  private String courseId;
  private String date;
  private String sumClick;

  public StudentVle(String courseId, String sumClick, String date) {
    this.courseId = courseId;
    this.date = date;
    this.sumClick = sumClick;
  }

  public String getCourseId() {
    return courseId;
  }

  public String getDate() {
    return date;
  }

  public String getSumClick() {
    return sumClick;
  }
}
