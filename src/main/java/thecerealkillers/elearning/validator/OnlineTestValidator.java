package thecerealkillers.elearning.validator;

import thecerealkillers.elearning.exceptions.InvalidOnlineTestException;
import thecerealkillers.elearning.model.OnlineTest;
import thecerealkillers.elearning.model.Question;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with love by Lucian and @Pi on 30.12.2015.
 */
public class OnlineTestValidator extends Validator {

    private static boolean isValidNumberOfTries(String numberOfTries) {
        Pattern pattern = Pattern.compile("[1-9]");
        Matcher matcher = pattern.matcher(numberOfTries);
        return matcher.matches();
    }

    private static boolean isValidCourseTitle(String courseTitle) {
        return !isEmpty(courseTitle);
    }

    private static boolean isValidTestTitle(String testTitle) {
        return !isEmpty(testTitle);
    }

    private static boolean answerIsSet(String correct) {
        return "true".equals(correct) || "false".equals(correct);
    }

    public static void validate(OnlineTest onlineTest) throws InvalidOnlineTestException {
        String feedback = "";

        if (!isValidTestTitle(onlineTest.getTitle())) {
            feedback += "Invalid test title. Title cannot be empty.\n";
        }
        if (!isValidCourseTitle(onlineTest.getCourse())) {
            feedback += "Invalid course title. Course title cannot be empty.\n";
        }
        if (!isValidNumberOfTries(onlineTest.getAttempts())) {
            feedback += "Invalid number for available attempts. The number must be between 1 and 10.\n";
        }
        if (!isValidQuestionList(onlineTest.getQuestions())) {
            feedback += "One or more questions are invalid. A question must have set it's text and if it's correct or not. Also, a answer cannot be empty.\n";
        }

        if (!"".equals(feedback)) {
            throw new InvalidOnlineTestException(feedback);
        }
    }

    private static boolean isValidQuestionList(List<Question> questionList) {
        for (Question question :
                questionList) {
            if (isEmpty(question.getText())) {
                return false;
            }
            if (isEmpty(question.getAnswer1()) || isEmpty(question.getAnswer2()) || isEmpty(question.getAnswer3()) || isEmpty(question.getAnswer4())) {
                return false;
            }
            if (!answerIsSet(question.getCorrect1()) || !answerIsSet(question.getCorrect2()) || !answerIsSet(question.getCorrect3()) || !answerIsSet(question.getCorrect4())) {
                return false;
            }
        }
        return true;
    }
}
