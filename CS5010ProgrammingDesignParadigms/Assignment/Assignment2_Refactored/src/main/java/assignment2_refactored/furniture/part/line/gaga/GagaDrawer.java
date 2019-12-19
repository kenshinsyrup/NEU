package assignment2_refactored.furniture.part.line.gaga;

import assignment2_refactored.furniture.accessory.handle.AbstractHandle;
import assignment2_refactored.furniture.accessory.handle.DrawerHandle;
import assignment2_refactored.furniture.part.Drawer;
import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;

/**
 * Represents Gaga drawer.
 */
public class GagaDrawer extends AbstractGaga implements Drawer {

  private final String GAGA = "Gaga Drawer";

  /**
   * Inits a Gaga drawer.
   *
   * @param sizeType is given size type.
   */
  public GagaDrawer(String sizeType) {
    if (sizeType.equals(SizeType.QUARTER)) {
      buildQuarterPart();
    }
  }

  private void buildQuarterPart() {
    setSize(quarterSize());

    setColors(quarterColors());

    setDrawerHandle(quarterHandle());

    setNum(quarterNum());

    setName(SizeType.QUARTER + " " + GAGA);
  }

  protected AbstractHandle quarterHandle() {
    return new DrawerHandle(Num.ONE, true, false);
  }

  /**
   * Implements Drawer interface.
   *
   * @return
   */
  public boolean isDrawer() {
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

