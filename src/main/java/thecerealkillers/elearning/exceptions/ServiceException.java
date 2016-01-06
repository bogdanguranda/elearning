package thecerealkillers.elearning.exceptions;


/**
 * Created by cuvidk on 11/20/2015.
 * Modified by Dani
 * * Added error messages for userServiceImpl and UserRoleServiceImpl
 */
@SuppressWarnings("unused")
public class ServiceException extends Exception {


    //--------------------------------------------UserServiceImpl------------------------------------------------------------------------------

    public static final String FAILED_ACCOUNT_CREATION = "Failed to create user account.\n";

    public static final String FAILED_GET_ROLE = "Failed to get role from DB\n";
    public static final String FAILED_EMAIL_SIGN_UP = "Failed to send email with the validation url.\n";
    public static final String FAILED_DAO_DELETE_ACCOUNT = "A database error occurred while deleting account.\n";
    public static final String FAILED_PASSWORD_SIGN_UP = "Failed while creating salt or generating salt.\n";
    public static final String FAILED_DAO_SIGN_UP = "A database error occurred while saving sign up data.\n";

    public static final String FAILED_DAO_EMAIL_VALIDATION = "A database error occurred while validating user's email address.\n";
    public static final String FAILED_EMAIL_EMAIL_VALIDATION = "Failed to send email to inform the user that his account is activated.\n";

    public static final String FAILED_LOG_IN = "Wrong username or password.\n";
    public static final String FAILED_PASSWORD_LOG_IN = "Failed while processing user's password.\n";
    public static final String FAILED_DAO_LOG_IN = "A database error occurred while reading login up data.\n";

    public static final String FAILED_AUTHENTICATE_PASSWORD_CHANGE = "Failed to authenticate with the username and the password provided.\n";
    public static final String FAILED_DAO_PASSWORD_CHANGE = "A database error occurred while changing password.\n";
    public static final String FAILED_EMAIL_PASSWORD_CHANGE = "Failed to send email after changing password.\n";

    public static final String FAILED_DAO_PASSWORD_RESET = "A database error occurred while resetting password.\n";
    public static final String FAILED_EMAIL_PASSWORD_RESET = "Failed to send email with the new password.\n";
    public static final String FAILED_GENERATE_PASSWORD = "Failed to generate new password.\n";

    public static final String CAN_NOT_RESET_PASSWORD = "Account does not exist or email address is not validated yet.\n";

    public static final String FAILED_DAO_RESET_REQUEST = "A database error occurred while processing password reset request.\n";
    public static final String FAILED_EMAIL_RESET_REQUEST = "Failed to send email with password reset url.\n";


    //------------------------------------------UserRoleServiceImpl----------------------------------------------------------------------------

    public static final String INVALID_USER_ROLE = "An user can not have this role.\n";
    public static final String FAILED_DAO_ADD_ROLE = "A database error occurred while setting the user's role.\n";


    //-------------------------------------------AdminServiceImpl------------------------------------------------------------------------------

    public static final String FAILED_DAO_ROLE_CHG = "A database error occurred while changing user's role.\n";
    public static final String FAILED_EMAIL_PASSWORD_SET = "Failed to send email with data about the account created.\n";
    public static final String FAILED_SUSPEND_ACCOUNT = "Failed to suspend the account.\n";
    public static final String FAILED_ACTIVATE_ACCOUNT = "Failed to activate the account.\n";
    public static final String FAILED_CHANGE_ACCOUNT_TYPE = "Failed to change the account role.\n";


    //------------------------------------------SessionServiceImpl-----------------------------------------------------------------------------

    public static final String FAILED_ADD_SESSION = "An error occurred while saving the session in the database.\n";
    public static final String FAILED_GET_ADD_SESSION = "Failed to initiate a new session or to get the current one from DB.\n";
    public static final String FAILED_DELETE_SESSION = "An error occurred while deleting the session.\n";
    public static final String FAILED_GET_BY_USE = "A database error occurred while getting the session by user.\n";
    public static final String FAILED_GET_BY_TOKEN = "A database error occurred while getting the session by token.\n";
    public static final String FAILED_GET_ROBE = "A database error occurred while getting the user's role by his session token.\n";


    //------------------------------------------PermissionServiceImpl-----------------------------------------------------------------------------

    public static final String FAILED_GET_PERMISSION = "Failed to get permission for database.\n";
    public static final String FAILED_DET_PERMISSION = "Failed to determine if user has permission to execute this operation.\n";


    //------------------------------------------ModuleServiceImpl---------------------------------------------------------------------------------

    public static final String FAILED_COURSE_INNEXISTENT = "The course you are trying to use is innexistent.\n";
    public static final String FAILED_MODULE_ALREADY_EXISTS = "The module you are trying to create already exists.\n";
    public static final String FAILED_MODULE_INNEXISTENT = "The module you are trying to manipulate is innexistent.\n";


    //------------------------------------------ModuleServiceImpl---------------------------------------------------------------------------------
    public static final String FAILED_MODULE_FILE_ALREADY_EXISTS = "The module file you are trying to upload already exists.\n";
    public static final String FAILED_NO_SUCH_MODULE_FILE = "The module file you are trying to manipulate is non-existent.\n";


    //------------------------------------------CoursesServiceImpl---------------------------------------------------------------------------------

    public static final String CANNOT_UNENROLL_OTHER_USER = "You can only unenroll yourself from a course.\n";
    public static final String CANNOT_ENROLL_OTHER_USER = "You can only enroll yourself to a course.\n";
    public static final String USER_ALREADY_ENROLLED = "You are already enrolled to this course.\n";
    public static final String USER_NOT_ENROLLED = "Username provided are not enrolled to this course.\n";


    //------------------------------------------TopicServiceImpl-----------------------------------------------------------------------------------

    public static final String FAILED_ADD_TOPIC = "An error occurred while adding the new topic.\n";
    public static final String FAILED_GET_TOPIC = "An error occurred while getting data about the topic.\n";
    public static final String FAILED_GET_ALL_TOPICS = "An error occurred while getting data about all the topics.\n";
    public static final String FAILED_UPDATE_TOPIC = "An error occurred while updating the topic.\n";
    public static final String FAILED_DELETE_TOPIC = "An error occurred while deleting the topic.\n";
    public static final String TOPIC_ALREADY_EXISTS = "A topic with this title already exists in the database.\n";


    //------------------------------------------ForumThreadServiceImpl-----------------------------------------------------------------------------

    public static final String ADD_FORUM_THREAD = "An error occurred while adding the thread.\n";
    public static final String GET_ALL_THREADS = "An error occurred while getting all the threads.\n";
    public static final String GET_THREADS_BY_OWNER = "An error occurred while getting all the threads owned by a specific user.\n";
    public static final String GET_THREAD_BY_TITLE = "An error occurred while getting thread data.\n";
    public static final String GET_THREADS_TOPIC = "An error occurred while getting all the threads that are in the topic.\n";
    public static final String UPDATE_THREAD = "An error occurred while updating the thread.\n";
    public static final String DELETE_THREAD_BY_TITLE = "An error occurred while deleting the thread.\n";
    public static final String THREAD_ALREADY_EXISTS = "A thread with this name already exists.\n";


    //------------------------------------------CommentServiceImpl---------------------------------------------------------------------------------

    public static final String ADD_COMMENT = "An error occurred while adding the comment.\n";
    public static final String GET_COMMENT_USER_TIME = "An error occurred while getting the comment by user and timestamp.\n";
    public static final String GET_COMMENTS_THREAD = "An error occurred while getting thread comments.\n";
    public static final String UPDATE_COMMENT = "An error occurred while updating the comment.\n";
    public static final String DELETE_COMMENT = "An error occurred while deleting the comment.\n";


    //------------------------------------------AuditServiceImpl-----------------------------------------------------------------------------------

    public static final String ADD_EVENT = "An error occurred while saving the event.\n";


    //------------------------------------------OnlineTestsServiceImpl------------------------------------------------------------------------
    public static final String FAILED_TEST_ALREADY_EXISTS = "The test you are trying to create already exists.\n";
    public static final String FAILED_TEST_NOT_EXISTS = "The test you are trying to use not exists.\n";
    public static final String FAILED_NO_ATTEMPTS_AVAILABLE = "We are sorry. You don't have available attempts.\n";

    public ServiceException(String message) {
        super(message);
    }
}
