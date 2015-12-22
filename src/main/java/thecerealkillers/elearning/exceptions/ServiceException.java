package thecerealkillers.elearning.exceptions;


/**
 * Created by cuvidk on 11/20/2015.
 * Modified by Dani
 * * Added error messages for userService
 */
public class ServiceException extends Exception {
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

    public static final String FAILED_COURSE_INNEXISTENT = "The course you are trying to use is innexistent.\n";

    public static final String FAILED_MODULE_ALREADY_EXISTS = "The module you are trying to create already exists.\n";
    public static final String FAILED_MODULE_INNEXISTENT = "The module you are trying to manipulate is innexistent.\n";

    public ServiceException(String message) {
        super(message);
    }
}
