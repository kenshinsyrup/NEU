package problem2;

import java.util.List;

/**
 * Represents a photographer.
 */
public class Photographer extends Artist {

  /**
   * Inits a photographer.
   *
   * @param name   is given name.
   * @param age    is given age.
   * @param genre  is given genre.
   * @param awards is given awards.
   */
  public Photographer(String name, int age, String genre, List<String> awards) {
    setName(name);
    setAge(age);
    setGenre(genre);
    setAwards(awards);
  }

}
