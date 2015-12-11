package thecerealkillers.elearning.exceptions;


/**
 * Created by Dani
 */
public class PasswordExpertException extends Exception {

    public static final String FAILED_SALT= "Failed to generate salt.\n";
    public static final String FAILED_HASH = "Failed to create hash.\n";

    public PasswordExpertException(String message) {
        super(message);
    }
}
