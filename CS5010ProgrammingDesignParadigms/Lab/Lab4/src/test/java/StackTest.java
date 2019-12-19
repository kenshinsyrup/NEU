import static junit.framework.TestCase.assertTrue;

import org.junit.Test;

public class StackTest {

  Stack stack;

  @Test
  public void testPush() {
    //  Stack push(E element); // Adds an element to the Stack
    if (this.stack == null) {
      return;
    }

    this.stack = this.stack.push(1);
    this.stack = this.stack.push(2);
    this.stack = this.stack.pop();
    assertTrue(this.stack.top().equals(1));
  }

  @Test
  public void testPop() {
    //  Stack pop(); // Removes the most recently added element
    this.stack = this.stack.push(1);
    this.stack = this.stack.push(2);
    this.stack = this.stack.pop();
    assertTrue(this.stack.pop().equals(1));
  }

  @Testg
  public void testTop() {
    //  E top(); // Returns but does not remove the most recent element.
    this.stack = this.stack.push(1);
    assertTrue(this.stack.top().equals(1));
  }
}
