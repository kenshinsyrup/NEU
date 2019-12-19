package assignment2_refactored.furniture.accessory.handle;

/**
 * Represents a door handle.
 */
public class DoorHandle extends AbstractHandle {

  /**
   * Inits a door handle.
   *
   * @param isRequired is true if required.
   * @param isSeparate is true if separate.
   */
  public DoorHandle(int num, boolean isRequired, boolean isSeparate) {
    setNum(num);
    setRequired(isRequired);
    setSeparate(isSeparate);
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