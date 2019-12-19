import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TemplateTest {

  private Template template;
  private User user;

  @Before
  public void setUp() throws Exception {
    this.template = new Template("src/main/java/static_files/emailtemplate.txt", "email");
    this.user = new User("first_name", "last_name", "company_name",
        "address", "city", "country", "state", "zip", "phone1", "phone2",
        "email", "web");
  }

  @Test
  public void generateContent() {
    try {
      assertNotNull(this.template.generateContent(this.user));
    } catch (Exception e) {
      assertNull(e);
    }
  }

  @Test
  public void getType() {
    assertTrue(this.template.getType().equals("email"));
  }

  @Test
  public void setType() {
    this.template.setType("letter");
    assertTrue(this.template.getType().equals("letter"));
  }

  @Test
  public void getTemplateData() {
    assertNotNull(this.template.getTemplateData());
  }

  @Test
  public void setTemplateData() {
    this.template.setTemplateData("");
    assertTrue(this.template.getTemplateData().equals(""));
  }

  @Test
  public void hashcode() {
    assertTrue(this.template.hashcode() == 529727414);
  }

  @Test
  public void testEquals() {
    try {
      Template template = new Template("src/main/java/static_files/emailtemplate.txt", "email");
      assertTrue(this.template.equals(template));
    } catch (Exception e) {
      assertNull(e);
    }
  }

  @Test
  public void testToString() {
    assertTrue(this.template.toString().length() == 1245);
  }
}