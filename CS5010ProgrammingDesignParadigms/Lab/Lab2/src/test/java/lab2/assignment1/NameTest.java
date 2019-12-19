package lab2.assignment1;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class NameTest {

  private Name name;

  @Before
  public void setUp() throws Exception {
    this.name = new Name("firstName", "lastName");
  }

  @Test
  public void getFirstName() {
    assertTrue(this.name.getFirstName().equals("firstName"));
  }

  @Test
  public void setFirstName() {
    this.name.setFirstName("firstName1");
    assertTrue(this.name.getFirstName().equals("firstName1"));
  }

  @Test
  public void getLastName() {
    assertTrue(this.name.getLastName().equals("lastName"));
  }

  @Test
  public void setLastName() {
    this.name.setLastName("lastName1");
    assertTrue(this.name.getLastName().equals("lastName1"));
  }
}