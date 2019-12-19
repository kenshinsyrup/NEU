package assignment4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SectionTest {

  Section section;

  @Before
  public void setUp() throws Exception {
    this.section = new Section("SectionId", 0, 0, 2, 10);
  }

  @Test
  public void isFull() {
    assertFalse(this.section.isFull());
  }

  @Test
  public void getSeatById() {
    assertNotNull(this.section.getSeatById(0, 0));
  }

  @Test
  public void getId() {
    assertTrue(this.section.getId().equals("SectionId"));
  }

  @Test
  public void getRowNum() {
    assertTrue(this.section.getRowNum() == 2);
  }

  @Test
  public void getColNum() {
    assertTrue(this.section.getColNum() == 10);
  }

  @Test
  public void getSeatList() {
    assertTrue(this.section.getSeatList().size() == 20);
  }
}