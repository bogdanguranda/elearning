package thecerealkillers.elearning.exceptions;

/**
 * Created by Lucian on 17.11.2015.
 */
public class InvalidNameException extends Exception {
    public static final String FIRST_NAME_WRONG_FORMAT = "First Name should have an uppercase character in front and lowercase characters in next.\n";
    public static final String LAST_NAME_WRONG_FORMAT = "Last Name should have an uppercase character in front and lowercase characters in next.\n";

    public InvalidNameException(String message) {
        super(message);
    }
}
