package assignment2_refactored.furniture.cabinet.line;

import assignment2_refactored.furniture.accessory.WallFixture;
import assignment2_refactored.furniture.cabinet.AbstractCabinet;
import assignment2_refactored.furniture.config.Fit;
import assignment2_refactored.utils.ColorName;
import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents Yossarian Cabinet.
 */
public class Yossarian extends AbstractCabinet {

  private final String YOSSARIAN = "Yossarian Cabinet";

  /**
   * Inits a Yossarian cabinet.
   *
   * @param sizeType is given type.
   */
  public Yossarian(String sizeType) {
    if (sizeType.equals(SizeType.WARDROBE)) {
      buildWardrobeSizeCabinet();
    } else if (sizeType.equals(SizeType.HALF)) {
      buildHalfSizeCabinet();
    } else if (sizeType.equals(SizeType.QUARTER)) {
      buildQuarterSizeCabinet();
    }
  }

  protected void buildWardrobeSizeCabinet() {
    setSize(wardrobeSize());

    setMounts(wardrobeMounts());

    setFits(wardrobeFits());

    setColors(wardrobeColors());

    setWallFixture(new WallFixture(1));

    setName(SizeType.WARDROBE + " " + YOSSARIAN);

  }

  protected void buildHalfSizeCabinet() {
    setSize(halfSize());

    setMounts(halfMounts());

    setFits(halfFits());

    setColors(halfColors());

    setName(SizeType.HALF + " " + YOSSARIAN);
  }

  protected void buildQuarterSizeCabinet() {
    setSize(quarterSize());

    setMounts(quarterMounts());

    setFits(quarterFits());

    setColors(quarterColors());

    setName(SizeType.QUARTER + " " + YOSSARIAN);
  }

  protected Set<Fit> wardrobeFits() {
    Set<Fit> fits = new HashSet<>();
    fits.add(new Fit(Num.SEVEN, Num.ZERO));
    setFits(fits);
    return fits;
  }

  protected Set<Fit> halfFits() {
    Set<Fit> fits = new HashSet<>();
    fits.add(new Fit(Num.THREE, Num.ZERO));
    setFits(fits);
    return fits;
  }

  protected Set<Fit> quarterFits() {
    Set<Fit> fits = new HashSet<>();
    fits.add(new Fit(Num.ONE, Num.ZERO));
    setFits(fits);
    return fits;
  }

  protected Set<String> wardrobeColors() {
    Set<String> colors = defaultColors();
    colors.add(ColorName.OXBLOOD);
    return colors;
  }

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

