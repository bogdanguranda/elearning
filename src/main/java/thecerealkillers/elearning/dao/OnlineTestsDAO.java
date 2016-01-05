package thecerealkillers.elearning.dao;

import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.OnlineTest;
import thecerealkillers.elearning.model.Question;
import thecerealkillers.elearning.model.QuestionsTest;
import thecerealkillers.elearning.model.UserPoints;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 30.12.2015.
 */
@Repository
public interface OnlineTestsDAO {

    boolean isTestExistent(OnlineTest onlineTest) throws DAOException;

    void addTest(OnlineTest onlineTest) throws DAOException;

    void deleteTest(OnlineTest onlineTest) throws DAOException;

    List<UserPoints> getStudentPoints(String course, String test, String username) throws DAOException;

    List<QuestionsTest> getOnlineTest(String course, String test) throws DAOException;
}
