package thecerealkillers.elearning.exceptions;

/**
 * Created by Lucian on 17.11.2015.
 */
public class InvalidSignUpInfoException extends Throwable {
    public InvalidSignUpInfoException(String feedback) {
        super(feedback);
    }
}
