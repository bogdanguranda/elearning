package thecerealkillers.elearning.validator;

import thecerealkillers.elearning.exceptions.InvalidOnlineTestException;
import thecerealkillers.elearning.model.OnlineTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with love by Lucian and @Pi on 30.12.2015.
 */
public class OnlineTestValidator extends Validator {
    public static void validate(OnlineTest onlineTest) throws InvalidOnlineTestException {

        String feedback = "";

        if (!isValidTestTitle(onlineTest.getTestTitle())) {
            feedback += "Invalid test title. Title cannot be empty.\n";
        }
        if (!isValidCourseTitle(onlineTest.getCourseTitle())) {
            feedback += "Invalid course title. Course title cannot be empty.\n";
        }
        if (!isValidNumberOfTries(onlineTest.getNumberOfTries())) {
            feedback += "Invalid number for available tries. The number must be between 1 and 10.\n";
        }

        if (!"".equals(feedback)) {
            throw new InvalidOnlineTestException(feedback);
        }
    }

    private static boolean isValidNumberOfTries(String numberOfTries) {
        Pattern pattern = Pattern.compile("[1-9]");
        Matcher matcher = pattern.matcher(numberOfTries);
        return matcher.matches();
    }

    private static boolean isValidCourseTitle(String courseTitle) {
        if (isEmpty(courseTitle))
            return false;
        return true;
    }

    private static boolean isValidTestTitle(String testTitle) {
        if (isEmpty(testTitle))
            return false;
        return true;
    }
}
