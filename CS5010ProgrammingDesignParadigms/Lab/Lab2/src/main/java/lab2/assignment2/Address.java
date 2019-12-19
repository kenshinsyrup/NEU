package lab2.assignment2;

import java.util.Objects;

/**
 * Represents a Address.
 */
public class Address {

  /**
   * gets streetAndNumber.
   *
   * @return is streetAndNumber.
   */
  public String getStreetAndNumber() {
    return streetAndNumber;
  }

  /**
   * Sets streetAndNumber.
   *
   * @param streetAndNumber is given streetAndNumber.
   */
  public void setStreetAndNumber(String streetAndNumber) {
    this.streetAndNumber = streetAndNumber;
  }

  /**
   * Gets city.
   *
   * @return city.
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets city.
   *
   * @param city is given city.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Gets ZIP code.
   *
   * @return ZIP code.
   */
  public String getzIPCode() {
    return zIPCode;
  }

  /**
   * Sets ZIP code.
   *
   * @param zIPCode is given ZIP code.
   */
  public void setzIPCode(String zIPCode) {
    this.zIPCode = zIPCode;
  }

  /**
   * Gets state.
   *
   * @return state.
   */
  public String getState() {
    return state;
  }

  /**
   * Sets state.
   *
   * @param state is given state.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Gets country.
   *
   * @return country.
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets country.
   *
   * @param country is given country.
   */
  public void setCountry(String country) {
    this.country = country;
  }

  private String streetAndNumber;
  private String city;
  private String zIPCode;
  private String state;
  private String country;

  /**
   * Inits a Address.
   *
   * @param streetAndNumber is streetAndNumber.
   * @param city            is city.
   * @param zIPCode         is zIPCode.
   * @param state           is state.
   * @param country         is country.
   */
  public Address(String streetAndNumber, String city, String zIPCode, String state,
      String country) {
    this.streetAndNumber = streetAndNumber;
    this.city = city;
    this.zIPCode = zIPCode;
    this.state = state;
    this.country = country;
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
    return Objects.hash(this.streetAndNumber);
  }

  /**
   * To String.
   *
   * @return name string.
   */
  public String toString() {
    return this.streetAndNumber;
  }

}
