package lab2.assignment2;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class MenuTest {

  private Menu menu;

  @Before
  public void setUp() throws Exception {
    this.menu = new Menu(null, null, null, null);
  }

  @Test
  public void getMeals() {
    assertNull(this.menu.getMeals());
  }

  @Test
  public void setMeals() {
    this.menu.setMeals(Arrays.asList("meal"));
    assertTrue(this.menu.getMeals().get(0).equals("meal"));
  }

  @Test
  public void getDesserts() {
    assertNull(this.menu.getDesserts());
  }

  @Test
  public void setDesserts() {
    this.menu.setDesserts(Arrays.asList("dessert"));
    assertTrue(this.menu.getDesserts().get(0).equals("dessert"));
  }

  @Test
  public void getBeverages() {
    assertNull(this.menu.getBeverages());
  }

  @Test
  public void setBeverages() {
    this.menu.setBeverages(Arrays.asList("beverage"));
    assertTrue(this.menu.getBeverages().get(0).equals("beverage"));
  }

  @Test
  public void getDrinks() {
    assertNull(this.menu.getDrinks());
  }

  @Test
  public void setDrinks() {
    this.menu.setDrinks(Arrays.asList("drinks"));
    assertTrue(this.menu.getDrinks().get(0).equals("drinks"));
  }

  @Test
  public void testEquals() {
    Menu menu1 = new Menu(Arrays.asList("meal"), Arrays.asList("dessert"),
        Arrays.asList("beverage"), Arrays.asList("drinks"));
    Menu menu2 = new Menu(Arrays.asList("meal"), Arrays.asList("dessert"),
        Arrays.asList("beverage"), Arrays.asList("drinks"));
    assertTrue(menu1.equals(menu2));
  }

  @Test
  public void testHashCode() {
    assertTrue(this.menu.hashCode() == 31);
  }

  @Test
  public void testToString() {
    System.out.println(this.menu.toString());
  }
}