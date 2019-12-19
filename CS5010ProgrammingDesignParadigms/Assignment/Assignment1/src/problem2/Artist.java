package problem2;

import java.util.List;

/**
 * Represents a artist.
 */
public abstract class Artist {

  private String name;
  private int age;
  private String genre;
  private List<String> awards;

  /**
   * Gets name of artist.
   *
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name of artist.
   *
   * @param name is given name.
   */
  public void setName(String name) {
    if (isValidName(name)) {
      this.name = name;
    }
  }

  /**
   * Gets age of artist.
   *
   * @return
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets age of artist.
   *
   * @param age is given age.
   */
  public void setAge(int age) {
    if (isValidAge(age)) {
      this.age = age;
    }
  }

  /**
   * Gets genre of artist.
   *
   * @return
   */
  public String getGenre() {
    return genre;
  }

  /**
   * Sets genre of artist.
   *
   * @param genre is given genre.
   */
  public void setGenre(String genre) {
    if (isValidGenre(genre)) {
      this.genre = genre;
    }
  }

  /**
   * Gets awards of artist.
   *
   * @return
   */
  public List<String> getAwards() {
    return awards;
  }

  /**
   * Sets awards of artist.
   *
   * @param awards is given awards.
   */
  public void setAwards(List<String> awards) {
    if (isValidAwards(awards)) {
      this.awards = awards;
    }
  }

  // Checks if given name is valid.
  private boolean isValidName(String name) {
    return !(name == null || name.length() == 0);
  }

  // Checks if given age is valid.
  private boolean isValidAge(int age) {
    return !(age <= 0 || age > 200);
  }

  // Checks if given genre is valid.
  private boolean isValidGenre(String genre) {
    return !(genre == null || genre.length() == 0);
  }

  // Checks if given awards if valid.
  private boolean isValidAwards(List<String> awards) {
    return !(awards == null || awards.size() == 0);
  }
}
