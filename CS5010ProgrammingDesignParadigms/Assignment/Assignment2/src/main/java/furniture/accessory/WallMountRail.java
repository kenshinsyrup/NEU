package furniture.accessory;

/**
 * Represents a wall mount rail.
 */
public class WallMountRail extends AbstractAccessory {

  /**
   * Inits a wall mount rail.
   *
   * @param num is given num.
   */
  public WallMountRail(int num, boolean isRequired, boolean isStandard, boolean isSeparate) {
    setNum(num);
    setRequired(isRequired);
    setStandard(isStandard);
    setSeparate(isSeparate);
  }
}
