package assignment2_refactored.furniture.cabinet.line;

import static org.junit.Assert.assertTrue;

import assignment2_refactored.utils.SizeType;
import org.junit.Before;
import org.junit.Test;

public class LuthienTest {

  Luthien luthien;

  @Before
  public void setUp() throws Exception {
    this.luthien = new Luthien(SizeType.HALF);
  }

  @Test
  public void buildHalfSizeCabinet() {
    this.luthien.buildHalfSizeCabinet();
  }

  @Test
  public void buildQuarterSizeCabinet() {
    this.luthien.buildQuarterSizeCabinet();
  }

  @Test
  public void halfFits() {
    assertTrue(this.luthien.halfFits().size() == 2);
  }

  @Test
  public void quarterFits() {
    assertTrue(this.luthien.quarterFits().size() == 2);
  }

  @Test
  public void halfColors() {
    assertTrue(this.luthien.halfColors().size() == 2);
  }

  @Test
  public void quarterColors() {
    assertTrue(this.luthien.quarterColors().size() == 2);
  }
}