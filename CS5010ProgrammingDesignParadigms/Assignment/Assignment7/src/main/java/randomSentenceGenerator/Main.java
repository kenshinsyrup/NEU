package randomSentenceGenerator;

public class Main {

  public static void main(String[] args) {
    if (args.length != 1) {
      return;
    }

    Generator generator = new Generator(args[0]);
    try {
      generator.generate();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
