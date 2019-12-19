package assignment2_refactored.furniture.part.line.gaga;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import assignment2_refactored.utils.SizeType;
import org.junit.Before;
import org.junit.Test;

public class GagaDoorTest {

  GagaDoor gagaDoor;

  @Before
  public void setUp() throws Exception {
    this.gagaDoor = new GagaDoor(SizeType.HALF);
  }

  @Test
  public void halfHandle() {
    assertNotNull(this.gagaDoor.halfHandle());
  }

  @Test
  public void quarterHandle() {
    assertNotNull(this.gagaDoor.quarterHandle());
  }

  @Test
  public void isDoor() {
    assertTrue(this.gagaDoor.isDoor());
  }
}