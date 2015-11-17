package thecerealkillers.elearning.exceptions;

/**
 * Created by Lucian on 17.11.2015.
 */
public class InvalidPasswordException extends Exception {

    public static final String PASSWORD_NOT_CONTAINS_ALPHABETIC_CHARACTER = "Password should contain alphabetic characters.\n";
    public static final String PASSWORD_TOO_SHORT = "Password should be composed from at least 7 characters.\n";
    public static final String PASSWORD_NOT_CONTAINS_DIGIT_CHARACTER = "Password should contain at least 1 digit.\n";

    public InvalidPasswordException(String message) {
        super(message);
    }
}
