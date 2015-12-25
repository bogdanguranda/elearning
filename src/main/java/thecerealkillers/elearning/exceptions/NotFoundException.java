package thecerealkillers.elearning.exceptions;

/**
 * Created by Dani.
 */
public class NotFoundException extends Exception  {


    //----------------------------TopicDAO---------------------------------------------------------------------

    public static final String GET_TOPIC = "No topics in the database with this title.\n";
    public static final String GET_TOPICS = "No topics in the database.\n";
    public static final String NO_TOPIC_WITH_TITLE = "No topic in the database with this name.\n";


    //----------------------------ThreadDAO--------------------------------------------------------------------

    public static final String GET_THREADS_BY_OWNER = "No threads owned by this user in the database.\n";
    public static final String GET_THREADS = "No threads in the database.\n";
    public static final String GET_THREAD = "No thread with this title in the database.\n";
    public static final String GET_THREADS_FOR_TOPIC = "No threads in this topic.\n";
    public static final String NO_THREAD_WHIT_TITLE = "No topic in the database with this title.\n";


    public NotFoundException(String message) {
        super(message);
    }
}
