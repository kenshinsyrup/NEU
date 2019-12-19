package problem2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests artist.
 */
public class ArtistTest {

  private Poet poet;
  private Actor actor;

  @Before
  public void setUp() throws Exception {
    List<String> awards = new ArrayList<>();
    awards.add("award");

    String genre = "popular";

    List<String> movies = new ArrayList<>();
    movies.add("movie");

    List<String> series = new ArrayList<>();
    series.add("series");

    List<String> otherMultimedia = new ArrayList<>();
    otherMultimedia.add("other");

    this.poet = new Poet("poet", 1, genre, awards);
    this.actor = new Actor("actor", 2, genre, awards, movies, series, otherMultimedia);
  }

  @Test
  public void getName() {
    assertEquals("poet", this.poet.getName());

    assertEquals("actor", this.actor.getName());
  }

  @Test
  public void setName() {
    this.poet.setName("poet2");
    assertEquals("poet2", this.poet.getName());

    this.actor.setName("actor2");
    assertEquals("actor2", this.actor.getName());
  }

  @Test
  public void getAge() {
    assertEquals(1, this.poet.getAge());

    assertEquals(2, this.actor.getAge());
  }

  @Test
  public void setAge() {
    this.poet.setAge(3);
    assertEquals(3, this.poet.getAge());

    this.actor.setAge(4);
    assertEquals(4, this.actor.getAge());
  }

  @Test
  public void getGenre() {
    assertTrue(this.poet.getGenre().equals("popular"));

    assertTrue(this.actor.getGenre().equals("popular"));
  }

  @Test
  public void setGenre() {
    this.poet.setGenre("classic");
    assertEquals("classic", this.poet.getGenre());

    this.actor.setGenre("classic");
    assertEquals("classic", this.actor.getGenre());
  }

  @Test
  public void getAwards() {
    List<String> awards = new ArrayList<>();
    awards.add("award");

    assertTrue(this.poet.getAwards().equals(awards));

    assertTrue(this.actor.getAwards().equals(awards));
  }

  @Test
  public void setAwards() {
    List<String> awards = new ArrayList<>();
    awards.add("award2");

    this.poet.setAwards(awards);
    assertEquals(awards, this.poet.getAwards());

    this.actor.setAwards(awards);
    assertEquals(awards, this.actor.getAwards());
  }
}