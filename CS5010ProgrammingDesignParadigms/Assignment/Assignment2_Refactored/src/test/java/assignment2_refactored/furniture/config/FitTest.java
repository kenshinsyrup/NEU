package assignment2_refactored.furniture.config;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class FitTest {

  Fit fit;

  @Before
  public void setUp() throws Exception {
    this.fit = new Fit(1, 1);
  }

  @Test
  public void getShelfNum() {
    assertTrue(this.fit.getShelfNum() == 1);
  }

  @Test
  public void setShelfNum() {
    this.fit.setShelfNum(2);
    assertTrue(this.fit.getShelfNum() == 2);
  }

  @Test
  public void getDrawerNum() {
    assertTrue(this.fit.getDrawerNum() == 1);
  }

  @Test
  public void setDrawerNum() {
    this.fit.setDrawerNum(2);
    assertTrue(this.fit.getDrawerNum() == 2);
  }
}