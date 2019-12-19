package assignment4;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RequestTest {

  Request request;
  Attendee attendee;

  @Before
  public void setUp() throws Exception {
    this.attendee = new Attendee("AttendeeId", false, false, null, null);
    this.request = new Request(attendee, null, null, utils.RESERVE);
  }

  @Test
  public void getRequester() {
    assertTrue(this.request.getRequester().getId().equals("AttendeeId"));
  }

  @Test
  public void getGroup() {
    assertNull(this.request.getGroup());
  }

  @Test
  public void getReqType() {
    assertTrue(this.request.getReqType().equals(utils.RESERVE));
  }

  @Test
  public void getGroupId() {
    assertNull(this.request.getGroupId());
  }
}