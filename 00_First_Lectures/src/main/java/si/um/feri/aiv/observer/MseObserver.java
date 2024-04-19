package si.um.feri.aiv.observer;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.vao.Skupnost;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import static jakarta.mail.Transport.send;

@ApplicationScoped
public class MseObserver implements Observer{

    private Session mySession;

    public MseObserver() {
        try {
            InitialContext ctx = new InitialContext();
            mySession = (Session) ctx.lookup("java:jboss/mail/MojMail");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error sending email");
        }
    }


    @Override
    public void update(Object o) {
        if (o instanceof Skupnost skupnost) {
            Skupnost skupnost1 = (Skupnost) o;
            for (MSE mse : skupnost1.getMseList()) {
                try {
                    MimeMessage message = new MimeMessage(mySession);
                    String mail = mse.getOseba().getMail();
                    message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(mail));
                    message.setSubject("Nov MSE dodan");
                    message.setText("V skupnost " + skupnost1.getNaziv() + " je bil dodan nov MSE: " + mse.toString());
                    Transport.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Napaka pri pošiljanju maila");
                }
            }
        }
        else if (o instanceof MSE x) {
            MSE mse = (MSE) o;

                try {
                    MimeMessage message = new MimeMessage(mySession);
                    String mail = mse.getOseba().getMail();
                    message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(mail));
                    message.setSubject("Nov MSE dodan");
                    message.setText("Dogodek je blizje od 2 meseca");
                    Transport.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Napaka pri pošiljanju maila");
                }

        }

    }
}
