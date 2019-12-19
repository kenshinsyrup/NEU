package assignment2_refactored.furniture.accessory.handle;

/**
 * Represents a drawer handle.
 */
public class DrawerHandle extends AbstractHandle {

  /**
   * Inits a drawer handle.
   *
   * @param isRequired is true if required.
   * @param isSeparate is true if separate.
   */
  public DrawerHandle(int num, boolean isRequired, boolean isSeparate) {
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