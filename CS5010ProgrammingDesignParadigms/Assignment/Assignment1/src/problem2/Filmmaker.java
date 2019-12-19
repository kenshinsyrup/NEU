package problem2;

import java.util.List;

/**
 * Represents a filmmaker.
 */
public class Filmmaker extends ShowPractitioner {

  /**
   * Inits a filmmaker.
   *
   * @param name            is given name.
   * @param age             is given age.
   * @param genre           is given genre.
   * @param awards          is given awards.
   * @param movies          is given movies.
   * @param series          is given series.
   * @param otherMultimedia is given other multimedia.
   */
  public Filmmaker(String name, int age, String genre, List<String> awards, List<String> movies,
      List<String> series, List<String> otherMultimedia) {
    setName(name);
    setAge(age);
    setGenre(genre);
    setAwards(awards);
    setMovies(movies);
    setSeries(series);
    setOtherMultimedia(otherMultimedia);
  }

}
