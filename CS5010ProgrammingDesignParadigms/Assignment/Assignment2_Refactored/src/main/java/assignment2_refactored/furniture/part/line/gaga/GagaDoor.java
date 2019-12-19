package assignment2_refactored.furniture.part.line.gaga;


import assignment2_refactored.furniture.accessory.handle.AbstractHandle;
import assignment2_refactored.furniture.accessory.handle.DoorHandle;
import assignment2_refactored.furniture.part.Door;
import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;

/**
 * Represents a Gaga door.
 */
public class GagaDoor extends AbstractGaga implements Door {

  private final String GAGA = "Gaga Door";

  /**
   * Inits a Gaga door.
   *
   * @param sizeType is given size type.
   */
  public GagaDoor(String sizeType) {
    if (sizeType.equals(SizeType.HALF)) {
      buildHalfPart();
    } else if (sizeType.equals(SizeType.QUARTER)) {
      buildQuarterPart();
    }
  }

  private void buildHalfPart() {
    setSize(halfSize());

    setColors(halfColors());

    setDoorHandle(halfHandle());

    setNum(halfNum());

    setName(SizeType.HALF + " " + GAGA);
  }

  private void buildQuarterPart() {
    setSize(quarterSize());

    setColors(quarterColors());

    setDoorHandle(quarterHandle());

    setNum(quarterNum());

    setName(SizeType.QUARTER + " " + GAGA);
  }

  protected AbstractHandle halfHandle() {
    return new DoorHandle(Num.ONE, true, true);
  }

  protected AbstractHandle quarterHandle() {
    return new DoorHandle(Num.ONE, true, true);
  }

  /**
   * Implements Door interface.
   *
   * @return
   */
  public boolean isDoor() {
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

