package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a arg parser.
 */
public class ArgParser {

  /**
   * Parses given args.
   *
   * @param args is given args.
   * @return Map.
   */
  public Map<String, String> parse(String[] args) {
    Map<String, String> argMap = new HashMap<>();
    int len = args.length;
    for (int i = 0; i < len; i++) {
      String arg = args[i];
      switch (arg) {
        case "--email":
          if (argMap.containsKey("action")) {
            System.out.println("Only one action \"--email\" or \"--letter\" allowed.");
            return null;
          }
          argMap.put("action", "--email");
          break;
        case "--letter":
          if (argMap.containsKey("action")) {
            System.out.println("Only one action \"--email\" or \"--letter\" allowed.");
            return null;
          }
          argMap.put("action", "--letter");
          break;
        case "--email-template":
          if (argMap.containsKey("--email-template")) {
            System.out.println("Only one template allowed.");
            return null;
          }
          if (i == len - 1 || !args[i + 1].contains(".")) {
            System.out.println("Must given template directory after --email-template.");
            return null;
          }
          argMap.put("--email-template", args[i + 1]);
          i++;
          break;
        case "--letter-template":
          if (argMap.containsKey("--letter-template")) {
            System.out.println("Only one template allowed.");
            return null;
          }
          if (i == len - 1 || !args[i + 1].contains(".")) {
            System.out.println("Must given template directory after --letter-template.");
            return null;
          }
          argMap.put("--letter-template", args[i + 1]);
          i++;
          break;
        case "--output-dir":
          if (argMap.containsKey("--output-dir")) {
            System.out.println("Only one output directory allowed.");
            return null;
          }
          if (i == len - 1) {
            System.out.println("Must given output directory after --output-dir.");
            return null;
          }
          argMap.put("--output-dir", args[i + 1]);
          i++;
          break;
        case "--csv-file":
          if (argMap.containsKey("--csv-file")) {
            System.out.println("Only one csv file allowed.");
            return null;
          }
          if (i == len - 1 || !args[i + 1].contains(".csv")) {
            System.out.println("Must given csv file after --csv-file.");
            return null;
          }
          argMap.put("--csv-file", args[i + 1]);
          i++;
          break;
        case "--send_email":
          argMap.put("send_email", "");
          break;
        case "--email_recepient":
          argMap.put("send_email", args[i + 1]);
          i++;
          break;
        default:
          System.out.println("Must given args as requirements");
      }
    }

    if (!argMap.containsKey("--output-dir") || !argMap.containsKey("--csv-file")) {
      System.out.println("--output-dir and --csv-file must be given.");
      return null;
    }

    if (!argMap.containsKey("action")) {
      System.out.println("--email or --letter action must be given");
      return null;
    }

    if (argMap.get("action").equals("--letter") && !argMap.containsKey("--letter-template")) {
      System.out.println("--letter action needs --letter-template given.");
      return null;
    }

    if (argMap.get("action").equals("--email") && !argMap.containsKey("--email-template")) {
      System.out.println("--email action needs --email-template given.");
      return null;
    }

    return argMap;

  }

  /**
   * Hash code.
   *
   * @return hashcode.
   */
  public int hashcode() {
    return 17;
  }

  /**
   * Compares.
   *
   * @param argParser is given argParser.
   * @return true if equals.
   */
  public boolean equals(ArgParser argParser) {
    return this.hashcode() == argParser.hashcode();
  }

  /**
   * To string.
   *
   * @return String.
   */
  public String toString() {
    return "ArgParser";
  }

}
