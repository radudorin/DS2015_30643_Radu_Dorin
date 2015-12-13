package com.springapp.hardware_store.utils;

import com.springapp.hardware_store.constants.Constants;
import com.springapp.hardware_store.model.Member;

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
     * @param to
     * @param member
     * @return true, if e-mail is sent.
     */
    public static boolean send(String to, Member member)
    {
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

        String mailText = "Dear " + member.getFullName() + ", <br>" +
                "\tThank you for registering. You can see below the information you entered.<br>" +
                "Username  : " +member.getUsername() + "<br>" +
                "Password  : " +member.getPassword() + "<br>" +
                "Phone  : " +member.getPhone() + "<br>" +
                "Address  : " +member.getAddress() + "<br>" +
                "Birth date  : " +member.getBirthDate() + "<br>" +
                "\t If you encounter any errors, please contact us at example@hardwareStore.com" + "<br><br>" +
                "Best regards," + "<br>" +
                "Hardware store administration.";

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // from e-mail address
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Thank you for registering"); // sets subject of e-mail
            message.setContent(mailText , "text/html; charset=utf-8"); // sets the content body of email

            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        }
        catch(MessagingException e)
        {
            throw new RuntimeException(e); // if the e-mail address is bad or doesn't exist
        }
    }

}
