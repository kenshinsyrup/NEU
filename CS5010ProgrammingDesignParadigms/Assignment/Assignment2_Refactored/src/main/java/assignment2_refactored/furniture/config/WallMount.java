package assignment2_refactored.furniture.config;

/**
 * Represents a wall mount.
 */
public class WallMount extends AbstractMount {

  /**
   * Inits a wall mount.
   *
   * @param wallMounted  is true if wall mounted.
   * @param railRequired is true if rail required.
   * @param railStandard is true if tail is standard.
   * @param railSeparate is true if rail is separate.
   */
  public WallMount(boolean wallMounted, boolean railRequired, boolean railStandard,
      boolean railSeparate) {
    setWallMounted(wallMounted);
    setRailRequired(railRequired);
    setRailStandard(railStandard);
    setRailSeparate(railSeparate);
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
