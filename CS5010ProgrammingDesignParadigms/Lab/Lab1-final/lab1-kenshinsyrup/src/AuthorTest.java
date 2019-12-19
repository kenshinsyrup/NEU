import org.junit.Assert;

/**
 * Tests the class Author.
 */
public class AuthorTest {

  private Author jane;

  /**
   * Sets up the variables needed for test.
   */
  @org.junit.Before
  public void setUp() throws Exception {

    this.jane = new Author("Jane Doe", "j@a.com", "222 Main St, Seattle, WA, 98980");
  }

  /**
   * Tests getName method.
   */
  @org.junit.Test
  public void getName() throws Exception {

    Assert.assertEquals("Jane Doe", this.jane.getName());
  }

  /**
   * Tests getEmail method.
   */
  @org.junit.Test
  public void getEmail() throws Exception {
    Assert.assertEquals("j@a.com", this.jane.getEmail());
  }

  /**
   * Tests getAddress method.
   */
  @org.junit.Test
  public void getAddress() throws Exception {
    Assert.assertEquals("222 Main St, Seattle, WA, 98980", this.jane.getAddress());
  }
}