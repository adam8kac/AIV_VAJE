package si.um.feri.aiv.observer;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.mail.Address;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import si.um.feri.aiv.vao.Oseba;

import javax.naming.InitialContext;
import java.io.IOException;
import java.io.Serializable;


@Named
@SessionScoped
public class Email implements Serializable {

    private static final long serialVersionUID = 1544680932114626710L;

    /**
     * Resource for sending the email. The mail subsystem is defined in either standalone.xml or domain.xml in your respective
     * configuration directory.
     */
//    @Resource(mappedName = "java:jboss/mail/MojMail")

    private Session mySession;

            public Email() {
                try {
                    InitialContext ctx = new InitialContext();
                    mySession = (Session) ctx.lookup("java:jboss/mail/MojMail");

                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error sending email");
                }
            }
    private String to = "adam.kac@student.um.si";

    private String from = "adam.kac@student.um.si";

    private String subject;

    private String body = "This is a test email from the Java EE 7 Tutorial.";

    private String pop3User = "user02@james.local";

    private String pop3Password = "1234";

    private String pop3Emails;

    private String imapEmails;

    /**
     * Method to send the email based upon values entered in the JSF view. Exception should be handled in a production usage but
     * is not handled in this example.
     */
    public void send() {
        try {
            Message message = new MimeMessage(mySession);
            message.setFrom(new InternetAddress(from));
            Address toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setContent(body, "text/plain");
            Transport.send(message);

            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Email sent to " + to);
            context.addMessage(null, facesMessage);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Error sending the Email. " + e.getMessage());
            context.addMessage(null, facesMessage);
        }
    }

    public void resetSmtp() {
        from = "user01@james.local";
        to = "user02@james.local";
        subject = null;
        body = null;
    }

    public void retrievePop3() throws Exception {
        try {
            pop3Emails = retrieveEmails("pop3", pop3User, pop3Password);
            if (pop3Emails == null) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("No message found.");
                context.addMessage(null, facesMessage);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Error retrieving emails using POP3. " + e.getMessage());
            context.addMessage(null, facesMessage);
        }
    }

    public void resetPop3() {
        pop3User = "user02@james.local";
        pop3Password = "1234";
        pop3Emails = null;
    }

    public void retrieveImap() throws Exception {
        try {
            imapEmails = retrieveEmails("imap");
            if (imapEmails == null) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("No message found.");
                context.addMessage(null, facesMessage);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Error retrieving emails using IMAP. " + e.getMessage());
            context.addMessage(null, facesMessage);
        }
    }

    public void resetImap() {
        imapEmails = null;
    }

    private String retrieveEmails(String protocol) throws MessagingException, IOException {
        return retrieveEmails(protocol, null, null);
    }

    private String retrieveEmails(String protocol, String user, String password) throws MessagingException, IOException {
        Store store = mySession.getStore(protocol);
        if (user != null && !user.trim().isEmpty()) {
            store.connect(user, password);
        } else {
            // Users the credentials configured in the Mail Subsystem
            store.connect();
        }

        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_ONLY);

        // get the list of inbox messages
        Message[] messages = inbox.getMessages();

        if (messages.length == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder("Emails retrieved via ").append(protocol).append("\n");
        for (int i = 0; i < messages.length; i++) {
            // stop after listing ten messages
            if (i > 10) {
                inbox.close(true);
                store.close();
            }

            sb.append("Message ").append((i + 1)).append("\n");
            sb.append("From : ").append(messages[i].getFrom()[0]).append("\n");
            sb.append("Subject : ").append(messages[i].getSubject()).append("\n");
            sb.append("Body : ").append(messages[i].getContent().toString()).append("\n");
            sb.append("Sent Date : ").append(messages[i].getSentDate()).append("\n");
            sb.append("----------------------------------").append("\n");
        }

        inbox.close(true);
        store.close();

        return sb.toString();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPop3User() {
        return pop3User;
    }

    public void setPop3User(String pop3User) {
        this.pop3User = pop3User;
    }

    public String getPop3Password() {
        return pop3Password;
    }

    public void setPop3Password(String pop3Password) {
        this.pop3Password = pop3Password;
    }

    public String getPop3Emails() {
        return pop3Emails;
    }

    public void setPop3Emails(String pop3Emails) {
        this.pop3Emails = pop3Emails;
    }

    public String getImapEmails() {
        return imapEmails;
    }

    public void setImapEmails(String imapEmails) {
        this.imapEmails = imapEmails;
    }
}
