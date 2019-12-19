package lab2.assignment3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PosnTest {

  private Posn posn1;
  private Posn posn2;
  private Posn posn3;

  @Before
  public void setUp() throws Exception {
    this.posn1 = new Posn(0, 1);
    this.posn2 = new Posn(0, 1);
    this.posn3 = new Posn(0, 1);
  }

  @Test
  public void testEquals() {
    // 1
    assertTrue(this.posn1.equals(this.posn1));

    // 2
    assertTrue(this.posn1.equals(this.posn2));
    assertTrue(this.posn2.equals(this.posn1));

    // 3
    if (this.posn1.equals(this.posn2) && this.posn2.equals(this.posn3)) {
      assertTrue(this.posn1.equals(this.posn3));
    }

    // 4
    for (int i = 0; i < 10; i++) {
      assertTrue(this.posn1.equals(this.posn2));
    }

    // 5
    assertFalse(this.posn1.equals(null));

  }

  @Test
  public void testHashCode() {
    assertTrue(this.posn1.hashCode() == 1);
    assertTrue(this.posn2.hashCode() == 1);
    assertTrue(this.posn3.hashCode() == 1);
  }
}