package thecerealkillers.elearning.validator;

import thecerealkillers.elearning.exceptions.*;
import thecerealkillers.elearning.model.UserLoginInfo;
import thecerealkillers.elearning.model.UserSignUpInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cuvidk on 11/14/2015.
 * Modified by Lucian on 11/16/2015.
 */
public class UserValidator extends Validator {
    /**
     * Checks if the login info is valid.
     *
     * @param loginInfo
     * @return a string that describes what's wrong with @loginInfo,
     * empty string else.
     */
    public static void validateLoginInfo(UserLoginInfo loginInfo) throws InvalidLoginInfoException {
        String feedback = "";

        try {
            validateUsername(loginInfo.getUsername());
        } catch (InvalidUsernameException ex_username) {
            feedback += ex_username.getMessage();
        }
        try {
            validatePassword(loginInfo.getPassword());
        } catch (InvalidPasswordException ex_password) {
            feedback += ex_password.getMessage();
        }

        if (!"".equals(feedback))
            throw new InvalidLoginInfoException(feedback);
    }

    /**
     * Checks if the sign-up info is valid.
     *
     * @param signUpInfo
     * @return a string that describes what's wrong with @singUpInfo,
     * empty string else.
     */
    public static void validateSignUpInfo(UserSignUpInfo signUpInfo) throws InvalidSignUpInfoException {
        String feedback = "";
        try {
            validateUsername(signUpInfo.getUsername());
        } catch (InvalidUsernameException ex_username) {
            feedback += ex_username.getMessage();
        }
        try {
            validateName(signUpInfo.getFirstName(), true);
        } catch (InvalidNameException ex_name) {
            feedback += ex_name.getMessage();
        }
        try {
            validateName(signUpInfo.getLastName(), false);
        } catch (InvalidNameException ex_name) {
            feedback += ex_name.getMessage();
        }
        try {
            validatePassword(signUpInfo.getPassword());
        } catch (InvalidPasswordException ex_password) {
            feedback += ex_password.getMessage();
        }
        try {
            validateEmail(signUpInfo.getEmail());
        } catch (InvalidEmailException ex_email) {
            feedback += ex_email.getMessage();
        }

        if (!"".equals(feedback))
            throw new InvalidSignUpInfoException(feedback);

    }

    /**
     * Checks if the FirstName or the LastName have the correct format
     *
     * @param name
     * @return a string that describes what's wrong with @name,
     * empty string else
     */
    private static void validateName(String name, boolean isFirstName) throws InvalidNameException {
        String feedback = "";
        Pattern pattern;
        Matcher matcher;
        String NAME_PATTERN = "[A-Z][a-zA-Z]*";

        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(name);

        if (!matcher.matches() && isFirstName)
            feedback += InvalidNameException.FIRST_NAME_WRONG_FORMAT;
        if (!matcher.matches() && !isFirstName)
            feedback += InvalidNameException.LAST_NAME_WRONG_FORMAT;

        if (!"".equals(feedback))
            throw new InvalidNameException(feedback);
    }


    /**
     * Checks if @username is a suitable username.
     *
     * @param username
     * @return a string that describes what's wrong with @username,
     * empty string else.
     */
    public static void validateUsername(String username) throws InvalidUsernameException {
        String feedback = "";

        if (username.length() < 3)
            feedback += InvalidUsernameException.USERNAME_TO_SHORT;
        if (!isAlphanumeric(username))
            feedback += InvalidUsernameException.USERNAME_FORBIDDEN_CHARACTER;

        if (!"".equals(feedback)) {
            throw new InvalidUsernameException(feedback);
        }
    }

    /**
     * Checks if @password is a suitable password.
     *
     * @param password
     * @return a string that describes what's wrong with @password,
     * empty string else.
     */
    public static void validatePassword(String password) throws InvalidPasswordException {
        String feedback = "";

        if (password.length() < 3)
            feedback += InvalidPasswordException.PASSWORD_TOO_SHORT;
//        if (!containsDigits(password))
//            feedback += InvalidPasswordException.PASSWORD_NOT_CONTAINS_DIGIT_CHARACTER;
//        if (!containsLowerCase(password) && !containsUpperCase(password))
//            feedback += InvalidPasswordException.PASSWORD_NOT_CONTAINS_ALPHABETIC_CHARACTER;

        if (!"".equals(feedback))
            throw new InvalidPasswordException(feedback);
    }

    /**
     * Checks if @email is in a correct email format
     *
     * @param email
     * @return a string that describes what's wrong with @email,
     * empty string else
     */
    public static void validateEmail(String email) throws InvalidEmailException {
        String feedback = "";

        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        if (!matcher.matches())
            feedback += InvalidEmailException.EMAIL_INVALID;

        if (!"".equals(feedback))
            throw new InvalidEmailException(feedback);
    }
}
