package thecerealkillers.elearning.exceptions;

/**
 * Created by Dani.
 */
public class NotFoundException extends Exception  {


    public static final String GET_TOPIC = "No topics in the database with this title.\n";
    public static final String GET_TOPICS = "No topics in the database.\n";
    public static final String NO_TOPIC_WITH_TITLE = "No topic in the database with this name.\n";

    public NotFoundException(String message) {
        super(message);
    }
}
