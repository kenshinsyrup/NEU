package problem1;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests CardBalance.
 */
public class CardBalanceTest {

  private CardBalance cardBalance;

  @Before
  public void setUp() throws Exception {
    cardBalance = new CardBalance(0, 0);
  }

  @Test
  public void depositToBalance() {
    assertTrue(this.cardBalance.depositToBalance(5, 5));
  }

  @Test
  public void getBalance() {
    assertTrue(this.cardBalance.depositToBalance(5, 5));
    assertTrue(this.cardBalance.getBalance() == 5.05);
  }
}