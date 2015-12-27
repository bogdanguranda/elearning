package thecerealkillers.elearning.dao.impl;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.UserDAO;
import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.model.User;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.*;


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
    public User get(String username) throws DAOException, NotFoundException {
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
                throw new NotFoundException(NotFoundException.NO_USER);

            return userList.get(0);
        } catch (NotFoundException notFound) {
            throw notFound;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() throws DAOException, NotFoundException {
        try {
            List<User> users;
            String sqlCommand = "SELECT * FROM user;";

            users = namedParameterJdbcTemplate.query(sqlCommand, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    User user = new User();

                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setHash("");
                    user.setSalt("");

                    return user;
                }
            });

            if (users.size() == 0)
                throw new NotFoundException(NotFoundException.NO_USERS);

            return users;
        } catch (NotFoundException notFound) {
            throw notFound;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void changePassword(String username, String newSalt, String newHash) throws DAOException {
        try {
            String sqlCommand = "UPDATE user SET hash = :hash, salt = :salt WHERE username = :username";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("salt", newSalt);
            namedParameters.put("hash", newHash);
            namedParameters.put("username", username);
            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);

        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void deleteAccount(String username) throws DAOException {

        try {
            String sqlCommand = "DELETE FROM user WHERE username = :username";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("username", username);
            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);

        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public Boolean isAvailable(String username, String email) throws DAOException {
        return isUsernameAvailable(username) && isEmailAvailable(email);
    }

    @Override
    public Boolean isUsernameAvailable(String username) throws DAOException {
        try {
            String sql = "select username from user where username = :username;";
            Map<String, String> namedParameters = Collections.singletonMap("username", username);

            List<String> userList = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("username");
                }
            });

            return userList.size() == 0;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public Boolean isEmailAvailable(String email) throws DAOException {
        try {
            String sql = "select username from user where email = :email;";
            Map<String, String> namedParameters = Collections.singletonMap("email", email);

            List<String> userList = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("username");
                }
            });

            return userList.size() == 0;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
