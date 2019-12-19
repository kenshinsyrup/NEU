package problem1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests TransitCard.
 */
public class TransitCardTest {

  private TransitCard transitCard;

  @org.junit.Before
  public void setUp() throws Exception {
    this.transitCard = new TransitCard("Jane", "Doe", "address", "email@address");
    assertNotNull(this.transitCard);
  }

  @org.junit.Test
  public void depositMoney() {
    Deposit deposit = new Deposit("Jane", "Doe", 5, 5);
    assertNotNull(deposit);

    assertEquals(true, this.transitCard.depositMoney(deposit));
  }

  @Test
  public void getBalance() {
    Deposit deposit = new Deposit("Jane", "Doe", 5, 5);
    assertNotNull(deposit);

    assertEquals(true, this.transitCard.depositMoney(deposit));

    assertTrue(this.transitCard.getBalance() == 5.05);
  }
}