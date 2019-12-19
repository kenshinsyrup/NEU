import java.util.Objects;

/**
 * Represents a User.
 */
public class User {

  private String first_name;
  private String last_name;
  private String company_name;
  private String address;
  private String city;
  private String country;
  private String state;
  private String zip;
  private String phone1;
  private String phone2;
  private String email;
  private String web;

  /**
   * Init a user.
   *
   * @param first_name   is given first_name.
   * @param last_name    is given last_name.
   * @param company_name is given company_name.
   * @param address      is given address.
   * @param city         is given city.
   * @param country      is given country.
   * @param state        is given state.
   * @param zip          is given zip.
   * @param phone1       is given phone1.
   * @param phone2       is given phone2.
   * @param email        is given email.
   * @param web          is given web.
   */
  public User(String first_name, String last_name, String company_name, String address, String city,
      String country, String state, String zip, String phone1, String phone2, String email,
      String web) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.company_name = company_name;
    this.address = address;
    this.city = city;
    this.country = country;
    this.state = state;
    this.zip = zip;
    this.phone1 = phone1;
    this.phone2 = phone2;
    this.email = email;
    this.web = web;
  }

  /**
   * Hash code.
   *
   * @return hashcode.
   */
  public int hashcode() {
    return Objects.hash(this.first_name, this.last_name, this.company_name, this.address, this.city,
        this.country, this.state, this.zip, this.phone1, this.phone2, this.email, this.web);
  }

  /**
   * Compare.
   *
   * @param user is given user.
   * @return true if equal.
   */
  public boolean equals(User user) {
    return this.hashcode() == user.hashcode();
  }

  /**
   * To string.
   *
   * @return string.
   */
  public String toString() {
    return this.first_name + " " + this.last_name + " " + this.company_name + " " + this.address
        + " " + this.city + " " +
        this.country + " " + this.state + " " + this.zip + " " + this.phone1 + " " + this.phone2
        + " " + this.email + " " + this.web;
  }

  /**
   * Gets first name.
   *
   * @return first_name
   */
  public String getFirst_name() {
    return first_name;
  }

  /**
   * Sets first name.
   *
   * @param first_name is given first_name.
   */
  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  /**
   * Gets last name.
   *
   * @return last_name.
   */
  public String getLast_name() {
    return last_name;
  }

  /**
   * Sets last_name.
   *
   * @param last_name is given last_name.
   */
  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  /**
   * Gets company name.
   *
   * @return company_name.
   */
  public String getCompany_name() {
    return company_name;
  }

  /**
   * Sets company_name.
   *
   * @param company_name is given company_name
   */
  public void setCompany_name(String company_name) {
    this.company_name = company_name;
  }

  /**
   * Gets address.
   *
   * @return is address.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets address.
   *
   * @param address is given address.
   */
  public void setAddress(String address) {
    this.address = address;
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
   * Set city.
   *
   * @param city is given city.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Gets country.
   *
   * @return is country.
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
   * Gets zip.
   *
   * @return zip.
   */
  public String getZip() {
    return zip;
  }

  /**
   * Sets zip.
   *
   * @param zip is given zip.
   */
  public void setZip(String zip) {
    this.zip = zip;
  }

  /**
   * Gets phone1.
   *
   * @return phone1.
   */
  public String getPhone1() {
    return phone1;
  }

  /**
   * Sets phone1.
   *
   * @param phone1 is given phone1.
   */
  public void setPhone1(String phone1) {
    this.phone1 = phone1;
  }

  /**
   * Gets phone2.
   *
   * @return phone2.
   */
  public String getPhone2() {
    return phone2;
  }

  /**
   * Sets phone2.
   *
   * @param phone2 is given phone2.
   */
  public void setPhone2(String phone2) {
    this.phone2 = phone2;
  }

  /**
   * Gets email.
   *
   * @return email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email.
   *
   * @param email is given email.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets web.
   *
   * @return web.
   */
  public String getWeb() {
    return web;
  }

  /**
   * Sets web.
   *
   * @param web is given web.
   */
  public void setWeb(String web) {
    this.web = web;
  }

}
