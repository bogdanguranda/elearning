package thecerealkillers.elearning.utilities;


import java.util.ArrayList;

/**
 * Created by Dani.
 */
public class Constants {

    public static final String ADMIN = "administrator";
    public static final String STUDENT = "student";
    public static final String TEACHER = "profesor";

    public static final ArrayList<String> ROLE_LIST = new ArrayList<String>() {{
        add(ADMIN);
        add(STUDENT);
        add(TEACHER);
    }};

    public static final String NO_PERMISSION = "User doesn't have permission for this action!";



    //----------------------------------TopicController-------------------------------------------------------------------------------------

    public static final String TOPIC_CREATE_TOPIC = "Topic added in database.";
    public static final String TOPIC_GET_ALL = "All topics retrieved form database.";
    public static final String TOPIC_GET_BY_TITLE = "Topic with the specified title retrieved.";
    public static final String TOPIC_UPDATE = "Topic updated.";
    public static final String TOPIC_DELETE = "Topic deleted from database.";


    //----------------------------------ThreadController------------------------------------------------------------------------------------

    public static final String THREAD_CREATE_THREAD = "Thread added in database.";
    public static final String THREAD_GET = "Thread retrieved.";
    public static final String THREAD_GET_IN_TOPIC = "All threads in the specified topic retrieved.";
    public static final String THREAD_GET_BY_USER = "All threads owned by the specified user retrieved.";
    public static final String THREAD_GET_ALL = "All threads retrieved.";
    public static final String THREAD_UPDATE = "Thread updated.";
    public static final String THREAD_DELETE = "Thread deleted from database.";
}