package assignment6ConcurrentSolution.consumer;

import assignment6ConcurrentSolution.SummaryData;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * Represents a Consumer.
 */
public class Consumer implements Runnable {

  private final static String EXIT = "EXIT";
  private final String STUDENT_VLE = "studentVle";
  private final String COURSE = "course";

  private BlockingQueue<String[]> dataBQ;
  private ConcurrentMap<String, SummaryData> courseSummaryMap;

  /**
   * Constructs a Consumer.
   *
   * @param dataBQ           is given dataBQ.
   * @param courseSummaryMap is given courseSummaryMap.
   */
  public Consumer(BlockingQueue<String[]> dataBQ,
      ConcurrentMap<String, SummaryData> courseSummaryMap) {
    this.dataBQ = dataBQ;
    this.courseSummaryMap = courseSummaryMap;
  }

  @Override
  public void run() {

    while (true) {
      // run until receive EXIT signal
      String[] data = null;
      try {
        data = this.dataBQ.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
        return;
      }

      String dataType = data[0].trim();
      // if receive exit IDATA from BQ, end consuming
      if (dataType.equals(EXIT)) {
        break;
      }

      String courseId = data[1].trim();
      if (courseId.length() == 0) {
        continue;
      }

      SummaryData summaryData = this.courseSummaryMap
          .getOrDefault(courseId, new SummaryData(courseId));

      // !!! sync summaryData to avoid race when set value ( race affects the activity file seriously)
      synchronized (summaryData) {
        if (dataType.equals(COURSE)) {
          String courseLen = data[2].trim();
          if (courseLen.length() == 0) {
            continue;
          }
          summaryData.setCourseLength(courseLen);
        } else if (dataType.equals(STUDENT_VLE)) { // StudentVle
          String sumClick = data[2].trim();
          String date = data[3].trim();
          if (date.length() == 0 || sumClick.length() == 0) {
            continue;
          }

          // update summary data
          Map<String, String> dateTotalclickMap = summaryData.getDateTotalclickMap();
          int totalClick = Integer.parseInt(dateTotalclickMap.getOrDefault(date, "0"));
          totalClick += Integer.parseInt(sumClick);
          dateTotalclickMap.put(date, String.valueOf(totalClick));
          summaryData.setDateTotalclickMap(dateTotalclickMap);
        } else { // Assessment
          String assessmentDate = data[2].trim();
          if (assessmentDate.length() == 0) {
            continue;
          }
          String assessmentType = data[3].trim();

          // update summary data
          Map<String, String> dateAssessmentMap = summaryData.getDateAssessmentMap();
          dateAssessmentMap.put(assessmentDate, assessmentType);
          summaryData.setDateAssessmentMap(dateAssessmentMap);
        }

      }

      this.courseSummaryMap.put(courseId, summaryData);

    }
  }

}
