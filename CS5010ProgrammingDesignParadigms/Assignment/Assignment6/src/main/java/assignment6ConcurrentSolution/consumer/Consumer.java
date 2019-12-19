package assignment6ConcurrentSolution.consumer;

import assignment6ConcurrentSolution.SummaryData;
import assignment6ConcurrentSolution.data.Assessment;
import assignment6ConcurrentSolution.data.Course;
import assignment6ConcurrentSolution.data.ExitData;
import assignment6ConcurrentSolution.data.IData;
import assignment6ConcurrentSolution.data.StudentVle;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

public class Consumer implements Runnable {

  private BlockingQueue<IData> dataBQ;
  private ConcurrentMap<String, Map<String, SummaryData>> courseDateSummaryMap;
  private ConcurrentMap<String, Course> idCourseMap;

  public Consumer(BlockingQueue<IData> dataBQ,
      ConcurrentMap<String, Map<String, SummaryData>> courseDateSummaryMap, ConcurrentMap<String, Course> idCourseMap) {
    this.dataBQ = dataBQ;
    this.courseDateSummaryMap = courseDateSummaryMap;
    this.idCourseMap = idCourseMap;
  }

  @Override
  public void run() {

    // run until receive EXIT signal
    while (true) {
      IData data = null;
      try {
        data = this.dataBQ.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      // if receive exit IDATA from BQ, end consuming
      if (data instanceof ExitData) {
        break;
      }

      if (data instanceof Course) {
        Course course = (Course) data;
        String courseId = course.getCourseId();
        System.out.println("Consume course: " + courseId + " len: "+ course.getCourseLength());
        this.idCourseMap.put(courseId, course);
      } else if (data instanceof StudentVle) { // StudentVle
        StudentVle studentVle = (StudentVle) data;
        String courseId = studentVle.getCourseId();
        String date = studentVle.getDate();

        System.out.println("Consume studentVle: " + courseId);

        // update summary data
        Map<String, SummaryData> dateSummaryMap = this.courseDateSummaryMap
            .getOrDefault(courseId, new HashMap<>());
        SummaryData summaryData = dateSummaryMap.getOrDefault(date, new SummaryData());
        summaryData.setCourseId(courseId);
        summaryData.setDate(date);
        summaryData.setTotalClicks(String.valueOf(
            Integer.parseInt(studentVle.getSumClick()) + Integer
                .parseInt(summaryData.getTotalClicks())));

        dateSummaryMap.put(date, summaryData);
        this.courseDateSummaryMap.put(courseId, dateSummaryMap);

        System.out.println(courseId+ " check date:"+ this.courseDateSummaryMap.get(courseId).get(date).getDate());
      } else { // Assessment
        Assessment assessment = (Assessment) data;
        String courseId = assessment.getCourseId();
        String assessmentDate = assessment.getDate();

        System.out.println("Consume assessment: " + courseId);

        // update summary data
        Map<String, SummaryData> dateSummaryMap = this.courseDateSummaryMap
            .getOrDefault(courseId, new HashMap<>());
        SummaryData summaryData = dateSummaryMap.getOrDefault(assessmentDate, new SummaryData());
        summaryData.setCourseId(courseId);
        summaryData.setAssessmentDate(assessmentDate);
        summaryData.setAssessmentType(assessment.getAssessmentType());

        dateSummaryMap.put(assessmentDate, summaryData);
        this.courseDateSummaryMap.put(courseId, dateSummaryMap);
      }
    }

  }

}
