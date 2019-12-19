package assignment4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SeatTest {

  Seat seat;
  Attendee attendee;

  @Before
  public void setUp() throws Exception {
    this.seat = new Seat("SeatId", true);
    this.attendee = new Attendee("AttendeeId", false, false, null, null);
  }

  @Test
  public void assignSeat() {
    assertTrue(this.seat.assignSeat(this.attendee));
  }

  @Test
  public void getId() {
    assertTrue(this.seat.getId().equals("SeatId"));
  }

  @Test
  public void getUser() {
    assertTrue(this.seat.assignSeat(this.attendee));
    assertTrue(this.seat.getUser().getId().equals("AttendeeId"));
  }

  @Test
  public void setUser() {
    this.seat.setUser(null);
    assertNull(this.seat.getUser());
  }

  @Test
  public void isReserved() {
    assertFalse(this.seat.isReserved());
  }

  @Test
  public void setReserved() {
    this.seat.setReserved(true);
    assertTrue(this.seat.isReserved());
  }

  @Test
  public void isPrinted() {
    assertFalse(this.seat.isPrinted());
  }

  @Test
  public void setPrinted() {
    this.seat.setPrinted(true);
    assertTrue(this.seat.isPrinted());
  }
}