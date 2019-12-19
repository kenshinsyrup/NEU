package assignment2_refactored.furniture.part;


import assignment2_refactored.furniture.AbstractFurniture;
import assignment2_refactored.furniture.accessory.Hinge;
import assignment2_refactored.furniture.accessory.handle.AbstractHandle;
import assignment2_refactored.furniture.config.Size;
import assignment2_refactored.furniture.part.line.belacqua.BelacquaDoor;
import assignment2_refactored.furniture.part.line.gaga.GagaDoor;
import assignment2_refactored.furniture.part.line.gaga.GagaDrawer;
import assignment2_refactored.furniture.part.line.slothrop.SlothropDoor;
import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents part for cabinet, now common fields are sizes(wardrobeSize, halfSize, quarterSize).
 */
public abstract class AbstractPart extends AbstractFurniture {

  private AbstractHandle doorHandle;
  private AbstractHandle drawerHandle;
  private Hinge hinge;
  private int num;

  /**
   * Get all parts in stock.
   *
   * @return
   */
  public static Set<AbstractPart> partStock() {
    Set<AbstractPart> abstractParts = new HashSet<>();
    abstractParts.add(new SlothropDoor(SizeType.WARDROBE));
    abstractParts.add(new SlothropDoor(SizeType.HALF));
    abstractParts.add(new BelacquaDoor(SizeType.WARDROBE));
    abstractParts.add(new BelacquaDoor(SizeType.HALF));
    abstractParts.add(new BelacquaDoor(SizeType.WARDROBE));
    abstractParts.add(new GagaDoor(SizeType.HALF));
    abstractParts.add(new GagaDoor(SizeType.QUARTER));
    abstractParts.add(new GagaDrawer(SizeType.QUARTER));
    return abstractParts;
  }

  protected Size wardrobeSize() {
    return new Size(Num.SEVENTY_TWO, Num.THIRTY_SIX, Num.ZERO);
  }

  protected Size halfSize() {
    return new Size(Num.THIRTY_SIX, Num.THIRTY_SIX, Num.ZERO);
  }

  protected Size quarterSize() {
    return new Size(Num.EIGHTEEN, Num.THIRTY_SIX, Num.ZERO);
  }

  public Hinge getHinge() {
    return hinge;
  }

  public void setHinge(Hinge hinge) {
    this.hinge = hinge;
  }


  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }


  public AbstractHandle getDoorHandle() {
    return doorHandle;
  }

  public void setDoorHandle(AbstractHandle doorHandle) {
    this.doorHandle = doorHandle;
  }

  public AbstractHandle getDrawerHandle() {
    return drawerHandle;
  }

  public void setDrawerHandle(
      AbstractHandle drawerHandle) {
    this.drawerHandle = drawerHandle;
  }
}

