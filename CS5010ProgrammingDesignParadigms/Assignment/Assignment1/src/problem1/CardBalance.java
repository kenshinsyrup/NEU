package problem1;

/**
 * Represents the balance of a transit card.
 */
public class CardBalance {

  private int dollars;
  private int cents;

  /**
   * Builds a instance of CardBalance with its detail information.
   *
   * @param dollars is given dollars number.
   * @param cents   is given cents number.
   */
  public CardBalance(int dollars, int cents) {
    this.dollars = dollars;
    this.cents = cents;
  }

  /**
   * Deposits given dollars and cents to balance.
   *
   * @param dollars is deposit dollars number.
   * @param cents   is deposit cents number.
   * @return true if deposits successfully.
   */
  public boolean depositToBalance(int dollars, int cents) {
    // total cents and dollars
    int totalCents = cents + this.cents;
    int totalDollars = dollars + this.dollars + (totalCents / 100);
    totalCents = totalCents % 100;

    // update balance cents and dollars
    this.cents = totalCents;
    this.dollars = totalDollars;

    return true;
  }

  /**
   * Gets the balance.
   *
   * @return balance of card.
   */
  public double getBalance() {
    return this.dollars + (this.cents / 100.0);
  }

}
