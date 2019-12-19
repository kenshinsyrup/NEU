package lab2.assignment2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class RestaurantTest {

  private Restaurant restaurant;

  @Before
  public void setUp() throws Exception {
    Address address = new Address("street and number", "city", "ZIP", "state", "country");
    Menu menu = new Menu(null, null, null, null);
    this.restaurant = new Restaurant("name", address, menu, true);
  }

  @Test
  public void getName() {
    assertTrue(this.restaurant.getName().equals("name"));
  }

  @Test
  public void setName() {
    this.restaurant.setName("name1");
    assertTrue(this.restaurant.getName().equals("name1"));
  }

  @Test
  public void getAddress() {
    Address addressCopy = new Address("street and number", "city", "ZIP", "state", "country");
    assertTrue(this.restaurant.getAddress().equals(addressCopy));
  }

  @Test
  public void setAddress() {
    Address newAddress = new Address("street and number1", "city", "ZIP", "state", "country");
    this.restaurant.setAddress(newAddress);
    assertTrue(this.restaurant.getAddress().equals(newAddress));
  }

  @Test
  public void getMenu() {
    Menu menuCopy = new Menu(null, null, null, null);
    assertTrue(this.restaurant.getMenu().equals(menuCopy));
  }

  @Test
  public void setMenu() {
    Menu newMenu = new Menu(Arrays.asList("meal"), null, null, null);
    this.restaurant.setMenu(newMenu);
    assertTrue(this.restaurant.getMenu().equals(newMenu));
  }

  @Test
  public void getOpen() {
    assertTrue(this.restaurant.getOpen());
  }

  @Test
  public void setOpen() {
    this.restaurant.setOpen(false);
    assertFalse(this.restaurant.getOpen());
  }

  @Test
  public void testEquals() {
    Address address = new Address("street and number1", "city", "ZIP", "state", "country");
    Menu menu = new Menu(Arrays.asList("meal"), null, null, null);
    Restaurant restaurant1 = new Restaurant("name", address, menu, false);
    assertTrue(restaurant1.equals(this.restaurant));
  }

  @Test
  public void testHashCode() {
    assertTrue(this.restaurant.hashCode() == 3373738);
  }

  @Test
  public void testToString() {
    assertTrue(this.restaurant.toString().equals("name"));
  }
}