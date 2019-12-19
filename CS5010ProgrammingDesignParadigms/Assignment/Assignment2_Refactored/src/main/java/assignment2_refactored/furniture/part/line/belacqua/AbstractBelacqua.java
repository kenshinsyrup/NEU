package assignment2_refactored.furniture.part.line.belacqua;

import assignment2_refactored.furniture.part.AbstractPart;
import assignment2_refactored.utils.ColorName;
import assignment2_refactored.utils.Num;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the Belacqua style part, now common fields are the colors(wardrobeColors, halfColors,
 * quarterColors).
 */
public abstract class AbstractBelacqua extends AbstractPart {

  // below is Belacqua colors.
  protected Set<String> wardrobeColors() {
    return defaultColors();
  }

  protected Set<String> halfColors() {
    return defaultColors();
  }

  protected Set<String> quarterColors() {
    return defaultColors();
  }

  private Set<String> defaultColors() {
    Set<String> colors = new HashSet<>();
    colors.add(ColorName.BONE);
    colors.add(ColorName.OXBLOOD);
    return colors;
  }

  // below is Belacqua num.
  protected int wardrobeNum() {
    return defaultNum();
  }

  protected int halfNum() {
    return defaultNum();
  }

  protected int quarterNum() {
    return defaultNum();
  }

  protected int defaultNum() {
    return Num.TWO;
  }
}

