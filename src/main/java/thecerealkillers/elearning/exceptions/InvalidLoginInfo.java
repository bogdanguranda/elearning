package thecerealkillers.elearning.exceptions;

/**
 * Created by Lucian on 17.11.2015.
 */
public class InvalidLoginInfo extends Throwable {
    public InvalidLoginInfo(String feedback) {
        super(feedback);
    }
}
