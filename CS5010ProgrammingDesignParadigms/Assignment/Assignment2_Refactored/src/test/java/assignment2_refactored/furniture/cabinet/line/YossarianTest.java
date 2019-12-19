package assignment2_refactored.furniture.cabinet.line;

import static org.junit.Assert.assertTrue;

import assignment2_refactored.utils.SizeType;
import org.junit.Before;
import org.junit.Test;

public class YossarianTest {

  Yossarian yossarian;

  @Before
  public void setUp() throws Exception {
    this.yossarian = new Yossarian(SizeType.WARDROBE);
  }

  @Test
  public void buildWardrobeSizeCabinet() {
    this.yossarian.buildWardrobeSizeCabinet();
  }

  @Test
  public void buildHalfSizeCabinet() {
    this.yossarian.buildHalfSizeCabinet();
  }

  @Test
  public void buildQuarterSizeCabinet() {
    this.yossarian.buildQuarterSizeCabinet();
  }

  @Test
  public void wardrobeFits() {
    assertTrue(this.yossarian.wardrobeFits().size() == 1);
  }

  @Test
  public void halfFits() {
    assertTrue(this.yossarian.halfFits().size() == 1);
  }

  @Test
  public void quarterFits() {
    assertTrue(this.yossarian.quarterFits().size() == 1);
  }

  @Test
  public void wardrobeColors() {
    assertTrue(this.yossarian.wardrobeColors().size() == 4);
  }

  @Test
  public void halfColors() {
    assertTrue(this.yossarian.halfColors().size() == 3);
  }

  @Test
  public void quarterColors() {
    assertTrue(this.yossarian.quarterColors().size() == 3);
  }
}