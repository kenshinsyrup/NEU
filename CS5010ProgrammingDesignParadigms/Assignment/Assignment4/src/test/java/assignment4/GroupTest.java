package assignment4;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class GroupTest {

  Group group;

  @Before
  public void setUp() throws Exception {
    // group leader
    List<Attendee> attendeeList = new ArrayList<>();
    Group group = new Group(attendeeList, "group1");
    Attendee attendee2 = new Attendee("Attendee2", true, false, "group1", null);
    attendeeList.add(attendee2);
    attendeeList.add(new Attendee("Attendee3", false, false, "group1", null));
    attendeeList.add(new Attendee("Attendee4", false, false, "group1", null));
    Attendee leader = new Attendee("Group1Leader", false, true, "group1", group);
    attendeeList.add(leader);
    group.setLeader(leader);

    this.group = group;
  }

  @Test
  public void getAttendeeList() {
    assertTrue(this.group.getAttendeeList().size() == 4);
  }

  @Test
  public void getLeader() {
    assertTrue(this.group.getLeader().getId().equals("Group1Leader"));
  }

  @Test
  public void setLeader() {
    this.group.setLeader(null);
    assertNull(this.group.getLeader());
  }
}