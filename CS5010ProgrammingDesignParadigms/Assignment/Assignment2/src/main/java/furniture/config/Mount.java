package furniture.config;

/**
 * Represents a mount way.
 */
public class Mount {

  // floor mounted with feet
  private boolean floorMounted;
  private boolean feetRequired;
  private boolean feetOptional;
  private boolean feetSeparate;

  // wall mounted with rail
  private boolean wallMounted;
  private boolean railRequired;
  private boolean railStandard;
  private boolean railSeparate;

  public void setFloorMounted(boolean floorMounted) {
    this.floorMounted = floorMounted;
  }

  public boolean isFeetRequired() {
    return feetRequired;
  }

  public void setFeetRequired(boolean feetRequired) {
    this.feetRequired = feetRequired;
  }

  public boolean isFeetOptional() {
    return feetOptional;
  }

  public void setFeetOptional(boolean feetOptional) {
    this.feetOptional = feetOptional;
  }

  public boolean isFeetSeparate() {
    return feetSeparate;
  }

  public void setFeetSeparate(boolean feetSeparate) {
    this.feetSeparate = feetSeparate;
  }

  public void setWallMounted(boolean wallMounted) {
    this.wallMounted = wallMounted;
  }

  public boolean isRailRequired() {
    return railRequired;
  }

  public void setRailRequired(boolean railRequired) {
    this.railRequired = railRequired;
  }

  public boolean isRailStandard() {
    return railStandard;
  }

  public void setRailStandard(boolean railStandard) {
    this.railStandard = railStandard;
  }

  public boolean isRailSeparate() {
    return railSeparate;
  }

  public void setRailSeparate(boolean railSeparate) {
    this.railSeparate = railSeparate;
  }

  /**
   * Inits a mount way.
   *
   * @param floorMounted is true if floor mounted.
   * @param feetRequired is true if feet is required.
   * @param feetOptional is true if feet is optional.
   * @param feetSeparate is true if feet is sold separately.
   * @param wallMounted  is true if wall mounted.
   * @param railRequired is true if rail is required.
   * @param railStandard is true if rail is standard.
   * @param railSeparate is true if rail is sold separately.
   */
  public Mount(boolean floorMounted, boolean feetRequired, boolean feetOptional,
      boolean feetSeparate,
      boolean wallMounted, boolean railRequired, boolean railStandard, boolean railSeparate) {
    this.floorMounted = floorMounted;
    this.feetRequired = feetRequired;
    this.feetOptional = feetOptional;
    this.feetSeparate = feetSeparate;
    this.wallMounted = wallMounted;
    this.railRequired = railRequired;
    this.railStandard = railStandard;
    this.railSeparate = railSeparate;
  }

  public boolean isFloorMounted() {
    return floorMounted;
  }

  public boolean isWallMounted() {
    return wallMounted;
  }

}
