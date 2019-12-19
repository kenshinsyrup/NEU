package furniture.config;

/**
 * Represents a wall mount.
 */
public class WallMount extends Mount {

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
    super(false, false, false, false, wallMounted, railRequired, railStandard, railSeparate);
  }

}
