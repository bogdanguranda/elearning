package thecerealkillers.elearning.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.MessagesDAO;
import thecerealkillers.elearning.model.Message;


import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    public void add(Message message) {
        String sql = "insert into message values (DEFAULT, :sender, :receiver, DEFAULT, :message);";
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("sender", message.getSenderUsername());
        namedParameters.put("receiver", message.getReceiverUsername());
        namedParameters.put("message", message.getMessage());

        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public List<Message> getAll() {
        List<Message> messageList;
        String sql = "select * from message;";

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
    }

    @Override
    public List<Message> getMessagesBetweenUsers(String sender, String receiver) {
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
                message.setTimestamp(null);
                message.setMessage(resultSet.getString("message"));

                return message;
            }
        });

        return messagesBetweenUsers;
    }

    @Override
    public List<Message> getMessagesByUser(String username) {
        List<Message> messagesByUser;
        String sql = "select * from message where sender = '" + username + "' or receiver = '" + username + "' ;";

        messagesByUser = namedParameterJdbcTemplate.query(sql, new RowMapper<Message>() {
            @Override
            public Message mapRow(ResultSet resultSet, int i) throws SQLException {
                Message message = new Message();
                message.setSenderUsername(resultSet.getString("sender"));
                message.setReceiverUsername(resultSet.getString("receiver"));
                message.setMessage(resultSet.getString("message"));
                message.setTimestamp(null);

                return message;
            }
        });
        return messagesByUser;
    }

    @Override
    public void delete(String username) {
        String sql = "delete from message where sender = :username;";
        Map<String, String> namedParameters = Collections.singletonMap("username", username);

        namedParameterJdbcTemplate.update(sql, namedParameters);
    }
}