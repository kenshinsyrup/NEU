package recommend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import furniture.config.Color;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import utils.ColorName;


/**
 * Represents criteria test.
 */
public class CriteriaTest {

  Criteria criteria;

  @Before
  public void setUp() throws Exception {
    this.criteria = new Criteria(36, 36, 0, 0, null, false, false, 0);
  }

  @Test
  public void getCabinetWidth() {
    assertEquals(36, this.criteria.getCabinetWidth());
  }

  @Test
  public void setCabinetWidth() {
    this.criteria.setCabinetWidth(72);
    assertEquals(72, this.criteria.getCabinetWidth());
  }

  @Test
  public void getCabinetHeight() {
    assertEquals(36, this.criteria.getCabinetHeight());
  }

  @Test
  public void setCabinetHeight() {
    this.criteria.setCabinetHeight(72);
    assertEquals(72, this.criteria.getCabinetHeight());
  }

  @Test
  public void getCabinetShelfNum() {
    assertEquals(0, this.criteria.getCabinetShelfNum());
  }

  @Test
  public void setCabinetShelfNum() {
    this.criteria.setCabinetShelfNum(1);
    assertEquals(1, this.criteria.getCabinetShelfNum());
  }

  @Test
  public void getCabinetDrawerNum() {
    assertEquals(0, this.criteria.getCabinetDrawerNum());
  }

  @Test
  public void setCabinetDrawerNum() {
    this.criteria.setCabinetDrawerNum(1);
    assertEquals(1, this.criteria.getCabinetDrawerNum());
  }

  @Test
  public void getColors() {
    assertNull(this.criteria.getColors());
  }

  @Test
  public void setColors() {
    Set<Color> colors = new HashSet<>();
    colors.add(new Color(ColorName.BLACK));
    this.criteria.setColors(colors);
    assertEquals(colors, this.criteria.getColors());
  }

  @Test
  public void isFloorMounted() {
    assertFalse(this.criteria.isFloorMounted());
  }

  @Test
  public void setFloorMounted() {
    this.criteria.setFloorMounted(true);
    assertTrue(this.criteria.isFloorMounted());
  }

  @Test
  public void isWallMounted() {
    assertFalse(this.criteria.isWallMounted());
  }

  @Test
  public void setWallMounted() {
    this.criteria.setWallMounted(true);
    assertTrue(this.criteria.isWallMounted());
  }

  @Test
  public void getBudget() {
    assertEquals(0, this.criteria.getBudget());
  }

  @Test
  public void setBudget() {
    this.criteria.setBudget(1);
    assertEquals(1, this.criteria.getBudget());
  }
}