package furniture.part;

import furniture.accessory.Hinge;
import furniture.accessory.handle.AbstractHandle;
import furniture.config.Size;
import furniture.AbstractFurniture;
import java.util.HashSet;
import java.util.Set;
import furniture.part.line.belacqua.AbstractBelacquaDoor;
import furniture.part.line.gaga.AbstractGagaDoor;
import furniture.part.line.gaga.AbstractGagaDrawer;
import furniture.part.line.slothrop.AbstractSlothropDoor;
import utils.Num;
import utils.SizeType;

/**
 * Represents part for cabinet, now common fields are sizes(wardrobeSize, halfSize, quarterSize).
 */
public abstract class AbstractPart extends AbstractFurniture {

  private AbstractHandle doorAbstractHandle;
  private AbstractHandle drawerAbstractHandle;
  private Hinge hinge;
  private int num;

  /**
   * Get all parts in stock.
   *
   * @return
   */
  public static Set<AbstractPart> partStock() {
    Set<AbstractPart> abstractParts = new HashSet<>();
    abstractParts.add(new AbstractSlothropDoor(SizeType.WARDROBE));
    abstractParts.add(new AbstractSlothropDoor(SizeType.HALF));
    abstractParts.add(new AbstractBelacquaDoor(SizeType.WARDROBE));
    abstractParts.add(new AbstractBelacquaDoor(SizeType.HALF));
    abstractParts.add(new AbstractBelacquaDoor(SizeType.WARDROBE));
    abstractParts.add(new AbstractGagaDoor(SizeType.HALF));
    abstractParts.add(new AbstractGagaDoor(SizeType.QUARTER));
    abstractParts.add(new AbstractGagaDrawer(SizeType.QUARTER));
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

  public void setDoorAbstractHandle(AbstractHandle doorAbstractHandle) {
    this.doorAbstractHandle = doorAbstractHandle;
  }

  public void setDrawerAbstractHandle(AbstractHandle drawerAbstractHandle) {
    this.drawerAbstractHandle = drawerAbstractHandle;
  }

  public AbstractHandle getDoorAbstractHandle() {
    return doorAbstractHandle;
  }

  public AbstractHandle getDrawerAbstractHandle() {
    return drawerAbstractHandle;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }
}
