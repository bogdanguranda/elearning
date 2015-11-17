package thecerealkillers.elearning.exceptions;

/**
 * Created by Lucian on 17.11.2015.
 */
public class InvalidEmailException extends Exception {

    public static final String EMAIL_INVALID = "Email is is wrong format.\n";

    public InvalidEmailException(String message) {
        super(message);
    }
}
