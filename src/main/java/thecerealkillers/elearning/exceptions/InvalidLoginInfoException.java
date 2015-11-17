package thecerealkillers.elearning.exceptions;

/**
 * Created by Lucian on 17.11.2015.
 */
public class InvalidLoginInfoException extends Throwable {
    public InvalidLoginInfoException(String feedback) {
        super(feedback);
    }
}
