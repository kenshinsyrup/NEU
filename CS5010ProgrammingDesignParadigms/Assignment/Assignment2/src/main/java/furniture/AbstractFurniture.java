package furniture;

import furniture.config.Color;
import furniture.config.Size;
import java.util.Set;

/**
 * Represents a furniture.
 */
public abstract class AbstractFurniture {

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Size getSize() {
    return size;
  }

  public void setSize(Size size) {
    this.size = size;
  }

  public Set<Color> getColors() {
    return colors;
  }

  public void setColors(Set<Color> colors) {
    this.colors = colors;
  }

  private String name;
  private Size size;
  private Set<Color> colors;
}
