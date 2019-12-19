package furniture.config;

import utils.ColorName;

/**
 * Represents a color.
 */
public class Color {

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private String name;

  /**
   * Init a color.
   *
   * @param name is given color name.
   */
  public Color(String name) {
    if (name.equals(ColorName.BROWN)) {
      this.name = ColorName.BROWN;
    } else if (name.equals(ColorName.BLACK)) {
      this.name = ColorName.BLACK;
    } else if (name.equals(ColorName.BONE)) {
      this.name = ColorName.BONE;
    } else if (name.equals(ColorName.OXBLOOD)) {
      this.name = ColorName.OXBLOOD;
    } else {
      this.name = ColorName.OTHER;
    }
  }

}
