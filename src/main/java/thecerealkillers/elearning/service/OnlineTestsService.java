package thecerealkillers.elearning.service;

import org.springframework.stereotype.Service;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.*;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 30.12.2015.
 */
@Service
public interface OnlineTestsService {

    void createTest(OnlineTest onlineTest) throws ServiceException;

    void deleteTest(String title, String course) throws ServiceException;

    List<UserPoints> getStudentsPoints(String course, String test, String username) throws ServiceException;

    List<QuestionsTest> getOnlineTest(String course, String test, String username, String userRole) throws ServiceException;

    TestCompleted takeOnlineTest(String usernameForToken, String course, String test, List<Answer> answerList) throws ServiceException;
}
