package problem2;

import java.util.List;

/**
 * Represents a painter.
 */
public class Painter extends Artist {

  /**
   * Inits a painter.
   *
   * @param name   is given name.
   * @param age    is given age.
   * @param genre  is given genre.
   * @param awards is given awards.
   */
  public Painter(String name, int age, String genre, List<String> awards) {
    setName(name);
    setAge(age);
    setGenre(genre);
    setAwards(awards);
  }

}
