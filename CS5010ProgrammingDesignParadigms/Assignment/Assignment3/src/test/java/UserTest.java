import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

  private User user;

  @Before
  public void setUp() throws Exception {
    this.user = new User("first_name", "last_name", "company_name",
        "address", "city", "country", "state", "zip", "phone1", "phone2",
        "email", "web");
  }

  @Test
  public void hashcode() {
    assertTrue(user.hashcode() == 1137975798);
  }

  @Test
  public void testEquals() {
    User user = new User("first_name", "last_name", "company_name",
        "address", "city", "country", "state", "zip", "phone1", "phone2",
        "email", "web");
    assertTrue(this.user.equals(user));
  }

  @Test
  public void testToString() {
    assertTrue(this.user.toString().equals(
        "first_name last_name company_name address city country state zip phone1 phone2 email web"));
  }

  @Test
  public void getFirst_name() {
    assertTrue(this.user.getFirst_name().equals("first_name"));
  }

  @Test
  public void setFirst_name() {
    this.user.setFirst_name("first_name1");
    assertTrue(this.user.getFirst_name().equals("first_name1"));
  }

  @Test
  public void getLast_name() {
    assertTrue(this.user.getLast_name().equals("last_name"));
  }

  @Test
  public void setLast_name() {
    this.user.setLast_name("last_name1");
    assertTrue(this.user.getLast_name().equals("last_name1"));
  }

  @Test
  public void getCompany_name() {
    assertTrue(this.user.getCompany_name().equals("company_name"));
  }

  @Test
  public void setCompany_name() {
    this.user.setCompany_name("company_name1");
    assertTrue(this.user.getCompany_name().equals("company_name1"));
  }

  @Test
  public void getAddress() {
    assertTrue(this.user.getAddress().equals("address"));
  }

  @Test
  public void setAddress() {
    this.user.setAddress("address1");
    assertTrue(this.user.getAddress().equals("address1"));
  }

  @Test
  public void getCity() {
    assertTrue(this.user.getCity().equals("city"));
  }

  @Test
  public void setCity() {
    this.user.setCity("city1");
    assertTrue(this.user.getCity().equals("city1"));
  }

  @Test
  public void getCountry() {
    assertTrue(this.user.getCountry().equals("country"));
  }

  @Test
  public void setCountry() {
    this.user.setCountry("country1");
    assertTrue(this.user.getCountry().equals("country1"));
  }

  @Test
  public void getState() {
    assertTrue(this.user.getState().equals("state"));
  }

  @Test
  public void setState() {
    this.user.setState("state1");
    assertTrue(this.user.getState().equals("state1"));
  }

  @Test
  public void getZip() {
    assertTrue(this.user.getZip().equals("zip"));
  }

  @Test
  public void setZip() {
    this.user.setZip("zip1");
    assertTrue(this.user.getZip().equals("zip1"));
  }

  @Test
  public void getPhone1() {
    assertTrue(this.user.getPhone1().equals("phone1"));
  }

  @Test
  public void setPhone1() {
    this.user.setPhone1("phone11");
    assertTrue(this.user.getPhone1().equals("phone11"));
  }

  @Test
  public void getPhone2() {
    assertTrue(this.user.getPhone2().equals("phone2"));
  }

  @Test
  public void setPhone2() {
    this.user.setPhone2("phone21");
    assertTrue(this.user.getPhone2().equals("phone21"));
  }

  @Test
  public void getEmail() {
    assertTrue(this.user.getEmail().equals("email"));
  }

  @Test
  public void setEmail() {
    this.user.setEmail("email1");
    assertTrue(this.user.getEmail().equals("email1"));
  }

  @Test
  public void getWeb() {
    assertTrue(this.user.getWeb().equals("web"));
  }

  @Test
  public void setWeb() {
    this.user.setWeb("web1");
    assertTrue(this.user.getWeb().equals("web1"));
  }
}