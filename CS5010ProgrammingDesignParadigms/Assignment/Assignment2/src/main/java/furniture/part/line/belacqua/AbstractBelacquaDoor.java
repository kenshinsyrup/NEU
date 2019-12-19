package furniture.part.line.belacqua;

import furniture.accessory.handle.DoorAbstractHandle;
import furniture.accessory.handle.AbstractHandle;
import furniture.part.Door;
import utils.Num;
import utils.SizeType;

/**
 * Represents Belacqua door.
 */
public class AbstractBelacquaDoor extends AbstractBelacqua implements Door {

  private final String BELACQUA = "Belacqua Door";

  /**
   * Inits a Belacqua door.
   *
   * @param sizeType is given size type.
   */
  public AbstractBelacquaDoor(String sizeType) {
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

    setDoorAbstractHandle(wardrobeHandle());

    setNum(wardrobeNum());

    setName(SizeType.WARDROBE + " " + BELACQUA);
  }

  private void buildHalfPart() {
    setSize(halfSize());

    setColors(halfColors());

    setDoorAbstractHandle(halfHandle());

    setNum(halfNum());

    setName(SizeType.HALF + " " + BELACQUA);
  }

  private void buildQuarterPart() {
    setSize(quarterSize());

    setColors(quarterColors());

    setDoorAbstractHandle(quarterHandle());

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
    return new DoorAbstractHandle(Num.ONE, true, false);
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
