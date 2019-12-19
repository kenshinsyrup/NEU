package furniture.part.line.slothrop;

import furniture.config.Color;
import java.util.HashSet;
import java.util.Set;
import furniture.part.AbstractPart;
import utils.Num;

/**
 * Represents the Slothrop style furniture.part, now the colors(wardrobeColors, halfColors) and
 * handle(wardrobeHandle, halfHandle) are the common fields.
 */
public abstract class AbstractSlothrop extends AbstractPart {

  protected Set<Color> wardrobeColors() {
    return defaultColors();
  }

  protected Set<Color> halfColors() {
    return defaultColors();
  }

  private Set<Color> defaultColors() {
    Set<Color> colors = new HashSet<>();
    colors.add(new Color("brown"));
    colors.add(new Color("black"));
    return colors;
  }

  // below is Gaga num.
  protected int wardrobeNum() {
    return defaultNum();
  }

  protected int halfNum() {
    return defaultNum();
  }

  protected int defaultNum() {
    return Num.ONE;
  }
}
