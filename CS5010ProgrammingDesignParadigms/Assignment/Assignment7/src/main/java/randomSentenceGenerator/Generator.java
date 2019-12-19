package randomSentenceGenerator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Represents a randomSentenceGenerator.Generator.
 */
public class Generator {

  private final String QUIT = "q";
  private final String YES = "y";
  private final String NO = "n";
  private final String Q_OR_NUM = "q or number";
  private final String Y_OR_N = "y/n";

  private final String LOADING = "Loading grammars...";
  private final String LIST_FILES = "The following grammars are available:";
  private final String CHOOSE_FILE = "Which would you like to use? (q to quit)";
  private final String ANOTHER_SENTENCE = "Would you like another? (y/n)";
  private final String WRONG_FILE = "Must chosen one file number listed above!";
  private final String INVALID_FILE = " is not a valid grammar file! Never choose it again!";

  private JSONReader jsonReader;
  private List<String> fileNameList;

  /**
   * Inits a randomSentenceGenerator.Generator.
   *
   * @param directoryPath is given directory path.
   */
  public Generator(String directoryPath) {
    System.out.println(LOADING);
    this.jsonReader = new JSONReader(directoryPath);
    this.fileNameList = jsonReader.getFileNameList();
  }
  /**
   * Generates sentence.
   *
   * @throws Exception up.
   */
  public void generate() throws Exception {
    System.out.println(LIST_FILES);
    outputFileList();

    System.out.println(CHOOSE_FILE);

    BufferedReader stdIn =
        new BufferedReader(new InputStreamReader(System.in));
    Grammar grammar = new Grammar();
    String line = "";
    String waitFor = Q_OR_NUM;
    while ((line = stdIn.readLine()) != null) {
      if (line.equals(QUIT)) {
        if (waitFor.equals(Q_OR_NUM)) {
          break;
        } else {
          System.out.println(warning(waitFor));
        }

      } else if (isPositiveInteger(line)) {
        if (!waitFor.equals(Q_OR_NUM)) {
          System.out.println(warning(waitFor));
          continue;
        }

        int idx = Integer.parseInt(line) - 1;
        if (idx >= this.fileNameList.size() || idx < 0) {
          System.out.println(WRONG_FILE);
          continue;
        }

        this.jsonReader.parseJSONFileToGrammar(this.fileNameList.get(idx), grammar);
        if (!grammar.isValid()) {
          System.out.println(
              this.fileNameList.get(idx) + INVALID_FILE);
          System.out.println(LIST_FILES);

          outputFileList();
          System.out.println(CHOOSE_FILE);
          continue;
        }

        String sentence = grammar.buildSentence();
        System.out.println(sentence);
        System.out.println(ANOTHER_SENTENCE);
        waitFor = Y_OR_N;

      } else if (line.equals(YES)) {
        if (!waitFor.equals(Y_OR_N)) {
          System.out.println(warning(waitFor));
          continue;
        }

        String sentence = grammar.buildSentence();
        System.out.println(sentence);
        System.out.println(ANOTHER_SENTENCE);
        waitFor = Y_OR_N;

      } else if (line.equals(NO)) {
        if (!waitFor.equals(Y_OR_N)) {
          System.out.println(warning(waitFor));
          continue;
        }

        System.out.println(LIST_FILES);

        outputFileList();
        System.out.println(CHOOSE_FILE);
        waitFor = Q_OR_NUM;
      }
    }

  }

  // Formats the output file name list
  private void outputFileList() {
    for (int i = 0; i < this.fileNameList.size(); i++) {
      String fileName = this.fileNameList.get(i).split("\\.")[0];
      String outputName = "";
      for (String subName : fileName.split("_")) {
        if (fileName.length() == 1) {
          outputName += subName.toUpperCase();
        } else {
          outputName += (subName.substring(0, 1).toUpperCase() + subName
              .substring(1, subName.length()));
        }
        outputName += " ";
      }
      System.out.println((i + 1) + ". " + outputName);
    }
  }

  // warning with given waitFor string.
  private String warning(String waitFor) {
    return "Should input: " + waitFor + " as prompt!";
  }

  // checks if the given str could be parsed to positive integer or not.
  private boolean isPositiveInteger(String str) {
    if (str == null) {
      return false;
    }

    int length = str.length();
    if (length == 0) {
      return false;
    }

    for (int i = 0; i < length; i++) {
      char ch = str.charAt(i);
      if (ch < '0' || ch > '9') {
        return false;
      }
    }

    return true;
  }
}
