package assignment2_refactored.furniture.config;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SizeTest {

  Size size;

  @Before
  public void setUp() throws Exception {
    this.size = new Size(1, 2, 3);
  }

  @Test
  public void getHeight() {
    assertTrue(this.size.getHeight() == 1);
  }

  @Test
  public void setHeight() {
    this.size.setHeight(10);
    assertTrue(this.size.getHeight() == 10);
  }

  @Test
  public void getWidth() {
    assertTrue(this.size.getWidth() == 2);
  }

  @Test
  public void setWidth() {
    this.size.setWidth(20);
    assertTrue(this.size.getWidth() == 20);
  }

  @Test
  public void getDepth() {
    assertTrue(this.size.getDepth() == 3);
  }

  @Test
  public void setDepth() {
    this.size.setDepth(30);
    assertTrue(this.size.getDepth() == 30);
  }
}