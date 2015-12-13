package utils;

import constants.Constants;
import model.DVD;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by radud on 11/12/2015.
 */
public class MailUtils {

    /**
     * Method used to send an e-mail with given text at given address.
     *
     * @param to
     * @param dvd
     * @return true, if e-mail is sent.
     */
    public static void send(String to, DVD dvd) {
        final String username = Constants.username;
        final String password = Constants.password;
        final String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, null);

        String mailText = dvd.toString();

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // from e-mail address
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("New dvd added."); // sets subject of e-mail
            message.setContent(mailText, "text/html; charset=utf-8"); // sets the content body of email

            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
//            throw new RuntimeException(e); // if the e-mail address is bad or doesn't exist
            System.out.println("Error sending email");
        }
    }

}
