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


    //----------------------------------CommentController-----------------------------------------------------------------------------------

    public static final String COMMENT_ADD = "Comment added in database.";
    public static final String COMMENT_GET = "Comment with the specified id retrieved.";
    public static final String COMMENT_GET_IN_THREAD = "All comments from the specified thread retrieved.";
    public static final String COMMENT_UPDATE = "Comment updated.";
    public static final String COMMENT_DELETE = "Comment deleted from database.";


    //----------------------------------BreachAttempt---------------------------------------------------------------------------------------

    public static final String TOKEN_DIFFERENT_USERNAME = "Nice try :P.";


    //----------------------------------CommentController-----------------------------------------------------------------------------------

    public static final String ADMIN_NEW_ACCOUNT = "New user account created.";
    public static final String ADMIN_SUSPEND = "User account suspended.";
    public static final String ADMIN_REACTIVATE = "User account reactivate.";
    public static final String ADMIN_CHANGE_TYPE = "User account's type was changed.";


    //----------------------------------CoursesController-----------------------------------------------------------------------------------

    public static final String COURSES_GET_ALL = "All courses retrieved.";


    //----------------------------------UserController--------------------------------------------------------------------------------------

    public static final String USER_AUTHENTICATE = "User log in success.";
    public static final String USER_GET = "Retrieved user data from the database.";
    public static final String USER_GET_ALL = "Retrieved all users from the database.";
}