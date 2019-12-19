package randomSentenceGenerator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    // TODO: change for A8 jar
    fileNameList.add("insult_grammar.json");
//    File file = new File(
//        getClass().getClassLoader().getResource("grammars").getFile()
//    );
//    getFileNameListInFolder(file);
    //    getFileNameListInFolder(new File(this.directoryPath));

  }


  /**
   * Parse JSON file to randomSentenceGenerator.Grammar.
   *
   * @param fileName is given file name.
   * @param grammar  is given grammar.
   * @throws IOException from file.
   */
  public void parseJSONFileToGrammar(String fileName, Grammar grammar) throws IOException {
    // TODO: change for A8 jar
//    String path = Paths.get(this.directoryPath, fileName).toString();
//    Map<String, Object> jsonMap = readJSONFile(path);
    Map<String, Object> jsonMap = readJSONFile("");

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
    // TODO: change for A8 jar
//    File jsonFile = new File(path);
//    InputStream is = this.getClass().getResourceAsStream("resources/grammars/insult_grammar.json");
    InputStream is =getClass().getClassLoader().getResourceAsStream("insult_grammar.json");
    System.out.println("is: "+is);
//    File jsonFile = new File(
//        getClass().getClassLoader().getResource("grammars/insult_grammar.json").getFile()
//    );
//    System.out.println(getClass().getClassLoader().getResource("grammars/insult_grammar.json").getPath());
//    if(jsonFile == null){
//      throw new IOException("resource path: "+ getClass().getClassLoader().getResource("main/grammars/insult_grammar.json").getPath());
//    }


    Type type = new TypeToken<Map<String, Object>>() {
    }.getType();
    return new Gson().fromJson(new InputStreamReader(is), type);
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
