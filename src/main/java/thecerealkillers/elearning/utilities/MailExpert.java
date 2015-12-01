package thecerealkillers.elearning.utilities;


import thecerealkillers.elearning.model.Mail;

import java.util.Properties;

import javax.mail.internet.*;
import javax.mail.*;


/**
 * Created by Dani
 */
public class MailExpert {

    private static String EMAIL_ADDRESS = "thecerealkillers.pc@gmail.com";
    private static String PASSWORD = "gldq dtsl xvtg fnyj";
    private static MailExpert instance = null;

    public MailExpert() {
    }

    public static void sendMail(Mail mail) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL_ADDRESS, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_ADDRESS));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
            message.setSubject(mail.getSubject());
            message.setText(mail.getMessage());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            System.out.println("Username or Password are incorrect ... exiting !");
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
