package assignment2_refactored.furniture.cabinet.line;

import static org.junit.Assert.*;

import assignment2_refactored.utils.SizeType;
import org.junit.Before;
import org.junit.Test;

public class AtreidesTest {

  Atreides atreides;

  @Before
  public void setUp() throws Exception {
    this.atreides = new Atreides(SizeType.HALF);
  }

  @Test
  public void buildHalfSizeCabinet() {
    this.atreides.buildHalfSizeCabinet();
  }

  @Test
  public void buildQuarterSizeCabinet() {
    this.atreides.buildQuarterSizeCabinet();
  }

  @Test
  public void halfFits() {
    assertTrue(this.atreides.halfFits().size() == 1);
  }

  @Test
  public void quarterFits() {
    assertTrue(this.atreides.quarterFits().size() == 1);
  }

  @Test
  public void halfColors() {
    assertTrue(this.atreides.halfColors().size() == 2);
  }

  @Test
  public void quarterColors() {
    assertTrue(this.atreides.quarterColors().size() == 2);
  }
}