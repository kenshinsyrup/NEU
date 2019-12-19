package furniture.cabinet;

import furniture.accessory.CornerFeet;
import furniture.accessory.WallFixture;
import furniture.accessory.WallMountRail;
import furniture.cabinet.line.Atreides;
import furniture.cabinet.line.Luthien;
import furniture.cabinet.line.Yossarian;
import furniture.config.Fit;
import furniture.config.FloorMount;
import furniture.config.Mount;
import furniture.config.Size;
import furniture.config.WallMount;
import furniture.AbstractFurniture;
import java.util.HashSet;
import java.util.Set;
import utils.Num;
import utils.SizeType;

/**
 * Represents Cabinet, now most have common fields size and mounts.
 */
public abstract class AbstractCabinet extends AbstractFurniture {

  private Set<Mount> mounts;
  private Set<Fit> fits;

  private WallMountRail wallMountRail;
  private WallFixture wallFixture;
  private CornerFeet feet;

  /**
   * Get all cabinets in stock.
   *
   * @return
   */
  public static Set<AbstractCabinet> cabinetStock() {
    Set<AbstractCabinet> abstractCabinets = new HashSet<>();

    abstractCabinets.add(new Yossarian(SizeType.WARDROBE));
    abstractCabinets.add(new Yossarian(SizeType.HALF));
    abstractCabinets.add(new Yossarian(SizeType.QUARTER));
    abstractCabinets.add(new Luthien(SizeType.HALF));
    abstractCabinets.add(new Luthien(SizeType.QUARTER));
    abstractCabinets.add(new Atreides(SizeType.HALF));
    abstractCabinets.add(new Atreides(SizeType.QUARTER));

    return abstractCabinets;
  }

  // below is size.
  protected Size wardrobeSize() {
    return new Size(Num.SEVENTY_TWO, Num.THIRTY_SIX, Num.SIXTEEN);
  }

  protected Size halfSize() {
    return new Size(Num.THIRTY_SIX, Num.THIRTY_SIX, Num.SIXTEEN);
  }

  protected Size quarterSize() {
    return new Size(Num.EIGHTEEN, Num.THIRTY_SIX, Num.SIXTEEN);
  }

  // below is mounts.
  // Floor mounted (no feet required)
  protected Set<Mount> wardrobeMounts() {
    Set<Mount> mounts = new HashSet<>();
    mounts.add(new FloorMount(true, false, false, false));
    return mounts;
  }

  // Floor mounted (feet optional, available separately)
  // or wall mounted (standard wall mount rail required)
  protected Set<Mount> halfMounts() {
    Set<Mount> mounts = new HashSet<>();
    mounts.add(new FloorMount(true, false, true, true));
    mounts.add(new WallMount(true, true, true, false));
    return mounts;
  }

  // Floor mounted (feet required, available separately)
  // or wall mounted (standard wall mount rail required)
  protected Set<Mount> quarterMounts() {
    Set<Mount> mounts = new HashSet<>();
    mounts.add(new FloorMount(true, true, false, true));
    mounts.add(new WallMount(true, true, true, false));
    return mounts;
  }

  public Set<Fit> getFits() {
    return fits;
  }

  public void setFits(Set<Fit> fits) {
    this.fits = fits;
  }

  public Set<Mount> getMounts() {
    return mounts;
  }

  public void setMounts(Set<Mount> mounts) {
    this.mounts = mounts;
  }


  public WallFixture getWallFixture() {
    return wallFixture;
  }

  public void setWallFixture(WallFixture wallFixture) {
    this.wallFixture = wallFixture;
  }

  public WallMountRail getWallMountRail() {
    return wallMountRail;
  }

  public CornerFeet getFeet() {
    return feet;
  }

  public void setFeet(CornerFeet feet) {
    this.feet = feet;
  }

  public void setWallMountRail(WallMountRail wallMountRail) {
    this.wallMountRail = wallMountRail;
  }
}
