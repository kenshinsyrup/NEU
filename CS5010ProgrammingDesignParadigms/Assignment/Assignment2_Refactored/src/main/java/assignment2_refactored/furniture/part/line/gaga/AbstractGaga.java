package assignment2_refactored.furniture.part.line.gaga;

import assignment2_refactored.furniture.part.AbstractPart;
import assignment2_refactored.utils.ColorName;
import assignment2_refactored.utils.Num;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the Gaga style part, now common fields are the colors(halfColors, quarterColors).
 */
public abstract class AbstractGaga extends AbstractPart {

  protected Set<String> halfColors() {
    return defaultColors();
  }

  protected Set<String> quarterColors() {
    return defaultColors();
  }

  private Set<String> defaultColors() {
    Set<String> colors = new HashSet<>();
    colors.add(ColorName.BROWN);
    colors.add(ColorName.BLACK);
    colors.add(ColorName.BONE);
    colors.add(ColorName.OXBLOOD);
    return colors;
  }

  // below is Gaga num.
  protected int halfNum() {
    return defaultNum();
  }

  protected int quarterNum() {
    return defaultNum();
  }

  protected int defaultNum() {
    return Num.ONE;
  }
}

