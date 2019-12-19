package assignment4;

/**
 * Represents a request.
 */
public class Request {

  private Attendee requester; // regular attendee or group leader

  private String groupId; // if groupId is null/"" means regular attendee
  private Group group;

  private String reqType; // reserve, print, cancel

  /**
   * Inits a request.
   *
   * @param requester is user.
   * @param groupId   is group id.
   * @param group     is group.
   * @param reqType   is type.
   */
  public Request(Attendee requester, String groupId, Group group, String reqType) {
    this.requester = requester;
    this.groupId = groupId;
    this.group = group;
    this.reqType = reqType;
  }

  /**
   * Gets user.
   *
   * @return requester.
   */
  public Attendee getRequester() {
    return requester;
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
   * Gets request type.
   *
   * @return type.
   */
  public String getReqType() {
    return reqType;
  }

  /**
   * Gets group id.
   *
   * @return id.
   */
  public String getGroupId() {
    return groupId;
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
