package lab2.assignment1;

/**
 * Represents a name.
 */
public class Name {

  /**
   * Gets first name.
   *
   * @return first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets first name.
   *
   * @param firstName is given first name.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets last name.
   *
   * @return last name.
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

  private String firstName;
  private String lastName;

  /**
   * Represents a Name.
   *
   * @param firstName is first name.
   * @param lastName  is last name.
   */
  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
