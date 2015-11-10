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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lucian on 10.11.2015.
 */

@Repository
public class MessageDAOImpl implements MessagesDAO{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource (DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void add(Message message) {
        String sql = "insert into message values (:sender, :receiver, :timestamp, :message);";
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("sender", message.getSenderUsername());
        namedParameters.put("receiver", message.getReceiverUsername());
        namedParameters.put("timestamp", message.getTimestamp().toString());
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
}
