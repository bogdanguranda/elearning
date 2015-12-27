package thecerealkillers.elearning.dao.impl;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.CommentDAO;
import thecerealkillers.elearning.model.Comment;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.util.*;

import java.sql.SQLException;
import java.sql.ResultSet;


/**
 * Created by Dani
 */
@Repository
public class CommentDAOImpl implements CommentDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void addComment(Comment comment) throws DAOException {
        try {
            String command = "INSERT INTO comment (`owner`, `topic`, `thread`, `message`, `timestamp`)  VALUES (:owner, :topic, :thread, :message, DEFAULT);";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("owner", comment.getOwner());
            namedParameters.put("topic", comment.getTopic());
            namedParameters.put("thread", comment.getThread());
            namedParameters.put("message", comment.getMessage());

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public Comment getComment(Integer commentID) throws DAOException, NotFoundException {
        try {
            String command = "SELECT * FROM comment WHERE id = :id;";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("id", commentID.toString());

            List<Comment> commentList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<Comment>() {
                @Override
                public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
                    Comment comment = new Comment();

                    comment.setId(resultSet.getInt("id"));
                    comment.setOwner(resultSet.getString("owner"));
                    comment.setTopic(resultSet.getString("topic"));
                    comment.setThread(resultSet.getString("thread"));
                    comment.setMessage(resultSet.getString("message"));
                    comment.setTimeStamp(resultSet.getTimestamp("timeStamp"));

                    return comment;
                }
            });

            if (commentList.size() == 0)
                throw new NotFoundException(NotFoundException.NO_COMMENT);

            return commentList.get(0);
        } catch (NotFoundException notFound) {
            throw notFound;

        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public List<Comment> getCommentsInThread(String threadTitle, String topicTitle) throws DAOException, NotFoundException {
        try {
            String command = "SELECT * FROM comment WHERE topic = :topic AND thread = :thread ORDER BY timestamp;";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("thread", threadTitle);
            namedParameters.put("topic", topicTitle);

            List<Comment> commentList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<Comment>() {
                @Override
                public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
                    Comment comment = new Comment();

                    comment.setTimeStamp(resultSet.getTimestamp("timeStamp"));
                    comment.setMessage(resultSet.getString("message"));
                    comment.setThread(resultSet.getString("thread"));
                    comment.setOwner(resultSet.getString("owner"));
                    comment.setTopic(resultSet.getString("topic"));
                    comment.setId(resultSet.getInt("id"));

                    return comment;
                }
            });

            if (commentList.size() == 0)
                throw new NotFoundException(NotFoundException.NO_THREAD);

            return commentList;
        } catch (NotFoundException notFound) {
            throw notFound;

        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void updateComment(Integer commentID, String newMessage) throws DAOException {
        try {
            String command = "UPDATE comment SET message = :message WHERE  id = :commentID;";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("commentID", commentID.toString());
            namedParameters.put("message", newMessage);

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void deleteComment(Integer commentID) throws DAOException {
        try {
            String command = "DELETE FROM comment WHERE id = :commentID;";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("commentID", commentID.toString());

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
