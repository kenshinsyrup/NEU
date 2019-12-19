package furniture.part.line.gaga;

import furniture.config.Color;
import java.util.HashSet;
import java.util.Set;
import furniture.part.AbstractPart;
import utils.ColorName;
import utils.Num;

/**
 * Represents the Gaga style part, now common fields are the colors(halfColors, quarterColors).
 */
public abstract class AbstractGaga extends AbstractPart {

  protected Set<Color> halfColors() {
    return defaultColors();
  }

  protected Set<Color> quarterColors() {
    return defaultColors();
  }

  private Set<Color> defaultColors() {
    Set<Color> colors = new HashSet<>();
    colors.add(new Color(ColorName.BROWN));
    colors.add(new Color(ColorName.BLACK));
    colors.add(new Color(ColorName.BONE));
    colors.add(new Color(ColorName.OXBLOOD));
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
