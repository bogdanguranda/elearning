package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.OnlineTestsDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.OnlineTest;

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
            String sqlCommand = "SELECT * FROM test WHERE testTitle = :testTitle AND courseTitle = :courseTitle";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("testTitle", onlineTest.getTestTitle());
            namedParameters.put("courseTitle", onlineTest.getCourseTitle());

            onlineTestList = namedParameterJdbcTemplate.query(sqlCommand, namedParameters, new RowMapper<OnlineTest>() {
                @Override
                public OnlineTest mapRow(ResultSet resultSet, int i) throws SQLException {

                    OnlineTest test = new OnlineTest();
                    test.setCourseTitle(resultSet.getString("courseTitle"));
                    test.setNumberOfTries(resultSet.getString("numberOfTries"));
                    test.setTestTitle(resultSet.getString("testTitle"));

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
            String sqlCommand = "INSERT INTO test VALUE (:testTitle, :courseTitle, :numberOfTries);";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("testTitle", onlineTest.getTestTitle());
            namedParameters.put("courseTitle", onlineTest.getCourseTitle());
            namedParameters.put("numberOfTries", onlineTest.getNumberOfTries());

            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
}
