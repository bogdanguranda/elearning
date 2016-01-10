package thecerealkillers.elearning.validator;

/**
 * Created by cuvidk on 11/14/2015.
 */

import thecerealkillers.elearning.exceptions.InvalidEnrollmentParams;
import thecerealkillers.elearning.exceptions.InvalidUsernameException;

/**
 * This is a generic validator class for strings.
 */
public class Validator {
    /**
     * Checks if @value is an empty string
     *
     * @param value
     * @return true if @value is an empty string, false else
     */
    public static boolean isEmpty(String value) {
        return value.length() == 0;
    }

    /**
     * Checks if @value is an alphanumeric string
     * (is composed only from numbers and alphabet characters)
     *
     * @param value
     * @return true if @value is an alphanumeric string, false else
     */
    public static boolean isAlphanumeric(String value) {
        for (int index = 0; index < value.length(); ++index) {
            if (!Character.isDigit(value.charAt(index)) &&
                    !Character.isLowerCase(value.charAt(index)) &&
                    !Character.isUpperCase(value.charAt(index)))
                return false;
        }
        return true;
    }

    /**
     * Checks if @value contains any digit
     *
     * @param value
     * @return true if @value contains at least 1 digit, false else
     */
    public static boolean containsDigits(String value) {
        for (int index = 0; index < value.length(); ++index) {
            if (Character.isDigit(value.charAt(index)))
                return true;
        }
        return false;
    }

    /**
     * Checks if @value contains any lower-case letter
     *
     * @param value
     * @return true if @value contains at least 1 lower-case, false else
     */
    public static boolean containsLowerCase(String value) {
        for (int index = 0; index < value.length(); ++index) {
            if (Character.isLowerCase(value.charAt(index)))
                return true;
        }
        return false;
    }

    /**
     * Checks if @value contains any upper-case letter
     *
     * @param value
     * @return true if @value contains at least 1 upper-case, false else
     */
    public static boolean containsUpperCase(String value) {
        for (int index = 0; index < value.length(); ++index) {
            if (Character.isUpperCase(value.charAt(index)))
                return true;
        }
        return false;
    }

    public static void validateEnrollment(String title, String username) throws InvalidEnrollmentParams {
        String feedback = "";
        if (title.isEmpty())
            feedback += "Invalid title. Title cannot be empty.\n";
        if (username.isEmpty())
            feedback += "Invalid username. Username cannot be empty.\n";

        if (!"".equals(feedback)) {
            throw new InvalidEnrollmentParams(feedback);
        }
    }

    public static void validateUsername(String username) throws InvalidUsernameException {
        String feedback = "";
        if (username.isEmpty()) {
            feedback = "Please provide the username.";
        }
        if(!"".equals(feedback)) {
            throw new InvalidUsernameException(feedback);
        }
    }
}
