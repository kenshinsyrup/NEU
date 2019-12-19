package randomSentenceGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents a randomSentenceGenerator.Grammar.
 */
public class Grammar {

  private String grammarTitle;
  private String grammarDesc;
  private List<String> start;

  private Map<String, List<String>> keyExpansionsMap;
  private Random random;

  /**
   * Represents a randomSentenceGenerator.Grammar.
   */
  public Grammar() {
    this.start = new ArrayList<>();
    this.keyExpansionsMap = new HashMap<>();

    this.random = new Random();
  }

  /**
   * Builds a sentence.
   *
   * @return a sentence.
   */
  public String buildSentence() {
    StringBuilder sb = new StringBuilder();
    int idx = this.random.nextInt(this.start.size());
    expand(sb, this.start.get(idx).split(" "));
    return sb.toString().trim();
  }

  /**
   * Checks the grammar is valid or not.
   *
   * @return true if grammar is valid.
   */
  public boolean isValid() {
    for (String word : start) {
      if (isNonTerminalWord(word) && !this.keyExpansionsMap.containsKey(normalizeWord(word))) {
        return false;
      }
    }
    return true;
  }

  // expands sentence with given words recursively.
  private void expand(StringBuilder sb, String[] words) {
    if (words == null || words.length == 0) {
      return;
    }

    for (String word : words) {
      if (isNonTerminalWord(word)) {
        List<String> nextWords = this.keyExpansionsMap.get(normalizeWord(word));
        int idx = this.random.nextInt(nextWords.size());
        expand(sb, nextWords.get(idx).split(" "));
      } else {
        String str = word.trim();
        // after trim, if length is 1 and not alphabetic not digit, then must be a punctuation, so we remove the space before it in sb.
        if (str.length() == 1 && !Character.isAlphabetic(str.charAt(0)) && !Character
            .isDigit(str.charAt(0))) {
          if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
          }
        }
        sb.append(str).append(" ");
      }
    }
  }

  // given word is non-terminal word or not
  private boolean isNonTerminalWord(String word) {
    return word != null && word.charAt(0) == '<' && word.charAt(word.length() - 1) == '>';
  }

  // normalize given word, trim surrounding spaces, if non-terminal word also remove <>
  private String normalizeWord(String word) {
    if (word == null) {
      return "";
    }

    String normalizedWord = word.trim().toLowerCase();
    if (isNonTerminalWord(word)) {
      normalizedWord = word.substring(1, word.length() - 1).trim();
    }

    return normalizedWord;
  }

  public void setStart(List<String> start) {
    this.start = start;
  }

  public void setGrammarTitle(String grammarTitle) {
    this.grammarTitle = grammarTitle;
  }

  public void setGrammarDesc(String grammarDesc) {
    this.grammarDesc = grammarDesc;
  }

  public Map<String, List<String>> getKeyExpansionsMap() {
    return keyExpansionsMap;
  }

  public void setKeyExpansionsMap(
      Map<String, List<String>> keyExpansionsMap) {
    this.keyExpansionsMap = keyExpansionsMap;
  }
}
