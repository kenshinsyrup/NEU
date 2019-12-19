package furniture.cabinet.line;

import furniture.cabinet.AbstractCabinet;
import furniture.config.Color;
import furniture.config.Fit;
import furniture.config.FloorMount;
import furniture.config.Mount;
import furniture.config.WallMount;
import java.util.HashSet;
import java.util.Set;
import utils.Num;
import utils.SizeType;

/**
 * Represents Atreides Cabinet.
 */
public class Atreides extends AbstractCabinet {

  private final String ATREIDES = "Atreides Cabinet";

  /**
   * Inits a Atreides cabinet.
   *
   * @param sizeType is given type.
   */
  public Atreides(String sizeType) {
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

    setName(SizeType.HALF + " " + ATREIDES);
  }

  protected void buildQuarterSizeCabinet() {
    setSize(quarterSize());

    setMounts(quarterMounts());

    setFits(quarterFits());

    setColors(quarterColors());

    setName(SizeType.QUARTER + " " + ATREIDES);
  }

  // below is Atreides mounts
  // Floor mounted (feet optional, available separately)
  // or wall mounted (Atreides cabinets use their own special wall-mounting rails,
  // which are distinct from the standard rails, but must be purchased separately.)
  protected Set<Mount> halfMounts() {
    Set<Mount> mounts = new HashSet<>();
    mounts.add(new FloorMount(true, false, true, true));
    mounts.add(new WallMount(true, true, false, true));
    return mounts;
  }

  // Floor mounted (feet required, available separately) or wall mounted
  //(Atreides cabinets use their own special wall-mounting rails, which are
  //distinct from the standard rails, but must be purchased separately.)
  protected Set<Mount> quarterMounts() {
    Set<Mount> mounts = new HashSet<>();
    mounts.add(new FloorMount(true, true, false, true));
    mounts.add(new WallMount(true, true, false, true));
    return mounts;
  }

  // below is Atreides fits
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

  // below is Atreides colors
  protected Set<Color> halfColors() {
    return defaultColors();
  }

  protected Set<Color> quarterColors() {
    return defaultColors();
  }

  private Set<Color> defaultColors() {
    Set<Color> colors = new HashSet<>();
    colors.add(new Color("brown"));
    colors.add(new Color("bone"));
    return colors;
  }

}
