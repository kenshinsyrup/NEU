package furniture.part;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import furniture.accessory.handle.DoorAbstractHandle;
import furniture.accessory.handle.DrawerAbstractHandle;
import furniture.part.line.belacqua.AbstractBelacquaDoor;
import org.junit.Before;
import org.junit.Test;
import utils.SizeType;


/**
 * Represents part test.
 */
public class AbstractPartTest {

  AbstractPart abstractPart;

  @Before
  public void setUp() throws Exception {
    this.abstractPart = new AbstractBelacquaDoor(SizeType.WARDROBE);
  }

  @Test
  public void partStock() {
    assertEquals(8, AbstractPart.partStock().size());
  }

  @Test
  public void getDoorHandle() {
    assertEquals(1, this.abstractPart.getDoorAbstractHandle().getNum());
  }

  @Test
  public void setDoorHandle() {
    this.abstractPart.setDoorAbstractHandle(new DoorAbstractHandle(2, true, true));
    assertEquals(2, this.abstractPart.getDoorAbstractHandle().getNum());
  }

  @Test
  public void setDrawerHandle() {
    this.abstractPart.setDrawerAbstractHandle(new DrawerAbstractHandle(1, true, true));
    assertEquals(1, this.abstractPart.getDrawerAbstractHandle().getNum());
  }

  @Test
  public void getDrawerHandle() {
    assertNull(this.abstractPart.getDrawerAbstractHandle());
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
}