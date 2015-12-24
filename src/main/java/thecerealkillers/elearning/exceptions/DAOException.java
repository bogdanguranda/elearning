package thecerealkillers.elearning.exceptions;

/**
 * Created by cuvidk on 11/20/2015.
 */
public class DAOException extends Exception {
    public static final String NO_SUBSCRIBED_GROUPS = "There is no subscribed groups for this course.\n";

    public DAOException(String message) {
        super(message);
    }
}
