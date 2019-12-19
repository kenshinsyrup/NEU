package problem1;

/**
 * Represents a Transit Card.
 */
public class TransitCard {

  // the owner of this card.
  private CardOwner owner;

  // the balance of this card.
  private CardBalance balance;

  /**
   * Inits a transit card.
   *
   * @param firstName    is given first name.
   * @param lastName     is given last name.
   * @param address      is given address.
   * @param emailAddress is given email address.
   */
  public TransitCard(String firstName, String lastName, String address, String emailAddress) {
    // init card owner
    CardOwner owner = new CardOwner(firstName, lastName, address, emailAddress);
    if (owner == null) {
      return;
    }
    this.owner = owner;

    // init card balance
    this.balance = new CardBalance(0, 0);
  }

  /**
   * Deposits given newDeposit to transit card.
   *
   * @param newDeposit is given deposit.
   * @return true if deposits to transit card successfully.
   */
  public boolean depositMoney(Deposit newDeposit) {
    // check user valid or not
    if (!isValidOwner(newDeposit.getFirstName(), newDeposit.getLastName())) {
      return false;
    }

    return this.balance.depositToBalance(newDeposit.getDollars(), newDeposit.getCents());

  }

  /**
   * Gets the balance of transit card.
   *
   * @return
   */
  public double getBalance() {
    return this.balance.getBalance();
  }

  // Checks if given firstName and lastName equals to card owner's.
  private boolean isValidOwner(String firstName, String lastName) {
    return this.owner.getFirstName().equals(firstName) && this.owner.getLastName().equals(lastName);
  }

}
