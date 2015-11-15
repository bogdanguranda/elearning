package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.UserAdminDAO;
import thecerealkillers.elearning.model.SessionDM;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.model.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cuvidk on 11/8/2015.
 */
@Repository
public class UserAdminDAOImpl implements UserAdminDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void addSession(SessionDM session) {
        String sqlCommand = "insert into session values (:username, :token, default);";
        Map<String, String> namedParameters = new HashMap<>();

        namedParameters.put("username", session.getUsername());
        namedParameters.put("token", session.getToken());

        namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
    }

    @Override
    public SessionDM getSession(String username) {
        String sql = "select * from session where username = :username;";
        Map<String, String> namedParameters = Collections.singletonMap("username", username);

        List<SessionDM> sessions = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<SessionDM>() {
            @Override
            public SessionDM mapRow(ResultSet resultSet, int i) throws SQLException {
                SessionDM session = new SessionDM();
                session.setUsername(resultSet.getString("username"));
                session.setCreationStamp(resultSet.getDate("creationTimestamp"));
                session.setToken(resultSet.getString("token"));

                return session;
            }
        });
        if (sessions.size() == 0)
            return null;
        return sessions.get(0);
    }

    @Override
    public void addUser(User user) {
        String sqlCommand = "insert into user values(:username, :firstName, :lastName, :email, :hash, :salt);";
        Map<String, String> namedParameters = new HashMap<>();

        namedParameters.put("username", user.getUsername());
        namedParameters.put("firstName", user.getFirstName());
        namedParameters.put("lastName", user.getLastName());
        namedParameters.put("email", user.getEmail());
        namedParameters.put("hash", user.getHash());
        namedParameters.put("salt", user.getSalt());
        namedParameterJdbcTemplate.update(sqlCommand, namedParameters);

        //Also adds the user in the user_status table as inactive, since it
        //just created the account. Email validation requested in UserAdminService.
        sqlCommand = "insert into user_status values(:username, 0, default);";
        namedParameters.clear();

        namedParameters.put("username", user.getUsername());
        namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
    }

    @Override
    public User get(String username) {
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
            return null;
        return userList.get(0);
    }

    @Override
    public UserStatus getUserStatus(String username) {
        String sqlCommand = "select * from user_status where username = :username;";
        Map<String, String> namedParameters = Collections.singletonMap("username", username);

        List<UserStatus> statuses = namedParameterJdbcTemplate.query(sqlCommand,
                namedParameters, new RowMapper<UserStatus>() {
            @Override
            public UserStatus mapRow(ResultSet resultSet, int i) throws SQLException {
                UserStatus status = new UserStatus();

                status.setUsername(resultSet.getString("username"));
                status.setActive(resultSet.getBoolean("active"));
                status.setSignUpTimestamp(resultSet.getDate("signUpTimestamp"));

                return status;
            }
        });

        if (statuses.size() == 0)
            return null;
        return statuses.get(0);
    }

    @Override
    public List<User> getAll() {
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
                user.setHash(resultSet.getString("hash"));
                user.setSalt(resultSet.getString("salt"));

                return user;
            }
        });
        return users;
    }
}
