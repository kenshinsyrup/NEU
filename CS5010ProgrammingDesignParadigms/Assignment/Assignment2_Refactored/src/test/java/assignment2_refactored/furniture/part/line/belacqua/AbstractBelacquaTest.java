package assignment2_refactored.furniture.part.line.belacqua;

import static org.junit.Assert.assertTrue;

import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;
import org.junit.Before;
import org.junit.Test;

public class AbstractBelacquaTest {

  AbstractBelacqua abstractBelacqua;

  @Before
  public void setUp() throws Exception {
    this.abstractBelacqua = new BelacquaDoor(SizeType.WARDROBE);
  }

  @Test
  public void wardrobeColors() {
    assertTrue(this.abstractBelacqua.wardrobeColors().size() == 2);
  }

  @Test
  public void halfColors() {
    assertTrue(this.abstractBelacqua.halfColors().size() == 2);
  }

  @Test
  public void quarterColors() {
    assertTrue(this.abstractBelacqua.quarterColors().size() == 2);
  }

  @Test
  public void wardrobeNum() {
    assertTrue(this.abstractBelacqua.wardrobeNum() == Num.TWO);
  }

  @Test
  public void halfNum() {
    assertTrue(this.abstractBelacqua.halfNum() == Num.TWO);
  }

  @Test
  public void quarterNum() {
    assertTrue(this.abstractBelacqua.quarterNum() == Num.TWO);
  }

  @Test
  public void defaultNum() {
    assertTrue(this.abstractBelacqua.defaultNum() == Num.TWO);
  }
}