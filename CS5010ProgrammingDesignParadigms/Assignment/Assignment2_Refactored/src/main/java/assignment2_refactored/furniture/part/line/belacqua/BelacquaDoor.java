package assignment2_refactored.furniture.part.line.belacqua;

import assignment2_refactored.furniture.accessory.handle.AbstractHandle;
import assignment2_refactored.furniture.accessory.handle.DoorHandle;
import assignment2_refactored.furniture.part.Door;
import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;

/**
 * Represents Belacqua door.
 */
public class BelacquaDoor extends AbstractBelacqua implements Door {

  private final String BELACQUA = "Belacqua Door";

  /**
   * Inits a Belacqua door.
   *
   * @param sizeType is given size type.
   */
  public BelacquaDoor(String sizeType) {
    if (sizeType.equals(SizeType.WARDROBE)) {
      buildWardrobePart();
    } else if (sizeType.equals(SizeType.HALF)) {
      buildHalfPart();
    } else if (sizeType.equals(SizeType.QUARTER)) {
      buildQuarterPart();
    }
  }

  private void buildWardrobePart() {
    setSize(wardrobeSize());

    setColors(wardrobeColors());

    setDoorHandle(wardrobeHandle());

    setNum(wardrobeNum());

    setName(SizeType.WARDROBE + " " + BELACQUA);
  }

  private void buildHalfPart() {
    setSize(halfSize());

    setColors(halfColors());

    setDoorHandle(halfHandle());

    setNum(halfNum());

    setName(SizeType.HALF + " " + BELACQUA);
  }

  private void buildQuarterPart() {
    setSize(quarterSize());

    setColors(quarterColors());

    setDoorHandle(quarterHandle());

    setNum(quarterNum());

    setName(SizeType.QUARTER + " " + BELACQUA);
  }

  // below is belacqua handle.
  protected AbstractHandle wardrobeHandle() {
    return defaultHandle();
  }

  protected AbstractHandle halfHandle() {
    return defaultHandle();
  }

  protected AbstractHandle quarterHandle() {
    return defaultHandle();
  }

  private AbstractHandle defaultHandle() {
    return new DoorHandle(Num.ONE, true, false);
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
