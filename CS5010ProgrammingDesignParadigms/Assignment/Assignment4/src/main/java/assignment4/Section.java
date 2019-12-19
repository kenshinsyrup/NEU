package assignment4;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a section.
 */
public class Section {

  // section unchangeable attributes.
  private String id;
  private int rowStartIdx; // col idx in venue.
  private int colStartIdx; // row idx in venue.
  private int rowNum;
  private int colNum;
  private List<Seat> seatList;

  private boolean isFull;

  /**
   * Inits a section.
   *
   * @param id          is given id.
   * @param rowStartIdx is row idx in venue.
   * @param colStartIdx is col idx in venue.
   * @param rowNum      is row num.
   * @param colNum      is col num.
   */
  public Section(String id, int rowStartIdx, int colStartIdx, int rowNum, int colNum) {
    this.id = id;
    this.rowStartIdx = rowStartIdx;
    this.colStartIdx = colStartIdx;
    this.rowNum = rowNum;
    this.colNum = colNum;

    this.seatList = new ArrayList<>();
    arrangeSeats();
  }

  /**
   * Gets full status.
   *
   * @return full or not.
   */
  public boolean isFull() {
    for (Seat seat : this.seatList) {
      if (!(seat.isReserved() || seat.isPrinted())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Gets seat by its id.
   *
   * @param rowIdx is row idx in section.
   * @param colIdx is col idx in section.
   * @return seat.
   */
  public Seat getSeatById(int rowIdx, int colIdx) {
    String seatId = this.id + " " + rowIdx + " " + colIdx;

    for (Seat seat : this.seatList) {
      if (seat.getId().equals(seatId)) {
        return seat;
      }
    }

    return null;
  }

  // puts seats to this section
  private void arrangeSeats() {
    // wheel accessible seats
    List<Seat> wheelchairAccessibleSeats = new ArrayList<>();
    for (int i = 0; i < this.colNum; i++) {
      String seatId = this.id + " " + 0 + " " + i;
      wheelchairAccessibleSeats.add(new Seat(seatId, true));
    }

    // regular seats
    List<Seat> regularSeats = new ArrayList<>();
    for (int i = 1; i < this.rowNum; i++) {
      for (int j = 0; j < this.colNum; j++) {
        String seatId = this.id + " " + i + " " + j;
        regularSeats.add(new Seat(seatId, false));
      }
    }

    this.seatList.addAll(wheelchairAccessibleSeats);
    this.seatList.addAll(regularSeats);
  }

  /**
   * Gets section id.
   *
   * @return id.
   */
  public String getId() {
    return id;
  }

  /**
   * Gets row num.
   *
   * @return row num.
   */
  public int getRowNum() {
    return rowNum;
  }

  /**
   * Gets col num.
   *
   * @return col num.
   */
  public int getColNum() {
    return colNum;
  }

  /**
   * Gets seat list.
   *
   * @return seat list.
   */
  public List<Seat> getSeatList() {
    return seatList;
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
