package assignment2_refactored.furniture.part.line.gaga;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import assignment2_refactored.utils.SizeType;
import org.junit.Before;
import org.junit.Test;

public class GagaDrawerTest {

  GagaDrawer gagaDrawer;

  @Before
  public void setUp() throws Exception {
    this.gagaDrawer = new GagaDrawer(SizeType.QUARTER);
  }

  @Test
  public void quarterHandle() {
    assertNotNull(this.gagaDrawer.quarterHandle());
  }

  @Test
  public void isDrawer() {
    assertTrue(this.gagaDrawer.isDrawer());
  }
}