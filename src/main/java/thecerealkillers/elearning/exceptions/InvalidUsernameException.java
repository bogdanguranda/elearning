package thecerealkillers.elearning.exceptions;

/**
 * Created by Lucian on 17.11.2015.
 */

public class InvalidUsernameException extends Exception {

    public static final String USERNAME_TO_SHORT = "Username should be composed from at least 6 characters.\n";
    public static final String USERNAME_FORBIDDEN_CHARACTER = "Username should only contain alphanumeric characters.\n";

    public InvalidUsernameException(String message) {
        super(message);
    }


}
