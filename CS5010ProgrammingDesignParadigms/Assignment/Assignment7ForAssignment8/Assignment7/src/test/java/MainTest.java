import static org.junit.Assert.assertNull;

import org.junit.Test;
import randomSentenceGenerator.Main;

public class MainTest {

  @Test
  public void main() {
    try {
      Main.main(new String[]{"src/grammars"});
    } catch (Exception e) {
      assertNull(e);
    }
  }
}