package lab2.assignment2;

import java.util.Objects;

/**
 * Represents a Restaurant.
 */
public class Restaurant {

  /**
   * Gets name.
   *
   * @return name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name is given name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets address.
   *
   * @return address.
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Sets address.
   *
   * @param address is given address.
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  /**
   * Gets menu.
   *
   * @return menu.
   */
  public Menu getMenu() {
    return menu;
  }

  /**
   * Sets menu.
   *
   * @param menu is given menu.
   */
  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  /**
   * Gets open.
   *
   * @return is true if open.
   */
  public Boolean getOpen() {
    return open;
  }

  /**
   * Sets open.
   *
   * @param open is given open.
   */
  public void setOpen(Boolean open) {
    this.open = open;
  }

  private String name;
  private Address address;
  private Menu menu;
  private Boolean open;

  /**
   * Inits a Restaurant.
   *
   * @param name    is given name.
   * @param address is given address.
   * @param menu    is given menu.
   * @param open    is given open.
   */
  public Restaurant(String name, Address address, Menu menu, Boolean open) {
    this.name = name;
    this.address = address;
    this.menu = menu;
    this.open = open;
  }

  /**
   * Compare.
   *
   * @param o is object to compare.
   * @return true if equal.
   */
  public boolean equals(Object o) {
    return this.hashCode() == o.hashCode();
  }

  /**
   * Hash Code.
   *
   * @return hash code by name.
   */
  public int hashCode() {
    return Objects.hash(this.name);
  }

  /**
   * To String.
   *
   * @return name string.
   */
  public String toString() {
    return this.name;
  }
}
