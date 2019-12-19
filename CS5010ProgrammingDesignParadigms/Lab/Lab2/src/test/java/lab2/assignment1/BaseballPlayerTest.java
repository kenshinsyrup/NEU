package lab2.assignment1;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BaseballPlayerTest {

  private BaseballPlayer baseballPlayer;

  @Before
  public void setUp() throws Exception {
    this.baseballPlayer = new BaseballPlayer(new Name("firstName", "lastName"), 170.0, 100.0,
        "league", "team", 1.0, 1);
  }

  @Test
  public void getTeam() {
    assertTrue(this.baseballPlayer.getTeam().equals("team"));
  }

  @Test
  public void setTeam() {
    this.baseballPlayer.setTeam("team1");
    assertTrue(this.baseballPlayer.getTeam().equals("team1"));
  }

  @Test
  public void getAverageBatting() {
    assertTrue(this.baseballPlayer.getAverageBatting() == 1.0);
  }

  @Test
  public void setAverageBatting() {
    this.baseballPlayer.setAverageBatting(2.0);
    assertTrue(this.baseballPlayer.getAverageBatting() == 2.0);
  }

  @Test
  public void getSeasonHomeRuns() {
    assertTrue(this.baseballPlayer.getSeasonHomeRuns() == 1);
  }

  @Test
  public void setSeasonHomeRuns() {
    this.baseballPlayer.setSeasonHomeRuns(2);
    assertTrue(this.baseballPlayer.getSeasonHomeRuns() == 2);
  }
}