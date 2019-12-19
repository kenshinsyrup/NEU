package problem2;

import java.util.List;

/**
 * Represents a poet.
 */
public class Poet extends Artist {

  /**
   * Inits a poet.
   *
   * @param name   is given name.
   * @param age    is given age.
   * @param genre  is given genre.
   * @param awards is given awards.
   */
  public Poet(String name, int age, String genre, List<String> awards) {
    setName(name);
    setAge(age);
    setGenre(genre);
    setAwards(awards);
  }

}
