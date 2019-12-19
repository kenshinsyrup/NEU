package problem1;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests Deposit.
 */
public class DepositTest {

  private Deposit deposit;

  @Before
  public void setUp() throws Exception {
    this.deposit = new Deposit("Jane", "Doe", 5, 5);
  }

  @Test
  public void getFirstName() {
    assertTrue(this.deposit.getFirstName().equals("Jane"));
  }

  @Test
  public void getLastName() {
    assertTrue(this.deposit.getLastName().equals("Doe"));
  }

  @Test
  public void getDollars() {
    assertTrue(this.deposit.getDollars() == 5);
  }

  @Test
  public void getCents() {
    assertTrue(this.deposit.getCents() == 5);
  }
}