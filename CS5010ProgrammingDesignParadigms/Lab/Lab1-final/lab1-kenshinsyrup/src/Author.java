/**
 * Represents an Author with their details--name, email and physical address.
 *
 * @author Zhao Wang
 */
public class Author {

  private String name;
  private String email;
  private String address;

  /**
   * Creates a new author given the author's name, email and address as strings.
   *
   * @param name    the author's name
   * @param email   the author's email address
   * @param address the author's physical address
   */
  public Author(String name, String email, String address) {
    this.name = name;
    this.email = email;
    this.address = address;
  }

  /**
   * Gets the name of author.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the email of author.
   *
   * @return the email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Gets the address of author.
   *
   * @return the address
   */
  public String getAddress() {
    return this.address;
  }
}