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
    public static final String GET_BY_TITLE = "Topic with the specified title retrieved.";
    public static final String TOPIC_DELETE = "Topic deleted from database.";
}