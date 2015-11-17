package thecerealkillers.elearning.exceptions;

/**
 * Created by Lucian on 17.11.2015.
 */
public class InvalidNameException extends Exception {
    public static final String NAME_WRONG_FORMAT = "Name should have an uppercase character in front and lowecase characters in next.\n";

    public InvalidNameException(String message) {
        super(message);
    }
}
