package thecerealkillers.elearning.validator;

import thecerealkillers.elearning.model.UserLoginInfo;
import thecerealkillers.elearning.model.UserSignUpInfo;

/**
 * Created by cuvidk on 11/14/2015.
 */
public class UserValidator extends Validator {
    /**
     * Checks if the login info is valid.
     * @param loginInfo
     * @return a string that describes what's wrong with @loginInfo,
     * empty string else.
     */
    public static String validateLoginInfo(UserLoginInfo loginInfo) {
        return validatePassword(loginInfo.getPassword()) +
                validateUsername(loginInfo.getUsername());
    }

    /**
     * Checks if the sign-up info is valid.
     * @param signUpInfo
     * @return a string that describes what's wrong with @singUpInfo,
     * empty string else.
     */
    public static String validateSignUpInfo(UserSignUpInfo signUpInfo) {
        //TODO: this is not fully developed, not at all
        String feedback = "";

        feedback += validateUsername(signUpInfo.getUsername());
        feedback += validatePassword(signUpInfo.getPassword());

        return feedback;
    }

    /**
     * Checks if @username is a suitable username.
     * @param username
     * @return a string that describes what's wrong with @username,
     * empty string else.
     */
    public static String validateUsername(String username) {
        //TODO: this is not fully developed, not at all
        String feedback = "";

        if (username.length() < 6)
            feedback += "Username should be composed from at least 6 characters.\n";
        if (!isAlphanumeric(username))
            feedback += "Username should only contain alphanumeric characters.\n";
        return feedback;
    }

    /**
     * Checks if @password is a suitable password.
     * @param password
     * @return a string that describes what's wrong with @password,
     * empty string else.
     */
    public static String validatePassword(String password) {
        //TODO: this is not fully developed, not at all
        String feedback = "";

        if (password.length() < 7)
            feedback += "Password should be composed from at least 7 characters.\n";
        if (!containsDigits(password))
            feedback += "Password should contain at least 1 digit.\n";
        if (!containsLowerCase(password) && !containsUpperCase(password))
            feedback += "Password should contain alphabetic characters also.\n";
        return feedback;
    }
}
