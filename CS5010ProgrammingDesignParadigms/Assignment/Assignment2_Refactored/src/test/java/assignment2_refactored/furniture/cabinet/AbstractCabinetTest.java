package assignment2_refactored.furniture.cabinet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import assignment2_refactored.furniture.accessory.CornerFeet;
import assignment2_refactored.furniture.accessory.WallFixture;
import assignment2_refactored.furniture.accessory.WallMountRail;
import assignment2_refactored.furniture.cabinet.line.Yossarian;
import assignment2_refactored.furniture.config.AbstractMount;
import assignment2_refactored.furniture.config.Fit;
import assignment2_refactored.furniture.config.FloorMount;
import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

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
  public void wardrobeSize() {
    assertTrue(this.abstractCabinet.wardrobeSize().getHeight() == Num.SEVENTY_TWO);
  }

  @Test
  public void halfSize() {
    assertTrue(this.abstractCabinet.halfSize().getHeight() == Num.THIRTY_SIX);
  }

  @Test
  public void quarterSize() {
    assertTrue(this.abstractCabinet.quarterSize().getHeight() == Num.EIGHTEEN);
  }

  @Test
  public void wardrobeMounts() {
    assertTrue(this.abstractCabinet.wardrobeMounts().size() == 1);
  }

  @Test
  public void halfMounts() {
    assertTrue(this.abstractCabinet.halfMounts().size() == 2);
  }

  @Test
  public void quarterMounts() {
    assertTrue(this.abstractCabinet.quarterMounts().size() == 2);
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
    Set<AbstractMount> mounts = new HashSet<>();
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

  @Test
  public void getFeet() {
    assertNull(this.abstractCabinet.getFeet());
  }

  @Test
  public void setFeet() {
    this.abstractCabinet.setFeet(new CornerFeet(1));
    assertNotNull(this.abstractCabinet.getFeet());
  }

  @Test
  public void getWallMountRail() {
    assertNull(this.abstractCabinet.getWallMountRail());
  }

  @Test
  public void setWallMountRail() {
    this.abstractCabinet.setWallMountRail(new WallMountRail(1, false, false, false));
    assertNotNull(this.abstractCabinet.getWallMountRail());
  }
}