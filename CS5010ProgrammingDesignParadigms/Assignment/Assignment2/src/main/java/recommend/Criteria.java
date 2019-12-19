package recommend;

import furniture.config.Color;
import java.util.Set;

/**
 * Represents a criteria.
 */
public class Criteria {
  
  private int cabinetWidth;
  private int cabinetHeight;
  private int cabinetShelfNum;
  private int cabinetDrawerNum;
  private Set<Color> colors;
  private boolean floorMounted;
  private boolean wallMounted;
  private int budget;

  /**
   * Inits a criteria.
   *
   * @param cabinetWidth     is given total furniture.cabinet width.
   * @param cabinetHeight    is given furniture.cabinet height.
   * @param cabinetShelfNum  is given furniture.cabinet shelf num.
   * @param cabinetDrawerNum is given furniture.cabinet drawer num.
   * @param colors           is given colors.
   * @param floorMounted     is true if need floor mounted.
   * @param wallMounted      is true if need wall mounted.
   * @param budget           is given budget.
   */
  public Criteria(int cabinetWidth, int cabinetHeight, int cabinetShelfNum, int cabinetDrawerNum,
      Set<Color> colors, boolean floorMounted, boolean wallMounted, int budget) {
    this.cabinetWidth = cabinetWidth;
    this.cabinetHeight = cabinetHeight;
    this.cabinetShelfNum = cabinetShelfNum;
    this.cabinetDrawerNum = cabinetDrawerNum;
    this.colors = colors;
    this.floorMounted = floorMounted;
    this.wallMounted = wallMounted;
    this.budget = budget;
  }


  public int getCabinetWidth() {
    return cabinetWidth;
  }

  public void setCabinetWidth(int cabinetWidth) {
    this.cabinetWidth = cabinetWidth;
  }

  public int getCabinetHeight() {
    return cabinetHeight;
  }

  public void setCabinetHeight(int cabinetHeight) {
    this.cabinetHeight = cabinetHeight;
  }

  public int getCabinetShelfNum() {
    return cabinetShelfNum;
  }

  public void setCabinetShelfNum(int cabinetShelfNum) {
    this.cabinetShelfNum = cabinetShelfNum;
  }

  public int getCabinetDrawerNum() {
    return cabinetDrawerNum;
  }

  public void setCabinetDrawerNum(int cabinetDrawerNum) {
    this.cabinetDrawerNum = cabinetDrawerNum;
  }

  public Set<Color> getColors() {
    return colors;
  }

  public void setColors(Set<Color> colors) {
    this.colors = colors;
  }

  public boolean isFloorMounted() {
    return floorMounted;
  }

  public void setFloorMounted(boolean floorMounted) {
    this.floorMounted = floorMounted;
  }

  public boolean isWallMounted() {
    return wallMounted;
  }

  public void setWallMounted(boolean wallMounted) {
    this.wallMounted = wallMounted;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }
}
