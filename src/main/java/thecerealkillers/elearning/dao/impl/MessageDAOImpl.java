package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.MessagesDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lucian on 10.11.2015.
 */

@Repository
public class MessageDAOImpl implements MessagesDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void add(Message message) throws DAOException {
        try {
            String sql = "INSERT INTO message(sender, receiver, timestamp, message) VALUES (:sender, :receiver, DEFAULT, :message);";
            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("sender", message.getSenderUsername());
            namedParameters.put("receiver", message.getReceiverUsername());
            namedParameters.put("message", message.getMessage());

            namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public List<Message> getAll() throws DAOException {
        try {
            List<Message> messageList;
            String sql = "SELECT * FROM message;";

            messageList = namedParameterJdbcTemplate.query(sql, new RowMapper<Message>() {
                @Override
                public Message mapRow(ResultSet resultSet, int i) throws SQLException {
                    Message message = new Message();
                    message.setMessage(resultSet.getString("message"));
                    message.setReceiverUsername(resultSet.getString("receiver"));
                    message.setSenderUsername(resultSet.getString("sender"));
                    message.setTimestamp(resultSet.getTimestamp("timestamp"));

                    return message;
                }
            });

            return messageList;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public List<Message> getMessagesBetweenUsers(String sender, String receiver) throws DAOException {
        try {
            List<Message> messagesBetweenUsers;
            String sql = "select * from message " +
                    "where (sender = '" + sender + "' and receiver = '" + receiver + "') " +
                    "or (receiver = '" + sender + "' and sender = '" + receiver + "');";

            messagesBetweenUsers = namedParameterJdbcTemplate.query(sql, new RowMapper<Message>() {
                @Override
                public Message mapRow(ResultSet resultSet, int i) throws SQLException {
                    Message message = new Message();
                    message.setSenderUsername(resultSet.getString("sender"));
                    message.setReceiverUsername(resultSet.getString("receiver"));
                    message.setTimestamp(resultSet.getTimestamp("timestamp"));
                    message.setMessage(resultSet.getString("message"));

                    return message;
                }
            });

            return messagesBetweenUsers;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public List<Message> getMessagesByUser(String username) throws DAOException {
        try {
            List<Message> messagesByUser;
            String sql = "select * from message where sender = '" + username + "' or receiver = '" + username + "' ;";

            messagesByUser = namedParameterJdbcTemplate.query(sql, new RowMapper<Message>() {
                @Override
                public Message mapRow(ResultSet resultSet, int i) throws SQLException {
                    Message message = new Message();
                    message.setSenderUsername(resultSet.getString("sender"));
                    message.setReceiverUsername(resultSet.getString("receiver"));
                    message.setTimestamp(resultSet.getTimestamp("timestamp"));
                    message.setMessage(resultSet.getString("message"));

                    return message;
                }
            });
            return messagesByUser;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public void delete(String username) throws DAOException {
        try {
            String sql = "delete from message where sender = :username;";
            Map<String, String> namedParameters = Collections.singletonMap("username", username);

            namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
}