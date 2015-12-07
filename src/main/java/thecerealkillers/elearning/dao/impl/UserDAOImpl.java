package thecerealkillers.elearning.dao.impl;

import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.UserDAO;
import thecerealkillers.elearning.model.User;

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
 * Created by cuvidk on 11/8/2015.
 * Modified by Dani.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void signUp(User user) throws DAOException {
        try {
            String sqlCommand = "insert into user values(:username, :firstName, :lastName, :email, :hash, :salt);";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("username", user.getUsername());
            namedParameters.put("firstName", user.getFirstName());
            namedParameters.put("lastName", user.getLastName());
            namedParameters.put("email", user.getEmail());
            namedParameters.put("hash", user.getHash());
            namedParameters.put("salt", user.getSalt());

            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public User get(String username) throws DAOException {
        try {
            String sql = "select * from user where username = :username;";
            Map<String, String> namedParameters = Collections.singletonMap("username", username);

            List<User> userList = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    User user = new User();

                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setHash(resultSet.getString("hash"));
                    user.setSalt(resultSet.getString("salt"));

                    return user;
                }
            });
            if (userList.size() == 0)
                throw new DAOException("Inexistent user with username: " + username);
            return userList.get(0);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public boolean isUsernameAvailable(String username) throws DAOException {
        try {
            String sql = "select username from user where username = :username;";
            Map<String, String> namedParameters = Collections.singletonMap("username", username);

            List<String> userList = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("username");
                }
            });

            if (userList.size() != 0)
                throw new DAOException("Username " + username + " is not available.");

            return true;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public boolean isEmailAvailable(String email) throws DAOException {
        try {
            String sql = "select username from user where email = :email;";
            Map<String, String> namedParameters = Collections.singletonMap("email", email);

            List<String> userList = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("username");
                }
            });

            if (userList.size() != 0)
                throw new DAOException("Email " + email + " is not available.");

            return true;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        try {
            List<User> users;
            String sqlCommand = "select * from user;";

            users = namedParameterJdbcTemplate.query(sqlCommand, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    User user = new User();

                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    //user.setHash(resultSet.getString("hash"));
                    //user.setSalt(resultSet.getString("salt"));
                    user.setHash("");
                    user.setSalt("");

                    return user;
                }
            });
            return users;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void changePassword(String username, String newHash) throws DAOException  {
        try {
            String sqlCommand = "UPDATE user SET hash = :hash WHERE username = :username";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("hash", newHash);
            namedParameters.put("username", username);
            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);

        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void delete(String username) throws DAOException {

        try {
            String sqlCommand = "DELETE FROM user WHERE username = :username";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("username", username);
            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);

        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

}
