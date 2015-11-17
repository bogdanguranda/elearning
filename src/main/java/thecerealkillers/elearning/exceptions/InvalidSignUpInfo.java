package thecerealkillers.elearning.exceptions;

/**
 * Created by Lucian on 17.11.2015.
 */
public class InvalidSignUpInfo extends Throwable {
    public InvalidSignUpInfo(String feedback) {
        super(feedback);
    }
}
