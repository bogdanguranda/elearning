package thecerealkillers.elearning.dao.impl;

import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.CommentDAO;
import thecerealkillers.elearning.model.Comment;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.text.SimpleDateFormat;
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
    public void addComment(String owner, String message, String threadTitle) throws DAOException {
        try {
            String command = "INSERT INTO elearning_db.comment VALUES (:owner, DEFAULT, :message)";

            Date timeStamp = new Date(Calendar.getInstance().getTimeInMillis());
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeStampStr = sdf.format(timeStamp);

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("owner", owner);
            namedParameters.put("message", message);

            namedParameterJdbcTemplate.update(command, namedParameters);

            addCommentToThread(owner, timeStampStr, threadTitle);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public Comment getCommentByOwnerAndTimeStamp(String owner, Date timeStamp) throws DAOException {
        try {
            String command = "SELECT * FROM elearning_db.comment WHERE owner = :owner AND timeStamp = :timeStamp";
            Map<String, String> namedParameters = new HashMap<>();

            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            namedParameters.put("owner", owner);
            namedParameters.put("timeStamp", sdf.format(timeStamp));

            List<Comment> commentList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<Comment>() {
                @Override
                public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
                    Comment comment = new Comment();

                    comment.setOwner(resultSet.getString("owner"));
                    comment.setTimeStamp(resultSet.getTimestamp("timeStamp"));
                    comment.setMessage(resultSet.getString("message"));

                    return comment;
                }
            });

            if (commentList.size() == 0)
                throw new DAOException("No comments owned by :  " + owner + "posted in :  " + sdf.format(timeStamp));

            return commentList.get(0);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public List<Comment> getCommentsForThread(String threadTitle) throws DAOException {
        try {
            Map<String, String> namedParameters = Collections.singletonMap("threadTitle", threadTitle);

            String firstCondition = "SELECT thread_comment.commentOwner FROM thread_comment WHERE thread = :threadTitle";
            String secondCondition = "SELECT thread_comment.timestamp AS timp FROM thread_comment WHERE thread = :threadTitle";
            String command = "SELECT * FROM comment AS B WHERE owner IN (" + firstCondition + ") AND timeStamp IN (" + secondCondition + ")";

            List<Comment> commentList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<Comment>() {
                @Override
                public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
                    Comment comment = new Comment();

                    comment.setOwner(resultSet.getString("owner"));
                    comment.setTimeStamp(resultSet.getTimestamp("timeStamp"));
                    comment.setMessage(resultSet.getString("message"));

                    return comment;
                }
            });

            if (commentList.size() == 0)
                throw new DAOException("No comments posted in thread :  " + threadTitle);

            return commentList;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void updateComment(String owner, Date timeStamp, String newMessage) throws DAOException {
        try {
            String command = "UPDATE comment SET message = :message WHERE owner = :owner AND timeStamp = :timeStamp";
            Map<String, String> namedParameters = new HashMap<>();

            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            namedParameters.put("owner", owner);
            namedParameters.put("timeStamp", sdf.format(timeStamp));
            namedParameters.put("message", newMessage);

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void deleteComment(String owner, Date timeStamp) throws DAOException {
        try {
            String command = "DELETE FROM comment WHERE owner = :owner AND timeStamp = :timeStamp";
            Map<String, String> namedParameters = new HashMap<>();

            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            namedParameters.put("owner", owner);
            namedParameters.put("timeStamp", sdf.format(timeStamp));

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }


    /// Additional required methods
    private void addCommentToThread(String owner, String timeStamp, String threadTitle) throws DAOException {
        try {
            String command = "INSERT INTO thread_comment VALUES (:thread, :commentOwner, :timeStamp)";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("thread", threadTitle);
            namedParameters.put("commentOwner", owner);
            namedParameters.put("timeStamp", timeStamp);

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
