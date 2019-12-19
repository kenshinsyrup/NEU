package furniture.accessory.handle;

/**
 * Represents a door handle.
 */
public class DoorAbstractHandle extends AbstractHandle {

  /**
   * Inits a door handle.
   *
   * @param isRequired is true if required.
   * @param isSeparate is true if separate.
   */
  public DoorAbstractHandle(int num, boolean isRequired, boolean isSeparate) {
    setNum(num);
    setRequired(isRequired);
    setSeparate(isSeparate);
  }
}
