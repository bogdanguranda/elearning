package thecerealkillers.elearning.dao.impl;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.SessionDAO;
import thecerealkillers.elearning.model.SessionDM;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cuvidk on 11/20/2015.
 * Modified by Dani.
 */
@Repository
public class SessionDAOImpl implements SessionDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void addSession(SessionDM session) throws DAOException {
        try {
            String sqlCommand = "INSERT INTO session VALUES (:username, :token, DEFAULT);";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("username", session.getUsername());
            namedParameters.put("token", session.getToken());

            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public SessionDM getSessionByUser(String username) throws DAOException {
        try {
            String sql = "SELECT * FROM session WHERE username = :username;";
            Map<String, String> namedParameters = Collections.singletonMap("username", username);

            List<SessionDM> sessions = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<SessionDM>() {
                @Override
                public SessionDM mapRow(ResultSet resultSet, int i) throws SQLException {
                    SessionDM session = new SessionDM();

                    session.setCreationStamp(resultSet.getTimestamp("creationTimestamp"));
                    session.setUsername(resultSet.getString("username"));
                    session.setToken(resultSet.getString("token"));

                    return session;
                }
            });

            if (sessions.size() == 0)
                throw new DAOException("Inexistent session for username " + username);

            return sessions.get(0);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public SessionDM getSessionByToken(String token) throws DAOException {
        try {
            String sql = "SELECT * FROM session WHERE token = :token;";
            Map<String, String> namedParameters = Collections.singletonMap("token", token);

            List<SessionDM> sessions = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<SessionDM>() {
                @Override
                public SessionDM mapRow(ResultSet resultSet, int i) throws SQLException {
                    SessionDM session = new SessionDM();

                    session.setUsername(resultSet.getString("username"));
                    session.setCreationStamp(resultSet.getTimestamp("creationTimestamp"));
                    session.setToken(resultSet.getString("token"));

                    return session;
                }
            });

            if (sessions.size() == 0)
                throw new DAOException("Inexistent session for token " + token);

            return sessions.get(0);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public boolean isSessionAvailable(String username) throws DAOException {
        try {
            String sql = "SELECT username FROM session WHERE username = :username;";
            Map<String, String> namedParameters = Collections.singletonMap("username", username);

            List<String> users = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("username");
                }
            });

            return users.size() != 0;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void deleteSession(String username) throws DAOException {
        try {
            String sqlCommand = "DELETE FROM session WHERE username = :username;";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("username", username);

            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
