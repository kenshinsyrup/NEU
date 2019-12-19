package assignment2_refactored.furniture.part.line.belacqua;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import assignment2_refactored.utils.SizeType;
import org.junit.Before;
import org.junit.Test;

public class BelacquaDoorTest {

  BelacquaDoor belacquaDoor;

  @Before
  public void setUp() throws Exception {
    this.belacquaDoor = new BelacquaDoor(SizeType.WARDROBE);
  }

  @Test
  public void wardrobeHandle() {
    assertNotNull(this.belacquaDoor.wardrobeHandle());
  }

  @Test
  public void halfHandle() {
    assertNotNull(this.belacquaDoor.halfHandle());
  }

  @Test
  public void quarterHandle() {
    assertNotNull(this.belacquaDoor.quarterHandle());
  }

  @Test
  public void isDoor() {
    assertTrue(this.belacquaDoor.isDoor());
  }
}