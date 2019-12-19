import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest {

  private Company company;
  private Company company1;
  private Company company2;

  @Before
  public void setUp() throws Exception {
    String csvPath = "src/main/java/static_files/insurancecompanymembers.csv";
    String emailTemplatePath = "src/main/java/static_files/emailtemplate.txt";
    String letterTemplatePath = "src/main/java/static_files/lettertemplate.txt";
    String outputDir = "src/output/";
    this.company = new Company(csvPath, emailTemplatePath, letterTemplatePath, outputDir);
    this.company1 = new Company(csvPath, emailTemplatePath, letterTemplatePath, outputDir);
    this.company2 = new Company(csvPath, emailTemplatePath, letterTemplatePath, outputDir);
  }

  @Test
  public void generateFile() {
    try {
      this.company.generateFile("email");
    } catch (Exception e) {
      assertNull(e);
    }
  }

  @Test
  public void getLetterTemplate() {
    assertNotNull(this.company.getLetterTemplate());
  }

  @Test
  public void setLetterTemplate() {
    this.company.setLetterTemplate(null);
    assertNull(this.company.getLetterTemplate());
  }

  @Test
  public void getEmailTemplate() {
    assertNotNull(this.company.getEmailTemplate());
  }

  @Test
  public void setEmailTemplate() {
    this.company.setEmailTemplate(null);
    assertNull(this.company.getEmailTemplate());
  }

  @Test
  public void getUsers() {
    assertTrue(this.company.getUsers().size() == 8);
  }

  @Test
  public void setUsers() {
    this.company.setUsers(new ArrayList<>());
    assertTrue(this.company.getUsers().size() == 0);
  }

  @Test
  public void getCsvProcessor() {
    assertNotNull(this.company.getCsvProcessor());
  }

  @Test
  public void setCsvProcessor() {
    this.company.setCsvProcessor(null);
    assertNull(this.company.getCsvProcessor());
  }

  @Test
  public void getOutputDir() {
    assertTrue(this.company.getOutputDir().equals("src/output/"));
  }

  @Test
  public void setOutputDir() {
    this.company.setOutputDir("");
    assertTrue(this.company.getOutputDir().equals(""));
  }

  @Test
  public void hashcode() {
    assertTrue(this.company1.hashcode() == 1438151303);
  }

  @Test
  public void testEquals() {
    assertTrue(this.company1.equals(this.company2));
  }

  @Test
  public void testToString() {
    assertTrue(this.company1.toString().length() == 4002);
  }
}