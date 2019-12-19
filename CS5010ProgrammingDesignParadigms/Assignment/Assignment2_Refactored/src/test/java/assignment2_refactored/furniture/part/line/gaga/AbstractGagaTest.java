package assignment2_refactored.furniture.part.line.gaga;

import static org.junit.Assert.assertTrue;

import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;
import org.junit.Before;
import org.junit.Test;

public class AbstractGagaTest {

  AbstractGaga abstractGaga;

  @Before
  public void setUp() throws Exception {
    this.abstractGaga = new GagaDoor(SizeType.HALF);
  }

  @Test
  public void halfColors() {
    assertTrue(this.abstractGaga.halfColors().size() == 4);
  }

  @Test
  public void quarterColors() {
    assertTrue(this.abstractGaga.quarterColors().size() == 4);
  }

  @Test
  public void halfNum() {
    assertTrue(this.abstractGaga.halfNum() == Num.ONE);
  }

  @Test
  public void quarterNum() {
    assertTrue(this.abstractGaga.quarterNum() == Num.ONE);
  }

  @Test
  public void defaultNum() {
    assertTrue(this.abstractGaga.defaultNum() == Num.ONE);
  }
}