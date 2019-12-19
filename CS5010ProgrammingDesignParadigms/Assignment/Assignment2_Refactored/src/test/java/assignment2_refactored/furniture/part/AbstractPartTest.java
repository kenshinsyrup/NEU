package assignment2_refactored.furniture.part;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import assignment2_refactored.furniture.accessory.Hinge;
import assignment2_refactored.furniture.accessory.handle.DoorHandle;
import assignment2_refactored.furniture.accessory.handle.DrawerHandle;
import assignment2_refactored.furniture.part.line.belacqua.BelacquaDoor;
import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;
import org.junit.Before;
import org.junit.Test;

/**
 * Represents part test.
 */
public class AbstractPartTest {

  AbstractPart abstractPart;

  @Before
  public void setUp() throws Exception {
    this.abstractPart = new BelacquaDoor(SizeType.WARDROBE);
  }

  @Test
  public void partStock() {
    assertEquals(8, AbstractPart.partStock().size());
  }


  @Test
  public void wardrobeSize() {
    assertTrue(this.abstractPart.wardrobeSize().getHeight() == Num.SEVENTY_TWO);
  }

  @Test
  public void halfSize() {
    assertTrue(this.abstractPart.halfSize().getHeight() == Num.THIRTY_SIX);
  }

  @Test
  public void quarterSize() {
    assertTrue(this.abstractPart.quarterSize().getHeight() == Num.EIGHTEEN);
  }

  @Test
  public void getHinge() {
    assertNull(this.abstractPart.getHinge());
  }

  @Test
  public void setHinge() {
    this.abstractPart.setHinge(new Hinge(1));
    assertNotNull(this.abstractPart.getHinge());
  }

  @Test
  public void getNum() {
    assertEquals(2, this.abstractPart.getNum());
  }

  @Test
  public void setNum() {
    this.abstractPart.setNum(3);
    assertEquals(3, this.abstractPart.getNum());
  }

  @Test
  public void getDoorHandle() {
    assertEquals(1, this.abstractPart.getDoorHandle().getNum());
  }

  @Test
  public void setDoorHandle() {
    this.abstractPart.setDoorHandle(new DoorHandle(2, true, true));
    assertEquals(2, this.abstractPart.getDoorHandle().getNum());
  }

  @Test
  public void setDrawerHandle() {
    this.abstractPart.setDrawerHandle(new DrawerHandle(1, true, true));
    assertEquals(1, this.abstractPart.getDrawerHandle().getNum());
  }

  @Test
  public void getDrawerHandle() {
    assertNull(this.abstractPart.getDrawerHandle());
  }
}