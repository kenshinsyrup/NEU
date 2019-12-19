package furniture.part.line.slothrop;

import furniture.accessory.handle.DoorAbstractHandle;
import furniture.accessory.handle.AbstractHandle;
import furniture.part.Door;
import utils.Num;
import utils.SizeType;

/**
 * Represents a Slothrop door.
 */
public class AbstractSlothropDoor extends AbstractSlothrop implements Door {

  private final String SLOTHROP = "Slothrop Door";

  /**
   * Inits a Slothrop door.
   *
   * @param sizeType is given size type.
   */
  public AbstractSlothropDoor(String sizeType) {
    if (sizeType.equals(SizeType.WARDROBE)) {
      buildWardrobePart();
    } else if (sizeType.equals(SizeType.HALF)) {
      buildHalfPart();
    }
  }

  private void buildWardrobePart() {
    setSize(wardrobeSize());

    setColors(wardrobeColors());

    setDoorAbstractHandle(wardrobeHandle());

    setNum(wardrobeNum());

    setName(SizeType.WARDROBE + " " + SLOTHROP);
  }

  private void buildHalfPart() {
    setSize(halfSize());

    setColors(halfColors());

    setDoorAbstractHandle(halfHandle());

    setNum(halfNum());

    setName(SizeType.HALF + " " + SLOTHROP);
  }

  protected AbstractHandle wardrobeHandle() {
    return defaultHandle();
  }

  protected AbstractHandle halfHandle() {
    return defaultHandle();
  }

  private AbstractHandle defaultHandle() {
    return new DoorAbstractHandle(Num.ONE, true, false);
  }

  /**
   * Implements Door Interface.
   *
   * @return
   */
  public boolean isDoor() {
    return true;
  }

}
