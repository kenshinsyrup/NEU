package assignment2_refactored.furniture.part.line.slothrop;

import static org.junit.Assert.assertTrue;

import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;
import org.junit.Before;
import org.junit.Test;

public class AbstractSlothropTest {

  AbstractSlothrop abstractSlothrop;

  @Before
  public void setUp() throws Exception {
    this.abstractSlothrop = new SlothropDoor(SizeType.HALF);
  }

  @Test
  public void wardrobeColors() {
    assertTrue(this.abstractSlothrop.wardrobeColors().size() == 2);
  }

  @Test
  public void halfColors() {
    assertTrue(this.abstractSlothrop.halfColors().size() == 2);
  }

  @Test
  public void wardrobeNum() {
    assertTrue(this.abstractSlothrop.wardrobeNum() == Num.ONE);
  }

  @Test
  public void halfNum() {
    assertTrue(this.abstractSlothrop.halfNum() == Num.ONE);
  }

  @Test
  public void defaultNum() {
    assertTrue(this.abstractSlothrop.defaultNum() == Num.ONE);
  }
}