package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.OnlineTestsDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.OnlineTest;
import thecerealkillers.elearning.model.Question;
import thecerealkillers.elearning.model.UserPoints;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with love by Lucian and @Pi on 30.12.2015.
 */
@Repository
public class OnlineTestDAOImpl implements OnlineTestsDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public boolean isTestExistent(OnlineTest onlineTest) throws DAOException {
        try {
            List<OnlineTest> onlineTestList;
            String sqlCommand = "SELECT * FROM test WHERE title = :testTitle AND course = :courseTitle";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("testTitle", onlineTest.getTitle());
            namedParameters.put("courseTitle", onlineTest.getCourse());

            onlineTestList = namedParameterJdbcTemplate.query(sqlCommand, namedParameters, new RowMapper<OnlineTest>() {
                @Override
                public OnlineTest mapRow(ResultSet resultSet, int i) throws SQLException {

                    OnlineTest test = new OnlineTest();
                    test.setCourse(resultSet.getString("course"));
                    test.setAttempts(resultSet.getString("attempts"));
                    test.setTitle(resultSet.getString("title"));

                    return test;
                }
            });

            if (onlineTestList.size() > 0)
                return true;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
        return false;
    }

    @Override
    public void addTest(OnlineTest onlineTest) throws DAOException {
        try {
            String sqlCommand = "INSERT INTO test VALUE (:title, :course, :attempts);";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", onlineTest.getTitle());
            namedParameters.put("course", onlineTest.getCourse());
            namedParameters.put("attempts", onlineTest.getAttempts());

            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);

            namedParameters.remove("title");
            namedParameters.remove("course");
            namedParameters.remove("attempts");

            for (Question question :
                    onlineTest.getQuestions()) {
                sqlCommand = "INSERT INTO question (course, title, text, answer1, correct1, answer2, correct2, answer3, correct3, answer4, correct4) " +
                        "value (:course, :title, :text, :answer1, :correct1, :answer2, :correct2, :answer3, :correct3, :answer4, :correct4);";
                namedParameters.put("course", onlineTest.getCourse());
                namedParameters.put("title", onlineTest.getTitle());
                namedParameters.put("text", question.getText());
                namedParameters.put("answer1", question.getAnswer1());
                namedParameters.put("correct1", question.getCorrect1());
                namedParameters.put("answer2", question.getAnswer2());
                namedParameters.put("correct2", question.getCorrect2());
                namedParameters.put("answer3", question.getAnswer3());
                namedParameters.put("correct3", question.getCorrect3());
                namedParameters.put("answer4", question.getAnswer4());
                namedParameters.put("correct4", question.getCorrect4());

                namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
            }
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public void deleteTest(OnlineTest onlineTest) throws DAOException {
        try {
            String sqlCommand = "DELETE FROM test WHERE title = :testTitle AND course = :courseTitle";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("testTitle", onlineTest.getTitle());
            namedParameters.put("courseTitle", onlineTest.getCourse());

            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public List<UserPoints> getStudentPoints(String course, String test, String username) throws DAOException {
        try {
            String sql = "SELECT attemptNumber, points FROM test_user WHERE title = :title AND course = :course AND username = :username;";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", test);
            namedParameters.put("course", course);
            namedParameters.put("username", username);

            return namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<UserPoints>() {
                @Override
                public UserPoints mapRow(ResultSet resultSet, int i) throws SQLException {
                    return new UserPoints(resultSet.getString("attemptNumber"), resultSet.getString("points"));
                }
            });
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
}
