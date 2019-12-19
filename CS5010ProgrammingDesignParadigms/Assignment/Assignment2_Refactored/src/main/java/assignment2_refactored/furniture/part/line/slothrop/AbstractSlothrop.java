package assignment2_refactored.furniture.part.line.slothrop;

import assignment2_refactored.furniture.part.AbstractPart;
import assignment2_refactored.utils.ColorName;
import assignment2_refactored.utils.Num;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the Slothrop style furniture.part, now the colors(wardrobeColors, halfColors) and
 * handle(wardrobeHandle, halfHandle) are the common fields.
 */
public abstract class AbstractSlothrop extends AbstractPart {

  protected Set<String> wardrobeColors() {
    return defaultColors();
  }

  protected Set<String> halfColors() {
    return defaultColors();
  }

  private Set<String> defaultColors() {
    Set<String> colors = new HashSet<>();
    colors.add(ColorName.BROWN);
    colors.add(ColorName.BLACK);
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

