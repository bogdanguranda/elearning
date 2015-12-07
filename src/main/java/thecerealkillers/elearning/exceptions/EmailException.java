package thecerealkillers.elearning.exceptions;

/**
 * Created by Dani
 */
public class EmailException extends Exception {

    public static final String EMAIL_EXCEPTION = "An error occurred while sending the mail.\n";

    public EmailException(String message) {
        super(message);
    }
}
