package assignment2_refactored.furniture;

import static org.junit.Assert.assertEquals;

import assignment2_refactored.furniture.cabinet.AbstractCabinet;
import assignment2_refactored.furniture.cabinet.line.Yossarian;
import assignment2_refactored.furniture.config.Size;
import assignment2_refactored.utils.ColorName;
import assignment2_refactored.utils.Num;
import assignment2_refactored.utils.SizeType;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/**
 * Represents furniture test.
 */
public class AbstractFurnitureTest {

  AbstractCabinet abstractCabinet;

  @Before
  public void setUp() throws Exception {
    this.abstractCabinet = new Yossarian(SizeType.WARDROBE);
  }

  @Test
  public void getName() {
    assertEquals(SizeType.WARDROBE + " " + "Yossarian Cabinet", this.abstractCabinet.getName());
  }

  @Test
  public void setName() {
    this.abstractCabinet.setName(SizeType.WARDROBE + " " + "Yossarian Cabinet1");
    assertEquals(SizeType.WARDROBE + " " + "Yossarian Cabinet1", this.abstractCabinet.getName());
  }

  @Test
  public void getSize() {
    Size size = new Size(Num.SEVENTY_TWO, Num.THIRTY_SIX, Num.SIXTEEN);
    assertEquals(size.getHeight(), this.abstractCabinet.getSize().getHeight());
  }

  @Test
  public void setSize() {
    Size size = new Size(Num.SEVENTY_TWO, Num.SEVENTY_TWO, Num.SIXTEEN);
    this.abstractCabinet.setSize(size);
    assertEquals(size, this.abstractCabinet.getSize());
  }

  @Test
  public void getColors() {
    assertEquals(4, this.abstractCabinet.getColors().size());
  }

  @Test
  public void setColors() {
    Set<String> colors = new HashSet<>();
    colors.add(ColorName.BLACK);
    this.abstractCabinet.setColors(colors);
    assertEquals(colors, this.abstractCabinet.getColors());
  }

}