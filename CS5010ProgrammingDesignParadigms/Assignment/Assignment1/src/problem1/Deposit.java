package problem1;

/**
 * Represents deposit operation to a transit card.
 */
public class Deposit {

  // final variables for restrictions.
  private final int LEAST_DOLLARS = 5;
  private final int MOST_DOLLARS = 50;
  private final int LEAST_CENTS = 0;
  private final int MOST_CENTS = 99;

  private String firstName;
  private String lastName;
  private int dollars;
  private int cents;

  /**
   * Inits a deposit operation.
   *
   * @param firstName is given first name.
   * @param lastName  is given last name.
   * @param dollars   is given dollars number.
   * @param cents     is given cents number.
   */
  public Deposit(String firstName, String lastName, int dollars, int cents) {
    if (!isValidDepositNum(dollars, cents)) {
      return;
    }

    this.firstName = firstName;
    this.lastName = lastName;
    this.dollars = dollars;
    this.cents = cents;
  }

  /**
   * Gets the first name of user who wants to do this deposit.
   *
   * @return
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Gets the last name of user who wants to do this deposit.
   *
   * @return
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Gets the dollars of this deposit.
   *
   * @return
   */
  public int getDollars() {
    return dollars;
  }

  /**
   * Gets the cents of this deposit.
   *
   * @return
   */
  public int getCents() {
    return cents;
  }

  /**
   * Checks the deposit number is valid.
   *
   * @param dollars is given dollars number.
   * @param cents   is given cents number.
   * @return
   */
  private boolean isValidDepositNum(int dollars, int cents) {
    return !(dollars < LEAST_DOLLARS || dollars > MOST_DOLLARS || cents < LEAST_CENTS
        || cents > MOST_CENTS);
  }
}
