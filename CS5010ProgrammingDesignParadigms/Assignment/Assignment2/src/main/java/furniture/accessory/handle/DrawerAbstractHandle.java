package furniture.accessory.handle;

/**
 * Represents a drawer handle.
 */
public class DrawerAbstractHandle extends AbstractHandle {

  /**
   * Inits a drawer handle.
   *
   * @param isRequired is true if required.
   * @param isSeparate is true if separate.
   */
  public DrawerAbstractHandle(int num, boolean isRequired, boolean isSeparate) {
    setNum(num);
    setRequired(isRequired);
    setSeparate(isSeparate);
  }
}
