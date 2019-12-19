package assignment2_refactored.furniture.part.line.slothrop;


import assignment2_refactored.furniture.accessory.handle.AbstractHandle;
import assignment2_refactored.furniture.accessory.handle.DoorHandle;
import assignment2_refactored.furniture.part.Door;
import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;

/**
 * Represents a Slothrop door.
 */
public class SlothropDoor extends AbstractSlothrop implements Door {

  private final String SLOTHROP = "Slothrop Door";

  /**
   * Inits a Slothrop door.
   *
   * @param sizeType is given size type.
   */
  public SlothropDoor(String sizeType) {
    if (sizeType.equals(SizeType.WARDROBE)) {
      buildWardrobePart();
    } else if (sizeType.equals(SizeType.HALF)) {
      buildHalfPart();
    }
  }

  private void buildWardrobePart() {
    setSize(wardrobeSize());

    setColors(wardrobeColors());

    setDoorHandle(wardrobeHandle());

    setNum(wardrobeNum());

    setName(SizeType.WARDROBE + " " + SLOTHROP);
  }

  private void buildHalfPart() {
    setSize(halfSize());

    setColors(halfColors());

    setDoorHandle(halfHandle());

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
    return new DoorHandle(Num.ONE, true, false);
  }

  /**
   * Implements Door Interface.
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

