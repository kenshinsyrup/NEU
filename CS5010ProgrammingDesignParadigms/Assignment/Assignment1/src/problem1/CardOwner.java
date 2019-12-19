package problem1;

/**
 * Represents a owner of transit card.
 */
public class CardOwner {

  private String firstName;
  private String lastName;
  private String address;
  private String emailAddress;

  /**
   * Builds a instance of CardOwner with its detail information.
   *
   * @param firstName    is given first name.
   * @param lastName     is given last name.
   * @param address      is given address.
   * @param emailAddress is given email address.
   */
  public CardOwner(String firstName, String lastName, String address, String emailAddress) {
    if (!(isValidStr(firstName) && isValidStr(lastName) && isValidStr(address)
        && isValidStr(emailAddress))) {
      return;
    }

    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.emailAddress = emailAddress;
  }

  /**
   * Gets the first name of card owner.
   *
   * @return
   */
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of card owner.
   *
   * @return
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets last name.
   *
   * @param lastName is given last name.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets address.
   *
   * @return
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets address.
   *
   * @param address is given address.
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Gets email address.
   *
   * @return
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * Sets email address.
   *
   * @param emailAddress is given email address.
   */
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  // Checks if the given str is null or length is 0.
  private boolean isValidStr(String str) {
    return !(str == null || str.length() == 0);
  }

}
