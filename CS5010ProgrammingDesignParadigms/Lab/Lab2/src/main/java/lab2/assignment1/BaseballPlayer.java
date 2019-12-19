package lab2.assignment1;

/**
 * Represents a BaseballPlayer.
 */
public class BaseballPlayer extends Athlete {

  private String team;
  private Double averageBatting;
  private Integer seasonHomeRuns;

  /**
   * gets team.
   *
   * @return team.
   */
  public String getTeam() {
    return team;
  }

  /**
   * Sets team.
   *
   * @param team is given team.
   */
  public void setTeam(String team) {
    this.team = team;
  }

  /**
   * Gets average batting.
   *
   * @return average batting.
   */
  public Double getAverageBatting() {
    return averageBatting;
  }

  /**
   * Sets averageBatting.
   *
   * @param averageBatting is given averageBatting.
   */
  public void setAverageBatting(Double averageBatting) {
    this.averageBatting = averageBatting;
  }

  /**
   * Gets seasonHomeRuns.
   *
   * @return seasonHomeRuns.
   */
  public Integer getSeasonHomeRuns() {
    return seasonHomeRuns;
  }

  /**
   * Sets seasonHomeRuns.
   *
   * @param seasonHomeRuns is given seasonHomeRuns.
   */
  public void setSeasonHomeRuns(Integer seasonHomeRuns) {
    this.seasonHomeRuns = seasonHomeRuns;
  }

  /**
   * Init a BaseballPlayer.
   *
   * @param athletesName   is name.
   * @param height         is height.
   * @param weight         is weight.
   * @param league         is league.
   * @param team           is team.
   * @param averageBatting is average btting.
   * @param seasonHomeRuns is season home runs.
   */
  public BaseballPlayer(Name athletesName, Double height, Double weight, String league, String team,
      Double averageBatting, Integer seasonHomeRuns) {
    super(athletesName, height, weight, league);

    this.team = team;
    this.averageBatting = averageBatting;
    this.seasonHomeRuns = seasonHomeRuns;
  }
}
