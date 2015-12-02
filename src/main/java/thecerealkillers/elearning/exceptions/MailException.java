package thecerealkillers.elearning.exceptions;

/**
 * Created by Jack on 02-Dec-15.
 */
public class MailException extends Exception {

    public static final String EMAIL_EXCEPTION = "An error occurred while sending the mail.\n";

    public MailException(String message) {
        super(message);
    }
}
