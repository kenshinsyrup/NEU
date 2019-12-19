package assignment6ConcurrentSolution;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {
  @Test
  public void main() {
    String[] args = {"courses.csv", "studentVle.csv", "assessments.csv", "11000"};

    try {
      Main.main(args);
    }  catch (Exception e) {
      assertNull(e);
    }
  }
}