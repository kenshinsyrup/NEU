import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Represents template.
 */
public class Template {

  private String templateData;
  private String type;

  /**
   * Inits a template.
   *
   * @param path is given path.
   * @param type is given type.
   * @throws Exception
   */
  public Template(String path, String type) throws Exception {
    this.templateData = new String(Files.readAllBytes(Paths.get(path)));
    this.type = type;
  }

  /**
   * Generate content by template.
   *
   * @param user is given user.
   * @return output file content.
   * @throws Exception
   */
  public String generateContent(User user) throws Exception {
    String content = new String(this.templateData);
    content = content.replaceAll("\\[\\[first_name\\]\\]", user.getFirst_name());
    content = content.replaceAll("\\[\\[last_name\\]\\]", user.getLast_name());
    content = content.replaceAll("\\[\\[company_name\\]\\]", user.getCompany_name());
    content = content.replaceAll("\\[\\[address\\]\\]", user.getAddress());
    content = content.replaceAll("\\[\\[city\\]\\]", user.getCity());
    content = content.replaceAll("\\[\\[country\\]\\]", user.getCountry());
    content = content.replaceAll("\\[\\[state\\]\\]", user.getState());
    content = content.replaceAll("\\[\\[zip\\]\\]", user.getZip());
    content = content.replaceAll("\\[\\[phone1\\]\\]", user.getPhone1());
    content = content.replaceAll("\\[\\[phone2\\]\\]", user.getPhone2());
    content = content.replaceAll("\\[\\[email\\]\\]", user.getEmail());
    content = content.replaceAll("\\[\\[web\\]\\]", user.getWeb());

    return content;
  }

  /**
   * Hash code.
   *
   * @return hashcode.
   */
  public int hashcode() {
    return Objects.hash(this.templateData, this.type);
  }

  /**
   * Compare.
   *
   * @param template is given template.
   * @return true if equal.
   */
  public boolean equals(Template template) {
    return this.hashcode() == template.hashcode();
  }

  /**
   * To string.
   *
   * @return String.
   */
  public String toString() {
    return this.type + " " + this.templateData;
  }

  /**
   * Gets type.
   *
   * @return type.
   */
  public String getType() {
    return type;
  }

  /**
   * Sets type.
   *
   * @param type is given type.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets template data.
   *
   * @return template data.
   */
  public String getTemplateData() {
    return templateData;
  }

  /**
   * Sets template data.
   *
   * @param templateData is given template data.
   */
  public void setTemplateData(String templateData) {
    this.templateData = templateData;
  }
}
