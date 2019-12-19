package furniture.config;

/**
 * Represents a fit for a furniture, like "May be fitted with up to 3 shelves, or with a one drawer
 * and up to 2 shelves."
 */
public class Fit {

  private int shelfNum;

  public int getShelfNum() {
    return shelfNum;
  }

  public void setShelfNum(int shelfNum) {
    this.shelfNum = shelfNum;
  }

  public int getDrawerNum() {
    return drawerNum;
  }

  public void setDrawerNum(int drawerNum) {
    this.drawerNum = drawerNum;
  }

  private int drawerNum;

  /**
   * Init a Fit.
   *
   * @param shelfNum  is given shelf num.
   * @param drawerNum is given drawer num.
   */
  public Fit(int shelfNum, int drawerNum) {
    this.shelfNum = shelfNum;
    this.drawerNum = drawerNum;
  }
}
