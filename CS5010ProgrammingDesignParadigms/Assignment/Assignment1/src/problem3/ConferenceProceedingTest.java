package problem3;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests ConferenceProceeding.
 */
public class ConferenceProceedingTest {

  private ConferenceProceeding conferenceProceeding;

  @Before
  public void setUp() throws Exception {
    List<String> authors = new ArrayList<>();
    authors.add("author");
    this.conferenceProceeding = new ConferenceProceeding("conferenceProceeding", authors, 2019, 100,
        "conferenceName", "conferenceLocation");
  }

  @Test
  public void getConferenceName() {
    assertTrue(this.conferenceProceeding.getConferenceName().equals("conferenceName"));
  }

  @Test
  public void setConferenceName() {
    this.conferenceProceeding.setConferenceName("conferenceName2");
    assertTrue(this.conferenceProceeding.getConferenceName().equals("conferenceName2"));
  }

  @Test
  public void getConferenceLocation() {
    assertTrue(this.conferenceProceeding.getConferenceLocation().equals("conferenceLocation"));
  }

  @Test
  public void setConferenceLocation() {
    this.conferenceProceeding.setConferenceName("conferenceLocation2");
    assertTrue(this.conferenceProceeding.getConferenceName().equals("conferenceLocation2"));
  }
}