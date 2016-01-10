package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.OnlineTestsDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with love by Lucian and @Pi on 30.12.2015.
 */
@SuppressWarnings("ALL")
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

            //noinspection Convert2Lambda
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

    @Override
    public List<QuestionsTest> getOnlineTest(String course, String test) throws DAOException {
        try {
            String sql = "SELECT text, answer1, answer2, answer3, answer4 FROM question WHERE course = :course AND title = :title";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("course", course);
            namedParameters.put("title", test);

            //noinspection Convert2Lambda
            return namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<QuestionsTest>() {
                @Override
                public QuestionsTest mapRow(ResultSet resultSet, int i) throws SQLException {
                    QuestionsTest questionsTest = new QuestionsTest();
                    questionsTest.setText(resultSet.getString("text"));
                    questionsTest.setAnswer1(resultSet.getString("answer1"));
                    questionsTest.setAnswer2(resultSet.getString("answer2"));
                    questionsTest.setAnswer3(resultSet.getString("answer3"));
                    questionsTest.setAnswer4(resultSet.getString("answer4"));
                    return questionsTest;
                }
            });
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public boolean userHasAvailableAttempts(OnlineTest onlineTest, String username) throws DAOException {
        try {
            String sql = "SELECT attempts FROM test WHERE title = :title AND course = :course;";
            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", onlineTest.getTitle());
            namedParameters.put("course", onlineTest.getCourse());

            @SuppressWarnings("Convert2Lambda") List<String> attemptsList = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("attempts");
                }
            });
            int onlineTestAttempts = Integer.parseInt(attemptsList.get(0));
            int lastAttemptsNumber = getLastAttemptNumber(onlineTest.getCourse(), onlineTest.getTitle(), username);

            return lastAttemptsNumber < onlineTestAttempts;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public int getLastAttemptNumber(String course, String title, String username) throws DAOException {
        try {
            String sqlCommand = "SELECT attemptNumber FROM test_user WHERE title = :title AND course = :course AND username = :username ORDER BY attemptNumber DESC;";
            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", title);
            namedParameters.put("course", course);
            namedParameters.put("username", username);

            @SuppressWarnings("Convert2Lambda") List<Integer> attemptsNumber = namedParameterJdbcTemplate.query(sqlCommand, namedParameters, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt("attemptNumber");
                }
            });

            if (attemptsNumber.size() == 0) {
                return 0;
            } else {
                return attemptsNumber.get(0);
            }
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public List<Answer> getOnlineTestAnswers(String course, String test) throws DAOException {
        try {
            String sql = "SELECT text, correct1, correct2, correct3, correct4 FROM question WHERE course = :course AND title = :title";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("course", course);
            namedParameters.put("title", test);

            //noinspection Convert2Lambda
            return namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Answer>() {
                @Override
                public Answer mapRow(ResultSet resultSet, int i) throws SQLException {
                    Answer answer = new Answer();
                    answer.setText(resultSet.getString("text"));
                    answer.setAnswer1(resultSet.getString("correct1"));
                    answer.setAnswer2(resultSet.getString("correct2"));
                    answer.setAnswer3(resultSet.getString("correct3"));
                    answer.setAnswer4(resultSet.getString("correct4"));

                    return answer;
                }
            });
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public void takeOnlineTest(int userPoints, String course, String test, String username) throws DAOException {
        try {
            String sql = "INSERT INTO test_user VALUE (:title, :course, :attemptNumber, :username, :points);";
            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", test);
            namedParameters.put("course", course);
            namedParameters.put("attemptNumber", String.valueOf(getLastAttemptNumber(course, test, username) + 1));
            namedParameters.put("username", username);
            namedParameters.put("points", String.valueOf(userPoints));

            namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public int getOnlineTestAttempts(String course, String test) throws DAOException {
        try {
            String sql = "SELECT attempts FROM test WHERE course = :course AND title = :title;";
            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", test);
            namedParameters.put("course", course);

            @SuppressWarnings("Convert2Lambda") List<Integer> attemptsList = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt("attempts");
                }
            });
            return attemptsList.get(0);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public Question getQuestion(String course, String test, String text) throws DAOException {
        try {
            String sql = "SELECT * FROM question WHERE course = :course AND title = :title AND text = :text;";
            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", test);
            namedParameters.put("course", course);
            namedParameters.put("text", text);

            @SuppressWarnings("Convert2Lambda") List<Question> questions = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Question>() {
                @Override
                public Question mapRow(ResultSet resultSet, int i) throws SQLException {
                    Question question = new Question();
                    question.setText(text);
                    question.setCorrect1(resultSet.getString("correct1"));
                    question.setCorrect2(resultSet.getString("correct2"));
                    question.setCorrect3(resultSet.getString("correct3"));
                    question.setCorrect4(resultSet.getString("correct4"));
                    question.setAnswer1(resultSet.getString("answer1"));
                    question.setAnswer2(resultSet.getString("answer2"));
                    question.setAnswer3(resultSet.getString("answer3"));
                    question.setAnswer4(resultSet.getString("answer4"));
                    return question;
                }
            });
            return questions.get(0);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
}
