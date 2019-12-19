package furniture.accessory;

/**
 * Represents a accessory.
 */
public abstract class AbstractAccessory {

  private boolean isRequired;
  private boolean isSeparate;
  private boolean isStandard;
  private int price;
  private int num;

  public boolean isRequired() {
    return isRequired;
  }

  public void setRequired(boolean required) {
    isRequired = required;
  }

  public boolean isSeparate() {
    return isSeparate;
  }

  public void setSeparate(boolean separate) {
    isSeparate = separate;
  }

  public boolean isStandard() {
    return isStandard;
  }

  public void setStandard(boolean standard) {
    isStandard = standard;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }
}
