package furniture.part.line.gaga;

import furniture.accessory.handle.DoorAbstractHandle;
import furniture.accessory.handle.AbstractHandle;
import furniture.part.Door;
import utils.Num;
import utils.SizeType;

/**
 * Represents a Gaga door.
 */
public class AbstractGagaDoor extends AbstractGaga implements Door {

  private final String GAGA = "Gaga Door";

  /**
   * Inits a Gaga door.
   *
   * @param sizeType is given size type.
   */
  public AbstractGagaDoor(String sizeType) {
    if (sizeType.equals(SizeType.HALF)) {
      buildHalfPart();
    } else if (sizeType.equals(SizeType.QUARTER)) {
      buildQuarterPart();
    }
  }

  private void buildHalfPart() {
    setSize(halfSize());

    setColors(halfColors());

    setDoorAbstractHandle(halfHandle());

    setNum(halfNum());

    setName(SizeType.HALF + " " + GAGA);
  }

  private void buildQuarterPart() {
    setSize(quarterSize());

    setColors(quarterColors());

    setDoorAbstractHandle(quarterHandle());

    setNum(quarterNum());

    setName(SizeType.QUARTER + " " + GAGA);
  }

  protected AbstractHandle halfHandle() {
    return new DoorAbstractHandle(Num.ONE, true, true);
  }

  protected AbstractHandle quarterHandle() {
    return new DoorAbstractHandle(Num.ONE, true, true);
  }

  /**
   * Implements Door interface.
   *
   * @return
   */
  public boolean isDoor() {
    return true;
  }

}
