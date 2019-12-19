package furniture.part.line.belacqua;

import furniture.config.Color;
import java.util.HashSet;
import java.util.Set;
import furniture.part.AbstractPart;
import utils.Num;

/**
 * Represents the Belacqua style part, now common fields are the colors(wardrobeColors, halfColors,
 * quarterColors).
 */
public abstract class AbstractBelacqua extends AbstractPart {

  // below is Belacqua colors.
  protected Set<Color> wardrobeColors() {
    return defaultColors();
  }

  protected Set<Color> halfColors() {
    return defaultColors();
  }

  protected Set<Color> quarterColors() {
    return defaultColors();
  }

  private Set<Color> defaultColors() {
    Set<Color> colors = new HashSet<>();
    colors.add(new Color("bone"));
    colors.add(new Color("oxblood"));
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
