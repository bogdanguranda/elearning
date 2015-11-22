package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.CommentDAO;
import thecerealkillers.elearning.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class CommentDAOImpl implements CommentDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void addComment(String owner, String message, String threadTitle) {
        String command = "INSERT INTO comment VALUES (:owner, DEFAULT, :message)";

        Date timeStamp = new Date();
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStampStr = sdf.format(timeStamp);

        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("owner", owner);
        namedParameters.put("message", message);


        namedParameterJdbcTemplate.update(command, namedParameters);

        addCommentToThread(owner, timeStampStr, threadTitle);
    }

    private void addCommentToThread(String owner, String timeStamp, String threadTitle) {
        String command = "INSERT INTO thread_comment VALUES (:thread, :commentOwner, :timeStamp)";
        Map<String, String> namedParameters = new HashMap<>();

        namedParameters.put("thread", threadTitle);
        namedParameters.put("commentOwner", owner);
        namedParameters.put("timeStamp", timeStamp.toString());

        namedParameterJdbcTemplate.update(command, namedParameters);
    }

    @Override
    public Comment getCommentByOwnerAndTimeStamp(String owner, Date timeStamp) {
        String command = "SELECT * FROM comment WHERE owner = :owner AND timeStamp = :timeStamp";
        Map<String, String> namedParameters = new HashMap<>();

        namedParameters.put("owner", owner);
        namedParameters.put("timeStamp", timeStamp.toString());

        List<Comment> commentList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<Comment>() {
            @Override
            public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
                Comment comment = new Comment();


                comment.setOwner(resultSet.getString("owner"));
                comment.setTimeStamp(resultSet.getDate("timeStamp"));
                comment.setMeessage(resultSet.getString("message"));

                System.out.print(comment.getMeessage());
                return comment;
            }
        });

        return commentList.get(0);
    }

    @Override
    public List<Comment> getCommentsForThread(String threadTitle) {
        Map<String, String> namedParameters = Collections.singletonMap("threadTitle", threadTitle);
        String command = "SELECT * FROM comment AS B WHERE owner IN (SELECT thread_comment.commentOwner FROM thread_comment WHERE thread = :threadTitle) AND timeStamp = (SELECT thread_comment.timestamp AS timp FROM thread_comment WHERE thread = :threadTitle)";

        List<Comment> commentList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<Comment>() {
            @Override
            public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
                Comment comment = new Comment();

                comment.setOwner(resultSet.getString("owner"));
                comment.setTimeStamp(resultSet.getDate("timeStamp"));
                comment.setMeessage(resultSet.getString("message"));

                return comment;
            }
        });

        return commentList;
    }

    @Override
    public void updateComment(String owner, Date timeStamp, String newMessage) {
        String command = "UPDATE comment SET newMessage = :newMessage WHERE owner = :owner AND timeStamp = :timeStamp";
        Map<String, String> namedParameters = new HashMap<>();

        namedParameters.put("owner", owner);
        namedParameters.put("timeStamp", timeStamp.toString());
        namedParameters.put("message", newMessage);

        namedParameterJdbcTemplate.update(command, namedParameters);
    }

    @Override
    public void deleteComment(String owner, Date timeStamp) {
        String command = "DELETE FROM comment WHERE owner = :owner AND timeStamp = :timeStamp";
        Map<String, String> namedParameters = new HashMap<>();

        namedParameters.put("owner", owner);
        namedParameters.put("timeStamp", timeStamp.toString());

        namedParameterJdbcTemplate.update(command, namedParameters);
    }
}
