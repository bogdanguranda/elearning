package thecerealkillers.elearning.validator;

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
    public static String validateLoginInfo(UserLoginInfo loginInfo) {
        String feedback = "";

        feedback += validateUsername(loginInfo.getUsername());
        feedback += validatePassword(loginInfo.getPassword());

        return feedback;
    }

    /**
     * Checks if the sign-up info is valid.
     *
     * @param signUpInfo
     * @return a string that describes what's wrong with @singUpInfo,
     * empty string else.
     */
    public static String validateSignUpInfo(UserSignUpInfo signUpInfo) {
        String feedback = "";

        feedback += validateUsername(signUpInfo.getUsername());
        feedback += validateName(signUpInfo.getFirstName());
        feedback += validateName(signUpInfo.getLastName());
        feedback += validatePassword(signUpInfo.getPassword());
        feedback += validateEmail(signUpInfo.getEmail());

        return feedback;
    }

    /**
     * Checks if the FirstName or the LastName have the correct format
     *
     * @param name
     * @return a string that describes what's wrong with @name,
     * empty string else
     */
    private static String validateName(String name) {
        String feedback = "";
        Pattern pattern;
        Matcher matcher;
        String NAME_PATTERN = "[A-Z][a-zA-Z]*";

        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(name);

        if (!matcher.matches())
            feedback += "Name should have an uppercase character in front and lowecase characters in next.\n";
        return feedback;
    }

    /**
     * Checks if @username is a suitable username.
     *
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
     *
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
            feedback += "Password should contain alphabetic characters.\n";
        return feedback;
    }

    /**
     * Checks if @email is in a correct email format
     *
     * @param email
     * @return a string that describes what's wrong with @email,
     * empty string else
     */
    public static String validateEmail(String email) {
        String feedback = "";
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        if (!matcher.matches())
            feedback += "Email is is wrong format.\n";
        return feedback;
    }
}
