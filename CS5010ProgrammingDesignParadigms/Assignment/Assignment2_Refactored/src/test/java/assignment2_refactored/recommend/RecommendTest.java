package assignment2_refactored.recommend;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RecommendTest {

  Recommend recommend;

  @Before
  public void setUp() throws Exception {
    this.recommend = new Recommend();
  }

  @Test
  public void recommend() {
    Criteria criteria = new Criteria(36, 36, 0, 0, null, false, false, 0);
    assertTrue(this.recommend.recommend(criteria));
  }
}