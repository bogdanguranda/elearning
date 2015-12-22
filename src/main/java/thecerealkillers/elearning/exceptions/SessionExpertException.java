package thecerealkillers.elearning.exceptions;

/**
 * Created by Dani.
 */
public class SessionExpertException extends Exception {

    public static final String FAILED_ADD_SESSION = "An error occurred while saving the session in the database.\n";
    public static final String FAILED_DELETE_SESSION = "An error occurred while deleting the session.\n";
    public static final String FAILED_GET_BY_USE = "A database error occurred while getting the session by user.\n";
    public static final String FAILED_GET_BY_TOKEN = "A database error occurred while getting the session by token.\n";
    public static final String FAILED_GET_ROBE = "A database error occurred while getting the user's role by his session token.\n";


    public SessionExpertException(String message) {
        super(message);
    }
}