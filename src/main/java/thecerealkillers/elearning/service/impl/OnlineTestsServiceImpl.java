package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.CoursesDAO;
import thecerealkillers.elearning.dao.OnlineTestsDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.*;
import thecerealkillers.elearning.service.OnlineTestsService;
import thecerealkillers.elearning.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with love by Lucian and @Pi on 30.12.2015.
 */
@Service
public class OnlineTestsServiceImpl implements OnlineTestsService {

    @Autowired
    private OnlineTestsDAO onlineTestsDAO;
    @Autowired
    private CoursesDAO coursesDAO;

    @Override
    public void createTest(OnlineTest onlineTest) throws ServiceException {
        try {
            if (onlineTestsDAO.isTestExistent(onlineTest)) {
                throw new ServiceException(ServiceException.FAILED_TEST_ALREADY_EXISTS);
            }
            onlineTestsDAO.addTest(onlineTest);
        } catch (DAOException daoExeption) {
            throw new ServiceException(daoExeption.getMessage());
        }
    }

    @Override
    public void deleteTest(String title, String course) throws ServiceException {
        try {
            OnlineTest onlineTest = new OnlineTest(title, course);
            if (onlineTestsDAO.isTestExistent(onlineTest)) {
                onlineTestsDAO.deleteTest(onlineTest);
            } else {
                throw new ServiceException(ServiceException.FAILED_TEST_NOT_EXISTS);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<UserPoints> getStudentsPoints(String course, String test, String username) throws ServiceException {
        try {
            OnlineTest onlineTest = new OnlineTest(test, course);
            if (onlineTestsDAO.isTestExistent(onlineTest)) {
                if (coursesDAO.userIsEnrolled(course, username)) {
                    return onlineTestsDAO.getStudentPoints(course, test, username);
                } else {
                    throw new ServiceException(ServiceException.USER_NOT_ENROLLED);
                }
            } else {
                throw new ServiceException(ServiceException.FAILED_TEST_NOT_EXISTS);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<QuestionsTest> getOnlineTest(String course, String test, String username, String userRole) throws ServiceException {
        try {
            OnlineTest onlineTest = new OnlineTest(test, course);
            if (onlineTestsDAO.isTestExistent(onlineTest)) {
                if (userRole.equals(Constants.TEACHER)) {
                    return onlineTestsDAO.getOnlineTest(course, test);
                } else if (coursesDAO.userIsEnrolled(course, username)) {
                    if (onlineTestsDAO.userHasAvailableAttempts(onlineTest, username)) {
                        return onlineTestsDAO.getOnlineTest(course, test);
                    } else {
                        throw new ServiceException(ServiceException.FAILED_NO_ATTEMPTS_AVAILABLE);
                    }
                } else {
                    throw new ServiceException(ServiceException.USER_NOT_ENROLLED);
                }
            } else {
                throw new ServiceException(ServiceException.FAILED_TEST_NOT_EXISTS);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public TestCompleted takeOnlineTest(String usernameForToken, String course, String test, List<Answer> userAnswerList) throws ServiceException {
        try {
            OnlineTest onlineTest = new OnlineTest(test, course);
            if (onlineTestsDAO.isTestExistent(onlineTest)) {
                if (coursesDAO.userIsEnrolled(course, usernameForToken)) {
                    if (onlineTestsDAO.userHasAvailableAttempts(onlineTest, usernameForToken)) {

                        List<Answer> testAnswers = onlineTestsDAO.getOnlineTestAnswers(course, test);
                        int userPoints = getUserPoints(testAnswers, userAnswerList);
                        int totalPoints = getTestTotalPoints(testAnswers);

                        onlineTestsDAO.takeOnlineTest(userPoints, course, test, usernameForToken);

                        int attemptsRemaining = getAttemptsRemaining(usernameForToken, course, test);
                        List<Answer> wrongAnswersList = getWrongAnswers(course, test, testAnswers, userAnswerList);

                        return new TestCompleted(userPoints, totalPoints, attemptsRemaining, wrongAnswersList);
                    } else {
                        throw new ServiceException(ServiceException.FAILED_NO_ATTEMPTS_AVAILABLE);
                    }
                } else {
                    throw new ServiceException(ServiceException.USER_NOT_ENROLLED);
                }
            } else {
                throw new ServiceException(ServiceException.FAILED_TEST_NOT_EXISTS);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Returns list with all wrong answers
     *
     * @param course
     * @param test
     * @param testAnswers
     * @param userAnswerList
     * @return
     * @throws ServiceException
     */
    private List<Answer> getWrongAnswers(String course, String test, List<Answer> testAnswers, List<Answer> userAnswerList) throws ServiceException {
        try {
            List<Answer> wrongAnswerList = new ArrayList<>();
            for (int contorAnswer = 0; contorAnswer < testAnswers.size(); contorAnswer++) {
                Answer currentTestAnswer = testAnswers.get(contorAnswer);
                Answer currentUserAnswer = userAnswerList.get(contorAnswer);
                if (!currentUserAnswer.equals(currentTestAnswer)) {
                    Question question = onlineTestsDAO.getQuestion(course, test, currentTestAnswer.getText());
                    wrongAnswerList.add(getCorrectAnswers(question, currentUserAnswer));
                }
            }
            return wrongAnswerList;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Compare user's answers for specific question with initial answers created by professor
     *
     * @param question
     * @param currentUserAnswer
     * @return
     */
    private Answer getCorrectAnswers(Question question, Answer currentUserAnswer) {
        Answer answer = new Answer();
        answer.setText(question.getText());
        if (question.getCorrect1().equals(currentUserAnswer.getAnswer1())) {
            answer.setAnswer1(Constants.ONLINE_TEST_CORRECT + question.getCorrect1() + Constants.ONLINE_TEST_SEPARATOR + question.getAnswer1());
        } else {
            answer.setAnswer1(Constants.ONLINE_TEST_INCORRECT + question.getCorrect1() + Constants.ONLINE_TEST_SEPARATOR + question.getAnswer1());
        }
        if (question.getCorrect2().equals(currentUserAnswer.getAnswer2())) {
            answer.setAnswer2(Constants.ONLINE_TEST_CORRECT + question.getCorrect2() + Constants.ONLINE_TEST_SEPARATOR + question.getAnswer2());
        } else {
            answer.setAnswer2(Constants.ONLINE_TEST_INCORRECT + question.getCorrect2() + Constants.ONLINE_TEST_SEPARATOR + question.getAnswer2());
        }
        if (question.getCorrect3().equals(currentUserAnswer.getAnswer3())) {
            answer.setAnswer3(Constants.ONLINE_TEST_CORRECT + question.getCorrect3() + Constants.ONLINE_TEST_SEPARATOR + question.getAnswer3());
        } else {
            answer.setAnswer3(Constants.ONLINE_TEST_INCORRECT + question.getCorrect3() + Constants.ONLINE_TEST_SEPARATOR + question.getAnswer3());
        }
        if (question.getCorrect4().equals(currentUserAnswer.getAnswer4())) {
            answer.setAnswer4(Constants.ONLINE_TEST_CORRECT + question.getCorrect4() + Constants.ONLINE_TEST_SEPARATOR + question.getAnswer4());
        } else {
            answer.setAnswer4(Constants.ONLINE_TEST_INCORRECT + question.getCorrect4() + Constants.ONLINE_TEST_SEPARATOR + question.getAnswer4());
        }
        return answer;
    }

    /**
     * Calculate total test attempts - already tried attempts
     *
     * @param usernameForToken
     * @param course
     * @param test
     * @return
     * @throws ServiceException
     */
    private int getAttemptsRemaining(String usernameForToken, String course, String test) throws ServiceException {
        try {
            int currentAttemptNumber = onlineTestsDAO.getLastAttemptNumber(course, test, usernameForToken);
            int totalAttempts = onlineTestsDAO.getOnlineTestAttempts(course, test);
            return totalAttempts - currentAttemptNumber;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Assume that any question has a maximum 100 points (4 answers * 25 points for correct answer)
     *
     * @param testAnswers
     * @return number of questions * maximum points for question
     */
    private int getTestTotalPoints(List<Answer> testAnswers) {
        return testAnswers.size() * 100;
    }

    /**
     * Functions returns user's points for current tried test
     *
     * @param testAnswers a list with all test answers from DB
     * @param userAnswerList a list with user's answers
     * @return
     */
    private int getUserPoints(List<Answer> testAnswers, List<Answer> userAnswerList) {
        int userPoints = 0;
        for (int contorAnswer = 0; contorAnswer < testAnswers.size(); contorAnswer++) {
            Answer currentTestAnswer = testAnswers.get(contorAnswer);
            Answer currentUserAnswer = userAnswerList.get(contorAnswer);

            userPoints += getAnswerPoints(currentTestAnswer, currentUserAnswer);
        }
        return userPoints;
    }

    /**
     * Functions checks all answers for current question
     * and returns points for current question.
     *
     * @param currentTestAnswer
     * @param currentUserAnswer
     * @return
     */
    private int getAnswerPoints(Answer currentTestAnswer, Answer currentUserAnswer) {
        int score = 0;
        if (currentTestAnswer.getAnswer1().equals(currentUserAnswer.getAnswer1())) {
            score += Constants.ONLINE_TEST_ANSWER_CORRECT;
        }
        if (currentTestAnswer.getAnswer2().equals(currentUserAnswer.getAnswer2())) {
            score += Constants.ONLINE_TEST_ANSWER_CORRECT;
        }
        if (currentTestAnswer.getAnswer3().equals(currentUserAnswer.getAnswer3())) {
            score += Constants.ONLINE_TEST_ANSWER_CORRECT;
        }
        if (currentTestAnswer.getAnswer4().equals(currentUserAnswer.getAnswer4())) {
            score += Constants.ONLINE_TEST_ANSWER_CORRECT;
        }
        return score;
    }
}
