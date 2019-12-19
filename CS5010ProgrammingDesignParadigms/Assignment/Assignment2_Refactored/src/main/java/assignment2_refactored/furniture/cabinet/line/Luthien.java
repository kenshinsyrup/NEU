package assignment2_refactored.furniture.cabinet.line;


import assignment2_refactored.furniture.cabinet.AbstractCabinet;
import assignment2_refactored.furniture.config.Fit;
import assignment2_refactored.furniture.config.Size;
import assignment2_refactored.utils.ColorName;
import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;
import java.util.HashSet;
import java.util.Set;

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
  protected Set<String> halfColors() {
    return defaultColors();
  }

  protected Set<String> quarterColors() {
    return defaultColors();
  }

  private Set<String> defaultColors() {
    Set<String> colors = new HashSet<>();
    colors.add(ColorName.BLACK);
    colors.add(ColorName.BONE);
    return colors;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}

