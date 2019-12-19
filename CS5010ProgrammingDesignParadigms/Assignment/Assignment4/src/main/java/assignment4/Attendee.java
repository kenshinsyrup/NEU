package assignment4;

/**
 * Represents a Attendee.
 */
public class Attendee {

  // Attendee unchangeable attributes
  private String id;
  private boolean needWheelAccessible;
  private boolean isGroupLeader;
  private String groupId;
  private Group group;

  private Seat seat;

  /**
   * Inits a Attendee.
   *
   * @param id                  is given id.
   * @param needWheelAccessible is wheel accessible or not.
   * @param isGroupLeader       is group leader or not.
   * @param groupId             is group id.
   * @param group               is group.
   */
  public Attendee(String id, boolean needWheelAccessible, boolean isGroupLeader, String groupId,
      Group group) {
    this.id = id;
    this.needWheelAccessible = needWheelAccessible;
    this.isGroupLeader = isGroupLeader;
    this.groupId = groupId;
    this.group = group;
  }

  /**
   * Notifies the group leader.
   *
   * @param status is current status.
   * @param group  is group.
   */
  public void notifyGroupLeader(String status, Group group) {
    if (group != null) {
      System.out.println(
          "Hi " + group.getLeader().getId() + ". I'm " + this.id + ", my seat status is " + status
              + ".");
    }
  }

  /**
   * Gets group id.
   *
   * @return group id.
   */
  public String getGroupId() {
    return groupId;
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
   * Needs wheel or not.
   *
   * @return need wheel accessible or not.
   */
  public boolean isNeedWheelAccessible() {
    return needWheelAccessible;
  }

  /**
   * Is group leader or not.
   *
   * @return group leader or not.
   */
  public boolean isGroupLeader() {
    return isGroupLeader;
  }

  /**
   * Gets group.
   *
   * @return group.
   */
  public Group getGroup() {
    return group;
  }

  /**
   * Sets group.
   *
   * @param group is given group.
   */
  public void setGroup(Group group) {
    this.group = group;
  }

  /**
   * Gets seat.
   *
   * @return seat.
   */
  public Seat getSeat() {
    return seat;
  }

  /**
   * Sets seat.
   *
   * @param seat is given seat.
   */
  public void setSeat(Seat seat) {
    this.seat = seat;
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
