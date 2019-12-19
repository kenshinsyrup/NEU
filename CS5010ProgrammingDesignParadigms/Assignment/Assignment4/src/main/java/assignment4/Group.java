package assignment4;

import java.util.List;

/**
 * Represents a group.
 */
public class Group {

  // Group unchangeable attributes.
  private List<Attendee> attendeeList;
  private String id;
  private Attendee leader;

  /**
   * Inits a group.
   *
   * @param attendeeList is group member list.
   * @param id           is group id.
   */
  public Group(List<Attendee> attendeeList, String id) {
    this.attendeeList = attendeeList;
    this.id = id;
  }

  /**
   * Gets group attendee list.
   *
   * @return list.
   */
  public List<Attendee> getAttendeeList() {
    return attendeeList;
  }

  /**
   * Gets group leader.
   *
   * @return leader.
   */
  public Attendee getLeader() {
    return leader;
  }

  /**
   * Sets leader.
   *
   * @param leader is given leader.
   */
  public void setLeader(Attendee leader) {
    this.leader = leader;
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
