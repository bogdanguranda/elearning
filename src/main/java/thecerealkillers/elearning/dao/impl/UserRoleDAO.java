package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.exceptions.DAOException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with love by Lucian and @Pi on 15.12.2015.
 */
@Repository
public class UserRoleDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void addRole(String username, String role) throws DAOException {

        try {
            String sql = "INSERT INTO user_role VALUE (:username, :role);";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("username", username);
            namedParameters.put("role", role);
            namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
}
