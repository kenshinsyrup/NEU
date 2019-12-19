package furniture.cabinet.line;

import furniture.accessory.WallFixture;
import furniture.cabinet.AbstractCabinet;
import furniture.config.Color;
import furniture.config.Fit;
import java.util.HashSet;
import java.util.Set;
import utils.ColorName;
import utils.Num;
import utils.SizeType;

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

  protected Set<Color> wardrobeColors() {
    Set<Color> colors = defaultColors();
    colors.add(new Color(ColorName.OXBLOOD));
    return colors;
  }

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
    return colors;
  }

}
