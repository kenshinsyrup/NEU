package furniture.cabinet;

import static org.junit.Assert.assertEquals;

import furniture.accessory.WallFixture;
import furniture.cabinet.line.Yossarian;
import furniture.config.Fit;
import furniture.config.FloorMount;
import furniture.config.Mount;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import utils.Num;
import utils.SizeType;


/**
 * Represents cabinet test.
 */
public class AbstractCabinetTest {

  AbstractCabinet abstractCabinet;

  @Before
  public void setUp() throws Exception {
    this.abstractCabinet = new Yossarian(SizeType.WARDROBE);
  }

  @Test
  public void cabinetStock() {
    assertEquals(7, AbstractCabinet.cabinetStock().size());
  }

  @Test
  public void getFits() {
    assertEquals(1, this.abstractCabinet.getFits().size());
  }

  @Test
  public void setFits() {
    Set<Fit> fits = new HashSet<>();
    fits.add(new Fit(Num.SEVEN, Num.ZERO));
    this.abstractCabinet.setFits(fits);
    assertEquals(fits, this.abstractCabinet.getFits());
  }

  @Test
  public void getMounts() {
    assertEquals(1, this.abstractCabinet.getMounts().size());
  }

  @Test
  public void setMounts() {
    Set<Mount> mounts = new HashSet<>();
    mounts.add(new FloorMount(true, false, false, false));
    this.abstractCabinet.setMounts(mounts);
    assertEquals(mounts, this.abstractCabinet.getMounts());
  }

  @Test
  public void getWallFixture() {
    assertEquals(1, this.abstractCabinet.getWallFixture().getNum());
  }

  @Test
  public void setWallFixture() {
    this.abstractCabinet.setWallFixture(new WallFixture(2));
    assertEquals(2, this.abstractCabinet.getWallFixture().getNum());
  }

}