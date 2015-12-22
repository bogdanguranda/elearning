package thecerealkillers.elearning.utilities;


import thecerealkillers.elearning.exceptions.XMLToEmailException;
import thecerealkillers.elearning.exceptions.EmailException;
import thecerealkillers.elearning.model.Email;

import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.*;
import javax.mail.*;


/**
 * Created by Dani
 */
public class EmailExpert {

    private static String EMAIL_ADDRESS = "thecerealkillers.pc@gmail.com";
    private static String PASSWORD = "gldq dtsl xvtg fnyj";
    private static String XML_VALIDATE_EMAIL_ADDRESS = "accountValidation";
    private static String XML_ACCOUNT_CREATED = "accountCreated";
    private static String XML_RESET_REQ_PASSWD = "resetPasswordRequest";
    private static String XML_CHANGED_PASSWD = "passwordChanged";
    private static String XML_RESET_PASSWD = "passwordReset";
    private static String XML_SET_PASSWD = "passwordSet";


    private EmailExpert() {
    }

    ///=========================================Public methods======================================================

    public static void sendMailValidation(String receiverAddress, String userRealName, String url) throws EmailException {
        sendConfirmationEmail(XML_VALIDATE_EMAIL_ADDRESS, receiverAddress, userRealName, url, new ArrayList<>());
    }

    public static void sendAccountCreated(String receiverAddress, String userRealName) throws EmailException {
        sendInformationEmail(XML_ACCOUNT_CREATED, receiverAddress, userRealName, new ArrayList<>());
    }

    public static void sendPasswordResetRequest(String receiverAddress, String userRealName, String url) throws EmailException {
        sendConfirmationEmail(XML_RESET_REQ_PASSWD, receiverAddress, userRealName, url, new ArrayList<>());
    }

    public static void sendPasswordChanged(String receiverAddress, String userRealName) throws EmailException {
        sendInformationEmail(XML_CHANGED_PASSWD, receiverAddress, userRealName, new ArrayList<>());
    }

    public static void sendPasswordReset(String receiverAddress, String username, String newPassword, String userRealName) throws EmailException {
        ArrayList<String> elementsToInsert = new ArrayList<>();

        elementsToInsert.add(username);
        elementsToInsert.add(newPassword);

        sendInformationEmail(XML_RESET_PASSWD, receiverAddress, userRealName, elementsToInsert);
    }

    public static void sendPasswordSet(String receiverAddress, String username, String newPassword, String userRealName) throws EmailException {
        ArrayList<String> elementsToInsert = new ArrayList<>();

        elementsToInsert.add(username);
        elementsToInsert.add(newPassword);

        sendInformationEmail(XML_SET_PASSWD, receiverAddress, userRealName, elementsToInsert);
    }


    ///========================================Private methods======================================================

    private static void sendConfirmationEmail(String emailScope, String receiverAddress, String username, String url,
                                              List<String> elementsToInsert) throws EmailException {
        try {
            Email email = XMLToEmail.getMailForSubject(emailScope, username, url, elementsToInsert);
            email.setTo(receiverAddress);

            EmailExpert.sendMail(email);
        } catch (XMLToEmailException ex) {
            throw new EmailException(EmailException.EMAIL_EXCEPTION + ex.getMessage());
        }
    }

    private static void sendInformationEmail(String emailScope, String receiverAddress, String username, List<String> elementsToInsert) throws EmailException {
        try {
            Email email = XMLToEmail.getMailForSubject(emailScope, username, "", elementsToInsert);
            email.setTo(receiverAddress);

            EmailExpert.sendMail(email);
        } catch (XMLToEmailException ex) {
            throw new EmailException(EmailException.EMAIL_EXCEPTION + ex.getMessage());
        }
    }

    private static void sendMail(Email email) throws EmailException {
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            message.setSubject(email.getSubject());
            message.setContent(email.getMessage(), "text/html");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new EmailException(EmailException.EMAIL_EXCEPTION + e.getMessage());
        }
    }
}
