package lab2.assignment2;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AddressTest {

  private Address address;

  @Before
  public void setUp() throws Exception {
    this.address = new Address("street and number", "city", "ZIP", "state", "country");
  }

  @Test
  public void getStreetAndNumber() {
    assertTrue(this.address.getStreetAndNumber().equals("street and number"));
  }

  @Test
  public void setStreetAndNumber() {
    this.address.setStreetAndNumber("street and number1");
    assertTrue(this.address.getStreetAndNumber().equals("street and number1"));
  }

  @Test
  public void getCity() {
    assertTrue(this.address.getCity().equals("city"));
  }

  @Test
  public void setCity() {
    this.address.setCity("city1");
    assertTrue(this.address.getCity().equals("city1"));
  }

  @Test
  public void getzIPCode() {
    assertTrue(this.address.getzIPCode().equals("ZIP"));
  }

  @Test
  public void setzIPCode() {
    this.address.setzIPCode("ZIP1");
    assertTrue(this.address.getzIPCode().equals("ZIP1"));
  }

  @Test
  public void getState() {
    assertTrue(this.address.getState().equals("state"));
  }

  @Test
  public void setState() {
    this.address.setState("state1");
    assertTrue(this.address.getState().equals("state1"));
  }

  @Test
  public void getCountry() {
    assertTrue(this.address.getCountry().equals("country"));
  }

  @Test
  public void setCountry() {
    this.address.setCountry("country1");
    assertTrue(this.address.getCountry().equals("country1"));
  }

  @Test
  public void testEquals() {
    Address addressCopy = new Address("street and number", "city", "ZIP", "state", "country");
    assertTrue(this.address.equals(addressCopy));
  }

  @Test
  public void testHashCode() {
    assertTrue(this.address.hashCode() == -38448626);
  }

  @Test
  public void testToString() {
    assertTrue(this.address.toString().equals("street and number"));
  }
}