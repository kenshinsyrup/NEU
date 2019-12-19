package problem2;

import java.util.List;

/**
 * Represents practitioner working in show areas, like actor, dancer or filmmaker.
 */
public abstract class ShowPractitioner extends Artist {

  private List<String> movies;
  private List<String> series;
  private List<String> otherMultimedia;

  /**
   * Gets movies.
   *
   * @return
   */
  public List<String> getMovies() {
    return movies;
  }

  /**
   * Sets movies.
   *
   * @param movies is given movies.
   */
  public void setMovies(List<String> movies) {
    if (isValidMediaList(movies)) {
      this.movies = movies;
    }
  }

  /**
   * Gets series.
   *
   * @return
   */
  public List<String> getSeries() {
    return series;
  }

  /**
   * Sets series.
   *
   * @param series is given series.
   */
  public void setSeries(List<String> series) {
    if (isValidMediaList(series)) {
      this.series = series;
    }
  }

  /**
   * Gets other multimedia.
   *
   * @return
   */
  public List<String> getOtherMultimedia() {
    return otherMultimedia;
  }

  /**
   * Sets other multimedia.
   *
   * @param otherMultimedia is given other multimedia.
   */
  public void setOtherMultimedia(List<String> otherMultimedia) {
    if (isValidMediaList(otherMultimedia)) {
      this.otherMultimedia = otherMultimedia;
    }
  }

  // Checks if given mediaList is valid.
  private boolean isValidMediaList(List<String> mediaList) {
    return !(mediaList == null || mediaList.size() == 0);
  }
}
