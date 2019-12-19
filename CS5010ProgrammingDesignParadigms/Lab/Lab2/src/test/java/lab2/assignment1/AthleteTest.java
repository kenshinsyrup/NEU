package lab2.assignment1;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AthleteTest {

  private Athlete athlete;

  @Before
  public void setUp() throws Exception {
    this.athlete = new Athlete(new Name("firstName", "lastName"), 170.0, 100.0, "league");
  }

  @Test
  public void getAthletesName() {
    Name name = this.athlete.getAthletesName();
    assertTrue(name.getFirstName().equals("firstName"));
    assertTrue(name.getLastName().equals("lastName"));
  }

  @Test
  public void getHeight() {
    assertTrue(this.athlete.getHeight() == 170.0);
  }

  @Test
  public void getWeight() {
    assertTrue(this.athlete.getWeight() == 100.0);
  }

  @Test
  public void getLeague() {
    assertTrue(this.athlete.getLeague().equals("league"));
  }

  @Test
  public void testEquals() {
    Athlete copyAthlete = new Athlete(new Name("firstName", "lastName"), 170.0, 100.0, "league");
    assertTrue(copyAthlete.equals(this.athlete));
  }

  @Test
  public void testHashCode() {
    assertTrue(this.athlete.hashCode() == -1636660217);
  }

  @Test
  public void testToString() {
    assertTrue(this.athlete.toString().equals("firstName lastName"));
  }
}