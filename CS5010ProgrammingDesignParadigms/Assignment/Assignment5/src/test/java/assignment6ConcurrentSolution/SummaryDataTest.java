package assignment6ConcurrentSolution;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class SummaryDataTest {

  private SummaryData summaryData;
  private String courseId;

  @Before
  public void setUp() {
    this.courseId = "courseId";
    this.summaryData = new SummaryData(this.courseId);
  }

  @Test
  public void getCourseId() {
    assertEquals(this.courseId, this.summaryData.getCourseId());
  }

  @Test
  public void getCourseLength() {
    this.summaryData.setCourseLength("testLength");
    assertEquals("testLength", this.summaryData.getCourseLength());
  }

  @Test
  public void getDateTotalclickMap() {
    assertEquals(0, this.summaryData.getDateTotalclickMap().size());
  }

  @Test
  public void getDateAssessmentMap() {
    assertEquals(0, this.summaryData.getDateAssessmentMap().size());
  }

  @Test
  public void setCourseId() {
    this.summaryData.setCourseId("2");
    assertEquals("2", this.summaryData.getCourseId());
  }

  @Test
  public void setCourseLength() {
    this.summaryData.setCourseLength("2");
    assertEquals("2", this.summaryData.getCourseLength());
  }

  @Test
  public void setDateTotalclickMap() {
    Map<String, String> testMap = new HashMap<>();
    testMap.put("1", "1");
    this.summaryData.setDateTotalclickMap(testMap);
    assertEquals(testMap, this.summaryData.getDateTotalclickMap());
  }

  @Test
  public void setDateAssessmentMap() {
    Map<String, String> testMap = new HashMap<>();
    testMap.put("1", "1");
    this.summaryData.setDateAssessmentMap(testMap);
    assertEquals(testMap, this.summaryData.getDateAssessmentMap());
  }
}