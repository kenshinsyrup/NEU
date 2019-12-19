package furniture.config;

/**
 * Represent a floor mount.
 */
public class FloorMount extends Mount {

  /**
   * Init a floor mount.
   *
   * @param floorMounted is true if could be floor mounted.
   * @param feetRequired is true if feet is required.
   * @param feetOptional is true if feet is optional.
   * @param feetSeparate is true if feet is sold separately.
   */
  public FloorMount(boolean floorMounted, boolean feetRequired, boolean feetOptional,
      boolean feetSeparate) {
    super(floorMounted, feetRequired, feetOptional, feetSeparate, false, false, false, false);
  }

}
