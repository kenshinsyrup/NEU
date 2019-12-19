package furniture.config;

/**
 * Represents a size.
 */
public class Size {

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getDepth() {
    return depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  private int height;
  private int width;
  private int depth;

  /**
   * Inits a size.
   *
   * @param height is given height.
   * @param width  is given width.
   * @param depth  is given depth.
   */
  public Size(int height, int width, int depth) {
    this.height = height;
    this.width = width;
    this.depth = depth;
  }


}
