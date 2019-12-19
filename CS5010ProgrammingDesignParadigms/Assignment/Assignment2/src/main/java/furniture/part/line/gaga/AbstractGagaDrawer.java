package furniture.part.line.gaga;

import furniture.accessory.handle.DrawerAbstractHandle;
import furniture.accessory.handle.AbstractHandle;
import furniture.part.Drawer;
import utils.Num;
import utils.SizeType;

/**
 * Represents Gaga drawer.
 */
public class AbstractGagaDrawer extends AbstractGaga implements Drawer {

  private final String GAGA = "Gaga Drawer";

  /**
   * Inits a Gaga drawer.
   *
   * @param sizeType is given size type.
   */
  public AbstractGagaDrawer(String sizeType) {
    if (sizeType.equals(SizeType.QUARTER)) {
      buildQuarterPart();
    }
  }

  private void buildQuarterPart() {
    setSize(quarterSize());

    setColors(quarterColors());

    setDrawerAbstractHandle(quarterHandle());

    setNum(quarterNum());

    setName(SizeType.QUARTER + " " + GAGA);
  }

  protected AbstractHandle quarterHandle() {
    return new DrawerAbstractHandle(Num.ONE, true, false);
  }

  /**
   * Implements Drawer interface.
   *
   * @return
   */
  public boolean isDrawer() {
    return true;
  }
}
