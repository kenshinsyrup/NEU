package assignment4;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VenueTest {

  Venue venue;

  @Before
  public void setUp() throws Exception {
    Section section0 = new Section("SectionId0", 0, 0, 1, 5);
    Section section1 = new Section("SectionId1", 1, 0, 2, 5);
    Section section2 = new Section("SectionId2", 3, 0, 3, 5);
    List<Section> sectionList = new ArrayList<>();
    sectionList.add(section0);
    sectionList.add(section1);
    sectionList.add(section2);

    this.venue = new Venue("VenueId", sectionList);
  }

  @Test
  public void processRequest() {
    // regular attendee
    Attendee attendee0 = new Attendee("Attendee0", false, false, null, null);
    Request reserveReq = new Request(attendee0, null, null, utils.RESERVE);
    Assert.assertTrue(this.venue.processRequest(reserveReq));

    Attendee attendee1 = new Attendee("Attendee1", true, false, null, null);
    reserveReq = new Request(attendee1, null, null, utils.RESERVE);
    Assert.assertTrue(this.venue.processRequest(reserveReq));

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
    reserveReq = new Request(leader, "group1", group, utils.RESERVE);
    Assert.assertTrue(this.venue.processRequest(reserveReq));

    // group member not leader
    Attendee attendee5 = new Attendee("Attendee5", false, false, "group1", null);
    reserveReq = new Request(attendee5, "group1", null, utils.RESERVE);
    Assert.assertTrue(this.venue.processRequest(reserveReq));

//    cancel
    Request cancelReq = new Request(attendee5, "group1", null, utils.CANCEL);
    Assert.assertTrue(this.venue.processRequest(cancelReq));

//    print
    Request printReq = new Request(attendee2, null, group, utils.PRINT);
    Assert.assertTrue(this.venue.processRequest(printReq));
    this.venue.preview();

  }

  @Test
  public void preview() {
    this.venue.preview();
  }
}