package thecerealkillers.elearning.utilities;


import thecerealkillers.elearning.exceptions.MailException;
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

    private MailExpert() {
    }

    public static void sendMailValidation(String to, String token) throws MailException {
        Mail mail = new Mail();

        mail.setTo(to);
        mail.setSubject(Constants.SUBJECT_VALIDATE_MAIL_ADDRESS);
        mail.setMessage("Hello! <br><br>" +
                "<a href=" + token + ">Validate your email address</a> <br><br>" +
                "NOTE :  This address is available  for " + Constants.HOURS_TO_VALIDATE +
                " hours, after that you will have to redo the sing up process." + Constants.FOOTER);

        MailExpert.sendMail(mail);
    }

    public static void sendAccountCreated(String to) throws MailException {
        Mail mail = new Mail();

        mail.setTo(to);
        mail.setSubject(Constants.SUBJECT_ACCOUNT_CREATED);
        mail.setMessage("Hello! <br><br>" +
                "Congratulations your account was successfully created. <br><br>" +
                "We are glad to see you, good luck." + Constants.FOOTER);

        MailExpert.sendMail(mail);
    }

    private static void sendMail(Mail mail) throws MailException {
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
            message.setContent(mail.getMessage(), "text/html");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new MailException(MailException.EMAIL_EXCEPTION+ e.getMessage());
        }
    }
}
