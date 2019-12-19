package lab2.assignment1;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RunnerTest {

  private Runner runner;

  @Before
  public void setUp() throws Exception {
    this.runner = new Runner(new Name("firstName", "lastName"), 170.0, 100.0, "leagur", 1.0, 1.0,
        "event");
  }

  @Test
  public void getBest5KTIme() {
    assertTrue(this.runner.getBest5KTIme() == 1.0);
  }

  @Test
  public void setBest5KTIme() {
    this.runner.setBest5KTIme(2.0);
    assertTrue(this.runner.getBest5KTIme() == 2.0);
  }

  @Test
  public void getBestHalfMarathonTime() {
    assertTrue(this.runner.getBestHalfMarathonTime() == 1.0);
  }

  @Test
  public void setBestHalfMarathonTime() {
    this.runner.setBestHalfMarathonTime(2.0);
    assertTrue(this.runner.getBestHalfMarathonTime() == 2.0);
  }

  @Test
  public void getFavoriteRunningEvent() {
    assertTrue(this.runner.getFavoriteRunningEvent().equals("event"));
  }

  @Test
  public void setFavoriteRunningEvent() {
    this.runner.setFavoriteRunningEvent("event1");
    assertTrue(this.runner.getFavoriteRunningEvent().equals("event1"));
  }
}