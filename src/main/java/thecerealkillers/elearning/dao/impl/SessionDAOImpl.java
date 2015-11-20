package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.SessionDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.SessionDM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cuvidk on 11/20/2015.
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
            String sqlCommand = "insert into session values (:username, :token, default);";
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
                throw new DAOException("Inexistent session for username " + username);
            return sessions.get(0);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public SessionDM getSessionByToken(String token) throws DAOException {
        try {
            String sql = "select * from session where token = :token;";
            Map<String, String> namedParameters = Collections.singletonMap("token", token);

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
                throw new DAOException("Inexistent session for token " + token);
            return sessions.get(0);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
