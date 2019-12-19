package lab2.assignment2;

import java.util.List;
import java.util.Objects;

/**
 * Represents a Menu.
 */
public class Menu {

  private List<String> meals;
  private List<String> desserts;
  private List<String> beverages;
  private List<String> drinks;

  /**
   * Gets meals.
   *
   * @return meals.
   */
  public List<String> getMeals() {
    return meals;
  }

  /**
   * Sets meals.
   *
   * @param meals is given meals.
   */
  public void setMeals(List<String> meals) {
    this.meals = meals;
  }

  /**
   * Gets desserts.
   *
   * @return desserts.
   */
  public List<String> getDesserts() {
    return desserts;
  }

  /**
   * Set desserts.
   *
   * @param desserts is given desserts.
   */
  public void setDesserts(List<String> desserts) {
    this.desserts = desserts;
  }

  /**
   * Gets beverages.
   *
   * @return beverages.
   */
  public List<String> getBeverages() {
    return beverages;
  }

  /**
   * Sets beverages.
   *
   * @param beverages is given beverages.
   */
  public void setBeverages(List<String> beverages) {
    this.beverages = beverages;
  }

  /**
   * Gets drinks.
   *
   * @return drinks.
   */
  public List<String> getDrinks() {
    return drinks;
  }

  /**
   * Sets drinks.
   *
   * @param drinks is given drinks.
   */
  public void setDrinks(List<String> drinks) {
    this.drinks = drinks;
  }

  /**
   * Inits a Menu.
   *
   * @param meals     is given meals.
   * @param desserts  is given desserts.
   * @param beverages is given beverages.
   * @param drinks    is given drinks.
   */
  public Menu(List<String> meals, List<String> desserts, List<String> beverages,
      List<String> drinks) {
    this.meals = meals;
    this.desserts = desserts;
    this.beverages = beverages;
    this.drinks = drinks;
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
    return Objects.hash(this.meals);
  }

  /**
   * To String.
   *
   * @return name string.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (this.meals != null) {
      for (int i = 0; i < this.meals.size(); i++) {
        sb.append(this.meals.get(i));
      }

    }

    return sb.toString();
  }

}
