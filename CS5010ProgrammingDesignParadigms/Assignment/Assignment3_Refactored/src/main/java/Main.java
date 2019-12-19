import java.util.Map;
import utils.ArgParser;

/**
 * Main.
 */
public class Main {

  /**
   * Main.main.
   *
   * @param args is given args.
   */
  public static void main(String[] args) {

    ArgParser argParser = new ArgParser();
    Map<String, String> argMap = argParser.parse(args);

    try {
      Company company = new Company(argMap.get("--csv-file"),
          argMap.getOrDefault("--email-template", ""),
          argMap.getOrDefault("--letter-template", ""), argMap.get("--output-dir"));

      company.generateFile(argMap.get("action"));

      company.sendEmailToUser(argMap.get("send_email"));
    } catch (Exception e) {
      System.out.println("Generate file error: " + e);
    }

  }

}
