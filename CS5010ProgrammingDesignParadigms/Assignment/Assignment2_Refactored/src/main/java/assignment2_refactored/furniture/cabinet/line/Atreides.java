package assignment2_refactored.furniture.cabinet.line;

import assignment2_refactored.furniture.cabinet.AbstractCabinet;
import assignment2_refactored.furniture.config.AbstractMount;
import assignment2_refactored.furniture.config.Fit;
import assignment2_refactored.furniture.config.FloorMount;
import assignment2_refactored.furniture.config.WallMount;
import assignment2_refactored.utils.ColorName;
import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;
import java.util.HashSet;
import java.util.Set;

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
  protected Set<AbstractMount> halfMounts() {
    Set<AbstractMount> mounts = new HashSet<>();
    mounts.add(new FloorMount(true, false, true, true));
    mounts.add(new WallMount(true, true, false, true));
    return mounts;
  }

  // Floor mounted (feet required, available separately) or wall mounted
  //(Atreides cabinets use their own special wall-mounting rails, which are
  //distinct from the standard rails, but must be purchased separately.)
  protected Set<AbstractMount> quarterMounts() {
    Set<AbstractMount> mounts = new HashSet<>();
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
  protected Set<String> halfColors() {
    return defaultColors();
  }

  protected Set<String> quarterColors() {
    return defaultColors();
  }

  private Set<String> defaultColors() {
    Set<String> colors = new HashSet<>();
    colors.add(ColorName.BROWN);
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

