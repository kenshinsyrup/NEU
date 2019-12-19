package assignment2_refactored.furniture.accessory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import assignment2_refactored.furniture.accessory.handle.DrawerHandle;
import org.junit.Before;
import org.junit.Test;

/**
 * Represents accessory test.
 */
public class AbstractAccessoryTest {

  private AbstractAccessory doorHandler;

  @Before
  public void setUp() throws Exception {
    this.doorHandler = new DrawerHandle(1, true, true);
  }

  @Test
  public void isRequired() {
    assertTrue(this.doorHandler.isRequired());
  }

  @Test
  public void setRequired() {
    this.doorHandler.setRequired(false);
    assertFalse(this.doorHandler.isRequired());
  }

  @Test
  public void isSeparate() {
    assertTrue(this.doorHandler.isSeparate());
  }

  @Test
  public void setSeparate() {
    this.doorHandler.setSeparate(false);
    assertFalse(this.doorHandler.isSeparate());
  }

  @Test
  public void isStandard() {
    assertFalse(this.doorHandler.isStandard());
  }

  @Test
  public void setStandard() {
    this.doorHandler.setStandard(true);
    assertTrue(this.doorHandler.isStandard());
  }

  @Test
  public void getPrice() {
    assertEquals(0, this.doorHandler.getPrice());
  }

  @Test
  public void setPrice() {
    this.doorHandler.setPrice(10);
    assertEquals(10, this.doorHandler.getPrice());
  }

  @Test
  public void getNum() {
    assertEquals(1, this.doorHandler.getNum());
  }

  @Test
  public void setNum() {
    this.doorHandler.setNum(2);
    assertEquals(2, this.doorHandler.getNum());
  }
}