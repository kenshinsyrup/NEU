package problem2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests ShowPractitioner.
 */
public class ShowPractitionerTest {

  private Dancer dancer;

  @Before
  public void setUp() throws Exception {
    List<String> awards = new ArrayList<>();
    awards.add("award");

    List<String> movies = new ArrayList<>();
    movies.add("movie");

    List<String> series = new ArrayList<>();
    series.add("series");

    List<String> otherMultimedia = new ArrayList<>();
    otherMultimedia.add("other");

    this.dancer = new Dancer("dancer", 1, "popular", awards, movies, series, otherMultimedia);
  }

  @Test
  public void getMovies() {
    List<String> movies = new ArrayList<>();
    movies.add("movie");

    assertTrue(this.dancer.getMovies().equals(movies));
  }

  @Test
  public void setMovies() {
    List<String> movies = new ArrayList<>();
    movies.add("movie2");

    this.dancer.setMovies(movies);
    assertEquals(movies, this.dancer.getMovies());
  }

  @Test
  public void getSeries() {
    List<String> series = new ArrayList<>();
    series.add("series");

    assertTrue(this.dancer.getSeries().equals(series));
  }

  @Test
  public void setSeries() {
    List<String> series = new ArrayList<>();
    series.add("series2");

    this.dancer.setSeries(series);
    assertEquals(series, this.dancer.getSeries());
  }

  @Test
  public void getOtherMultimedia() {
    List<String> otherMultimedia = new ArrayList<>();
    otherMultimedia.add("other");

    assertTrue(this.dancer.getOtherMultimedia().equals(otherMultimedia));
  }

  @Test
  public void setOtherMultimedia() {
    List<String> others = new ArrayList<>();
    others.add("others2");

    this.dancer.setOtherMultimedia(others);
    assertEquals(others, this.dancer.getOtherMultimedia());
  }
}