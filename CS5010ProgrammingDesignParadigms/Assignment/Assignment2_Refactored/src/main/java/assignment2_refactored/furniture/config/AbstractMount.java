package assignment2_refactored.furniture.config;


/**
 * Represents a mount way.
 */
public abstract class AbstractMount {

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

  public boolean isFloorMounted() {
    return floorMounted;
  }

  public boolean isWallMounted() {
    return wallMounted;
  }

}

