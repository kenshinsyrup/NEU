package furniture.cabinet.line;

import furniture.cabinet.AbstractCabinet;
import furniture.config.Color;
import furniture.config.Fit;
import furniture.config.Size;
import java.util.HashSet;
import java.util.Set;
import utils.ColorName;
import utils.Num;
import utils.SizeType;

/**
 * Represent Luthien Cabinet.
 */
public class Luthien extends AbstractCabinet {

  private final String LUTHIEN = "Luthien Cabinet";

  /**
   * Inits a Luthiedn cabinet.
   *
   * @param sizeType is given type.
   */
  public Luthien(String sizeType) {
    if (sizeType.equals(SizeType.HALF)) {
      buildHalfSizeCabinet();
    } else if (sizeType.equals(SizeType.QUARTER)) {
      buildQuarterSizeCabinet();
    }
  }

  protected void buildHalfSizeCabinet() {
    setSize(halfSize());

    setMounts(halfMounts());

    setFits(halfFits());

    setColors(halfColors());

    setName(SizeType.HALF + " " + LUTHIEN);
  }

  protected void buildQuarterSizeCabinet() {
    setSize(quarterSize());

    setMounts(quarterMounts());

    setFits(quarterFits());

    setColors(quarterColors());

    setName(SizeType.QUARTER + " " + LUTHIEN);
  }

  // below is Luthien size
  protected Size halfSize() {
    return new Size(Num.THIRTY_SIX, Num.THIRTY_SIX, Num.EIGHTEEN);
  }

  protected Size quarterSize() {
    return new Size(Num.EIGHTEEN, Num.THIRTY_SIX, Num.EIGHTEEN);
  }

  // below is Luthien fits
  protected Set<Fit> halfFits() {
    Set<Fit> fits = new HashSet<>();
    fits.add(new Fit(Num.THREE, Num.ZERO));
    fits.add(new Fit(Num.TWO, Num.ONE));
    setFits(fits);
    return fits;
  }

  protected Set<Fit> quarterFits() {
    Set<Fit> fits = new HashSet<>();
    fits.add(new Fit(Num.ONE, Num.ZERO));
    fits.add(new Fit(Num.ZERO, Num.ONE));
    setFits(fits);
    return fits;
  }

  // below is Luthien colors
  protected Set<Color> halfColors() {
    return defaultColors();
  }

  protected Set<Color> quarterColors() {
    return defaultColors();
  }

  private Set<Color> defaultColors() {
    Set<Color> colors = new HashSet<>();
    colors.add(new Color(ColorName.BLACK));
    colors.add(new Color(ColorName.BONE));
    return colors;
  }

}
