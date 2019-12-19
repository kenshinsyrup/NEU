package assignment4;

/**
 * Represents a seat.
 */
public class Seat {

  // Seat unchangeable attributes.
  private String id;
  private boolean isWheelchairAccessible;

  private Attendee user;
  private boolean isReserved;
  private boolean isPrinted;

  /**
   * Inits a seat.
   *
   * @param id                     is given id.
   * @param isWheelchairAccessible is wheel accessible.
   */
  public Seat(String id, boolean isWheelchairAccessible) {
    this.id = id;
    this.isWheelchairAccessible = isWheelchairAccessible;
  }

  /**
   * Assigns seat to user.
   *
   * @param user is attendee.
   * @return boolean.
   */
  public boolean assignSeat(Attendee user) {
    if (user.isNeedWheelAccessible() && !this.isWheelchairAccessible) {
      return false;
    }

    if (this.isReserved || this.isPrinted) {
      return false;
    }

    this.user = user;
    user.setSeat(this);
    this.isReserved = true;
    return true;
  }

  /**
   * Gets id.
   *
   * @return id.
   */
  public String getId() {
    return id;
  }

  /**
   * Gets user.
   *
   * @return attendee.
   */
  public Attendee getUser() {
    return user;
  }

  /**
   * Sets user.
   *
   * @param user is given attendee.
   */
  public void setUser(Attendee user) {
    this.user = user;
  }

  /**
   * Gets reserved status.
   *
   * @return status.
   */
  public boolean isReserved() {
    return isReserved;
  }

  /**
   * Sets reserved.
   *
   * @param reserved is given reserved status.
   */
  public void setReserved(boolean reserved) {
    isReserved = reserved;
  }

  /**
   * Gets printed status.
   *
   * @return status.
   */
  public boolean isPrinted() {
    return isPrinted;
  }

  /**
   * Sets printed status.
   *
   * @param printed is status.
   */
  public void setPrinted(boolean printed) {
    isPrinted = printed;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
