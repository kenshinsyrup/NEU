package problem2;

import java.util.List;

/**
 * Represents a musician.
 */
public class Musician extends Artist {

  /**
   * Inits a musician.
   *
   * @param name   is given name.
   * @param age    is given age.
   * @param genre  is given genre.
   * @param awards is given awards.
   */
  public Musician(String name, int age, String genre, List<String> awards) {
    setName(name);
    setAge(age);
    setGenre(genre);
    setAwards(awards);
  }

}
