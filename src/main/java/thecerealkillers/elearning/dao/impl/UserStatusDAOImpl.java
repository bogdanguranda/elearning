package thecerealkillers.elearning.dao.impl;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.UserStatusDAO;
import thecerealkillers.elearning.model.UserStatus;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Dani
 */
@Repository
public class UserStatusDAOImpl implements UserStatusDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void add(UserStatus userStatus) throws DAOException {
        try {
            String sqlCommand = "INSERT INTO user_status VALUES (:username, FALSE, DEFAULT, :token);";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("username", userStatus.getUsername());
            namedParameters.put("token", userStatus.getToken());
            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void update(String username, String newToken) throws DAOException {
        try {
            String sqlCommand;

            if (newToken.compareTo("") == 0) {
                sqlCommand = "UPDATE user_status SET token = :newToken, signUpTimestamp =  CAST('2000-01-01' AS DATETIME) " +
                        "WHERE username = :username";
                newToken = username;
            } else {
                sqlCommand = "UPDATE user_status SET token = :newToken, signUpTimestamp = DEFAULT WHERE username = :username";
            }

            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("newToken", newToken);
            namedParameters.put("username", username);
            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public UserStatus get(String username) throws DAOException {
        try {
            String sqlCommand = "select * from user_status where username = :username;";
            Map<String, String> namedParameters = Collections.singletonMap("username", username);

            List<UserStatus> statuses = namedParameterJdbcTemplate.query(sqlCommand,
                    namedParameters, new RowMapper<UserStatus>() {
                        @Override
                        public UserStatus mapRow(ResultSet resultSet, int i) throws SQLException {
                            UserStatus status = new UserStatus();

                            status.setUsername(resultSet.getString("username"));
                            status.setActive(resultSet.getBoolean("active"));
                            status.setSignUpTimestamp(resultSet.getTimestamp("signUpTimestamp"));
                            status.setToken(resultSet.getString("token"));

                            return status;
                        }
                    });

            if (statuses.size() == 0)
                throw new DAOException("Inexistent user with username: " + username);
            return statuses.get(0);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void activateAccount(String username) throws DAOException {
        try {
            String sqlCommand = "UPDATE user_status SET active = TRUE WHERE username = :username";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("username", username);
            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);

            this.update(username, "");
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void suspendAccount(String username) throws DAOException {
        try {
            String sqlCommand = "UPDATE user_status SET active = FALSE WHERE username = :username";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("username", username);
            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);

            this.update(username, "");
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void accountDeleted(String username) throws DAOException {
        try {
            String sqlCommand = "DELETE FROM user_status WHERE username = :username";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("username", username);
            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public boolean isAccountActivated(String username) throws DAOException {
        return this.get(username).getActive().equals(true);
    }
}
