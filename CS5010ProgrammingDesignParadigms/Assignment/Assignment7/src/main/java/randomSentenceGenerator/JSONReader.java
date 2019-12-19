package randomSentenceGenerator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a randomSentenceGenerator.JSONReader.
 */
public class JSONReader {

  private String directoryPath;

  private final String GRAMMAR_TITLE = "grammarTitle";
  private final String GRAMMAR_DESC = "grammarDesc";
  private final String START = "start";

  private List<String> fileNameList;

  /**
   * Represents a JSON file reader.
   *
   * @param path
   */
  public JSONReader(String path) {
    this.directoryPath = path;
    this.fileNameList = new ArrayList<>();

    getFileNameListInFolder(new File(this.directoryPath));
  }

  /**
   * Parse JSON file to randomSentenceGenerator.Grammar.
   *
   * @param fileName is given file name.
   * @param grammar  is given grammar.
   * @throws IOException from file.
   */
  public void parseJSONFileToGrammar(String fileName, Grammar grammar) throws IOException {
    String path = Paths.get(this.directoryPath, fileName).toString();
    Map<String, Object> jsonMap = readJSONFile(path);
    for (String key : jsonMap.keySet()) {
      switch (key) {
        case GRAMMAR_TITLE: {
          grammar.setGrammarTitle((String) jsonMap.get(key));
          break;
        }
        case GRAMMAR_DESC: {
          grammar.setGrammarDesc((String) jsonMap.get(key));
          break;
        }
        case START: {
          List<String> start = new ArrayList<>();
          if (jsonMap.get(key) instanceof List) {
            for (Object obj : (List) jsonMap.get(key)) {
              if (obj instanceof String) {
                start.add((String) obj);
              }
            }
            grammar.setStart(start);
          }
          break;
        }
        default: {
          String normalizedKey = key.toLowerCase();
          Map<String, List<String>> keyExpansionsMap = grammar.getKeyExpansionsMap();
          List<String> expansionList = keyExpansionsMap
              .getOrDefault(normalizedKey, new ArrayList<>());
          if (jsonMap.get(key) instanceof List) {
            for (Object obj : (List) jsonMap.get(key)) {
              if (obj instanceof String) {
                expansionList.add((String) obj);
              }
            }
          }
          keyExpansionsMap.put(normalizedKey, expansionList);
          grammar.setKeyExpansionsMap(keyExpansionsMap);
        }
      }
    }
  }

  // read a JSON file by given path.
  private Map<String, Object> readJSONFile(String path) throws IOException {
    File jsonFile = new File(path);
    Type type = new TypeToken<Map<String, Object>>() {
    }.getType();
    return new Gson().fromJson(new FileReader(jsonFile), type);
  }

  // get all file name list in the given folder.
  private void getFileNameListInFolder(File folder) {
    for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
      if (fileEntry.isFile()) {
        fileNameList.add(fileEntry.getName());
      }
    }
  }

  public List<String> getFileNameList() {
    return fileNameList;
  }
}
