package lab2.assignment1;

/**
 * Represents a Runner.
 */
public class Runner extends Athlete {

  private Double best5KTIme;
  private Double bestHalfMarathonTime;

  /**
   * Gets best5KTIme.
   *
   * @return best5KTIme.
   */
  public Double getBest5KTIme() {
    return best5KTIme;
  }

  /**
   * Sets best5KTIme.
   *
   * @param best5KTIme is given best5KTIme.
   */
  public void setBest5KTIme(Double best5KTIme) {
    this.best5KTIme = best5KTIme;
  }

  /**
   * Gets bestHalfMarathonTime.
   *
   * @return is bestHalfMarathonTime.
   */
  public Double getBestHalfMarathonTime() {
    return bestHalfMarathonTime;
  }

  /**
   * Sets bestHalfMarathonTime.
   *
   * @param bestHalfMarathonTime is given bestHalfMarathonTime.
   */
  public void setBestHalfMarathonTime(Double bestHalfMarathonTime) {
    this.bestHalfMarathonTime = bestHalfMarathonTime;
  }

  /**
   * Gets favoriteRunningEvent.
   *
   * @return is favoriteRunningEvent.
   */
  public String getFavoriteRunningEvent() {
    return favoriteRunningEvent;
  }

  /**
   * Sets favoriteRunningEvent.
   *
   * @param favoriteRunningEvent is given favoriteRunningEvent.
   */
  public void setFavoriteRunningEvent(String favoriteRunningEvent) {
    this.favoriteRunningEvent = favoriteRunningEvent;
  }

  private String favoriteRunningEvent;

  /**
   * Init a Runner.
   *
   * @param athletesName         is name.
   * @param height               is height.
   * @param weight               is weight.
   * @param league               is league.
   * @param best5KTIme           is best 5K time.
   * @param bestHalfMarathonTime is best half marathon time.
   * @param favoriteRunningEvent is favorite running event.
   */
  public Runner(Name athletesName, Double height, Double weight, String league, Double best5KTIme,
      Double bestHalfMarathonTime, String favoriteRunningEvent) {
    super(athletesName, height, weight, league);

    this.best5KTIme = best5KTIme;
    this.bestHalfMarathonTime = bestHalfMarathonTime;
    this.favoriteRunningEvent = favoriteRunningEvent;
  }
}
