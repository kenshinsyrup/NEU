package assignment2_refactored.furniture.config;

/**
 * Represent a floor mount.
 */
public class FloorMount extends AbstractMount {

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
    setFloorMounted(floorMounted);
    setFeetRequired(feetRequired);
    setFeetOptional(feetOptional);
    setFeetSeparate(feetSeparate);
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
