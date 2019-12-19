package problem1;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests CardOwner.
 */
public class CardOwnerTest {

  private CardOwner cardOwner;

  @Before
  public void setUp() throws Exception {
    this.cardOwner = new CardOwner("Jane", "Doe", "address", "email@address");
  }

  @Test
  public void getFirstName() {
    assertTrue(this.cardOwner.getFirstName().equals("Jane"));
  }

  @Test
  public void setFirstName() {
    this.cardOwner.setFirstName("Jane2");
    assertTrue(this.cardOwner.getFirstName().equals("Jane2"));
  }

  @Test
  public void getLastName() {
    assertTrue(this.cardOwner.getLastName().equals("Doe"));
  }

  @Test
  public void setLastName() {
    this.cardOwner.setLastName("Doe2");
    assertTrue(this.cardOwner.getLastName().equals("Doe2"));
  }

  @Test
  public void getAddress() {
    assertTrue(this.cardOwner.getAddress().equals("address"));
  }

  @Test
  public void setAddress() {
    this.cardOwner.setAddress("address2");
    assertTrue(this.cardOwner.getAddress().equals("address2"));
  }

  @Test
  public void getEmailAddress() {
    assertTrue(this.cardOwner.getEmailAddress().equals("email@address"));
  }

  @Test
  public void setEmailAddress() {
    this.cardOwner.setEmailAddress("email@address2");
    assertTrue(this.cardOwner.getEmailAddress().equals("email@address2"));
  }
}