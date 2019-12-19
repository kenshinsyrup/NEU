import com.sun.mail.smtp.SMTPTransport;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.CSVProcessor;

/**
 * Represents a Company.
 */
public class Company {

  private Template letterTemplate;
  private Template emailTemplate;
  private List<User> users;
  private CSVProcessor csvProcessor;
  private String outputDir;

  /**
   * Inits a Company.
   *
   * @param csvPath            is given csv path.
   * @param emailTemplatePath  is email template path.
   * @param letterTemplatePath is letter template path.
   * @param outputDir          is output dir.
   * @throws Exception
   */
  public Company(String csvPath, String emailTemplatePath, String letterTemplatePath,
      String outputDir)
      throws Exception {
    this.csvProcessor = new CSVProcessor(csvPath);

    this.users = new ArrayList<>();
    populateUserData();

    if (letterTemplatePath != null && !letterTemplatePath.equals("")) {
      this.letterTemplate = new Template(letterTemplatePath, "letter");
    }
    if (emailTemplatePath != null && !emailTemplatePath.equals("")) {
      this.emailTemplate = new Template(emailTemplatePath, "email");
    }
    this.outputDir = outputDir;
  }

  /**
   * Generate file.
   *
   * @param type is given file type.
   * @throws Exception
   */
  public void generateFile(String type) throws Exception {
    for (int i = 0; i < this.users.size(); i++) {
      String content = "";
      User user = this.users.get(i);
      if (type.equals("letter")) {
        content = this.letterTemplate.generateContent(user);
      } else if (type.equals("email")) {
        content = this.emailTemplate.generateContent(user);
      }

      File directory = new File(this.outputDir);
      // If you require it to make the entire directory path including parents,
      if (!directory.exists()) {
        directory.mkdir();
      }

      File file = new File(this.outputDir + "/" + +user.hashCode() + ".txt");
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(content);
      bw.close();
    }
  }

  /**
   * Sends email to user with given email address
   *
   * @param email is given email
   * @return boolean
   * @throws Exception throw
   */
  public boolean sendEmailToUser(String email) throws Exception {
    for (int i = 0; i < this.users.size(); i++) {
      User user = users.get(i);
      if (user.getEmail().equals(email)) {
        sendEmail(user, this.emailTemplate.generateContent(user));
        return true;
      }
    }

    return false;
  }

  //  send email to user with content
  private boolean sendEmail(User user, String content) {
    // for example, smtp.mailgun.org
    String SMTP_SERVER = "smtp.googlemail.com";
    String USERNAME = "your_email_address@gmail.com";
    String PASSWORD = "password.";

    String EMAIL_FROM = "your_email_address@gmail.com";
    String EMAIL_TO = user.getEmail();
    String EMAIL_TO_CC = "";

    String EMAIL_SUBJECT = "Test Send Email via SMTP";
    String EMAIL_TEXT = "Hello Java Mail \n ABC123";

    Properties prop = System.getProperties();
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
    prop.put("mail.smtp.port", "25"); // default port 25

    Session session = Session.getInstance(prop, null);
    Message msg = new MimeMessage(session);

    try {

      // from
      msg.setFrom(new InternetAddress(EMAIL_FROM));

      // to
      msg.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(EMAIL_TO, false));

      // cc
      msg.setRecipients(Message.RecipientType.CC,
          InternetAddress.parse(EMAIL_TO_CC, false));

      // subject
      msg.setSubject(EMAIL_SUBJECT);

      // content
      msg.setText(content);

      msg.setSentDate(new Date());

      // Get SMTPTransport
      SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

      // connect
      t.connect(SMTP_SERVER, USERNAME, PASSWORD);

      // send
      t.sendMessage(msg, msg.getAllRecipients());

      System.out.println("Response: " + t.getLastServerResponse());

      t.close();

    } catch (MessagingException e) {
      e.printStackTrace();
      return false;
    }

    return true;

  }

  // populate user data from cvs files to User object.
  private void populateUserData() throws Exception {
    if (this.csvProcessor != null) {
      List<String[]> dataList = this.csvProcessor.parseData();
      // index from 1 because 0th is the header of csv
      for (int i = 1; i < dataList.size(); i++) {
        String[] data = dataList.get(i);
        User user = new User(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7],
            data[8], data[9], data[10], data[11]);
        this.users.add(user);
      }
    }
  }

  /**
   * Hash code.
   *
   * @return hashcode.
   */
  public int hashcode() {
    return Objects.hash(this.emailTemplate.toString(), this.letterTemplate.toString(),
        this.outputDir);
  }

  /**
   * Compares.
   *
   * @param company is given company.
   * @return true if equals.
   */
  public boolean equals(Company company) {
    return this.hashcode() == company.hashcode();
  }

  /**
   * To string.
   *
   * @return String.
   */
  public String toString() {
    return this.letterTemplate.toString() + " " + this.emailTemplate.toString() + " " + this.users
        .toString() + " " + this.csvProcessor.toString() + " " + this.outputDir;
  }

  /**
   * Gets letter template.
   *
   * @return letter template.
   */
  public Template getLetterTemplate() {
    return letterTemplate;
  }

  /**
   * Sets letter template.
   *
   * @param letterTemplate is given letter template.
   */
  public void setLetterTemplate(Template letterTemplate) {
    this.letterTemplate = letterTemplate;
  }

  /**
   * Gets email template.
   *
   * @return email template.
   */
  public Template getEmailTemplate() {
    return emailTemplate;
  }

  /**
   * Set email template.
   *
   * @param emailTemplate is given email template.
   */
  public void setEmailTemplate(Template emailTemplate) {
    this.emailTemplate = emailTemplate;
  }

  /**
   * Gets users.
   *
   * @return users.
   */
  public List<User> getUsers() {
    return users;
  }

  /**
   * Sets users.
   *
   * @param users is given users.
   */
  public void setUsers(List<User> users) {
    this.users = users;
  }

  /**
   * Gets csv processor.
   *
   * @return csv processor.
   */
  public CSVProcessor getCsvProcessor() {
    return csvProcessor;
  }

  /**
   * Sets csv processor.
   *
   * @param csvProcessor is given csv provessor.
   */
  public void setCsvProcessor(CSVProcessor csvProcessor) {
    this.csvProcessor = csvProcessor;
  }

  /**
   * Gets output dir.
   *
   * @return output dir.
   */
  public String getOutputDir() {
    return outputDir;
  }

  /**
   * Sets output dir.
   *
   * @param outputDir is given output dir.
   */
  public void setOutputDir(String outputDir) {
    this.outputDir = outputDir;
  }


}
