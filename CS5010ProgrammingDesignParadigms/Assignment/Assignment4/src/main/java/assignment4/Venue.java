package assignment4;

import java.util.List;

/**
 * Represents a venue.
 */
public class Venue {

  //  Venue unchangeable attributes
  private String id;

  private List<Section> sectionList;


  /**
   * Inits a venue.
   *
   * @param id          is id.
   * @param sectionList is section list.
   */
  public Venue(String id, List<Section> sectionList) {
    this.id = id;
    this.sectionList = sectionList;
  }

  /**
   * Processes request.
   *
   * @param req is given request.
   * @return result.
   */
  public boolean processRequest(Request req) {

    switch (req.getReqType()) {
      case utils.RESERVE:
        return processReserve(req);
      case utils.CANCEL:
        return processCancel(req);
      case utils.PRINT:
        return processPrint(req);
      default:
        System.out.println("Wrong type request.");
        return false;
    }
  }

  /**
   * Preview current venue status.
   */
  public void preview() {
    for (Section section : this.sectionList) {
      System.out.println("Section: " + section.getId());
      for (int i = 0; i < section.getRowNum(); i++) {
        for (int j = 0; j < section.getColNum(); j++) {
          Seat seat = section.getSeatById(i, j);
          System.out.print("Reserved: " + seat.isReserved() + " Printed: " + seat.isPrinted());
          if (seat.getUser() != null) {
            System.out.print(" User: " + seat.getUser().getId());
          } else {
            System.out.print(" User: No");
          }
          System.out.print(" | ");
        }
        System.out.println();
      }
    }
  }

  // process reserve request.
  private boolean processReserve(Request req) {
    if (isFull()) {
      return false;
    }

    String groupId = req.getGroupId();

    // condition 1:
    // regular individual attendee: arrange the seat from the last section
    if (groupId == null || groupId.length() == 0) {
      for (int i = this.sectionList.size() - 1; i >= 0; i--) {
        Section section = this.sectionList.get(i);
        if (section.isFull()) {
          continue;
        }

        for (int j = section.getSeatList().size() - 1; j >= 0; j--) {
          Seat seat = section.getSeatList().get(j);
          if (seat.isPrinted() || seat.isReserved()) {
            continue;
          }

          if (seat.assignSeat(req.getRequester())) {
            return true;
          }
        }
      }

      return false;
    }

    // condition 2:
    // 2.1 group attendee but not leader
    // 2.2 group leader with group: first available seat in first available section
    groupId = groupId.toUpperCase();
    // check if this groupId is in a section, if is, add this user to the group tail area
    Section firstAvailableSection = null;
    int firstAvailableRow = -1;
    for (int i = 0; i < this.sectionList.size(); i++) {
      Section section = this.sectionList.get(i);

      // assume one row for one group, so only check first seat at every row
      for (int j = 0; j < section.getRowNum(); j++) {
        Seat firstColSeat = section.getSeatById(j, 0);
        Attendee firstColSeatUser = firstColSeat.getUser();

        if (firstColSeatUser != null && firstColSeatUser.getGroupId() != null && firstColSeatUser
            .getGroupId().toUpperCase().equals(groupId)) {
          for (int k = 0; k < section.getColNum(); k++) {
            Seat seat = section.getSeatById(j, k);

            // find the first empty seat
            if (seat.getUser() == null) {
              Group group = req.getGroup();
              // if group attendee but not leader
              if (group == null) {
                seat.assignSeat(req.getRequester());
                req.getRequester().setGroup(firstColSeatUser
                    .getGroup());
                req.getRequester().notifyGroupLeader(req.getReqType(), firstColSeatUser
                    .getGroup());
                return true;
              } else {
                // if group leader
                int attendeeIdx = 0;
                for (int l = k; l < section.getColNum(); l++) {
                  if (attendeeIdx >= group.getAttendeeList().size()) {
                    break;
                  }

                  Attendee attendeeToBeAssigned = group.getAttendeeList().get(attendeeIdx);
                  Seat seatToBeAssigned = section.getSeatById(j, l);
                  seatToBeAssigned.assignSeat(attendeeToBeAssigned);
                  attendeeToBeAssigned.notifyGroupLeader(req.getReqType(), group);

                  attendeeIdx++;
                }
                return true;
              }
            }
          }
        } else {
          // record the first available section and row
          if (firstAvailableSection == null) {
            firstAvailableSection = section;
            firstAvailableRow = j;
          }
        }
      }
    }

    // if not return in for loop above, means no such group in all sections, so we put this user and its group to the firstAvailableSection's firstAvailableRow
    if (firstAvailableSection != null) {
      Group group = req.getGroup();

      if (group == null) {
        Seat firstSeat = firstAvailableSection.getSeatById(firstAvailableRow, 0);
        firstSeat.assignSeat(req.getRequester());
        return true;
      } else {
        int attendeeIdx = 0;
        System.out.println("start seat: " + firstAvailableRow + " " + 0);
        for (int i = 0; i < firstAvailableSection.getColNum(); i++) {
          if (attendeeIdx >= group.getAttendeeList().size()) {
            break;
          }

          Attendee attendeeToBeAssigned = group.getAttendeeList().get(attendeeIdx);
          Seat seatToBeAssigned = firstAvailableSection.getSeatById(firstAvailableRow, i);
          seatToBeAssigned.assignSeat(attendeeToBeAssigned);
          attendeeToBeAssigned.notifyGroupLeader(req.getReqType(), group);

          attendeeIdx++;
        }
        return true;
      }
    }

    return false;

  }

  // process cancel request.
  private boolean processCancel(Request req) {
    if (req.getRequester() == null) {
      return false;
    }

    Attendee attendee = req.getRequester();
    // if not leader, set the seat not reserved
    if (!attendee.isGroupLeader()) {
      if (attendee.getSeat().isPrinted()) {
        return false;
      }

      attendee.getSeat().setReserved(false);
      attendee.getSeat().setUser(null);
      attendee.setSeat(null);
      attendee.notifyGroupLeader(req.getReqType(), req.getGroup());
      return true;
    }

    // if leader, set all seats not reserved
    Group group = attendee.getGroup();
    for (Attendee gtoupAttendee : group.getAttendeeList()) {
      if (attendee.getSeat().isPrinted()) {
        return false;
      }

      attendee.getSeat().setReserved(false);
      attendee.notifyGroupLeader(req.getReqType(), group);
    }
    return true;
  }

  // process print request.
  private boolean processPrint(Request req) {
    if (req.getRequester() == null) {
      return false;
    }

    Attendee attendee = req.getRequester();
    // if not leader, set the seat printed
    if (!attendee.isGroupLeader()) {
      if (attendee.getSeat().isPrinted()) {
        return false;
      }

      attendee.getSeat().setPrinted(true);
      attendee.notifyGroupLeader(req.getReqType(), req.getGroup());
      return true;
    }

    // if leader, set all seats printed
    Group group = attendee.getGroup();
    for (Attendee gtoupAttendee : group.getAttendeeList()) {
      if (attendee.getSeat().isPrinted()) {
        return false;
      }
      attendee.getSeat().setPrinted(true);
      attendee.notifyGroupLeader(req.getReqType(), group);
    }
    return true;
  }


  // check Venue is full or not
  private boolean isFull() {
    if (this.sectionList == null) {
      return true;
    }

    for (Section section : this.sectionList) {
      if (!section.isFull()) {
        return false;
      }
    }

    return true;
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
