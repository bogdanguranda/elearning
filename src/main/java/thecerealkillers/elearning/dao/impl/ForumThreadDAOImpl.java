package thecerealkillers.elearning.dao.impl;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.ForumThreadDAO;
import thecerealkillers.elearning.model.ForumThread;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.*;


/**
 * Created by Dani
 */
@Repository
public class ForumThreadDAOImpl implements ForumThreadDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void add(ForumThread newForumThread, String topicTitle) throws DAOException {
        try {
            String command = "INSERT INTO thread VALUES (:threadTitle, :owner)";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("threadTitle", newForumThread.getTitle());
            namedParameters.put("owner", newForumThread.getOwner());

            namedParameterJdbcTemplate.update(command, namedParameters);

            addThreadToTopic(newForumThread.getTitle(), topicTitle);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public List<ForumThread> getAll() throws DAOException {
        try {
            String command = "SELECT * FROM thread";
            List<ForumThread> topicList;

            topicList = namedParameterJdbcTemplate.query(command, new RowMapper<ForumThread>() {
                @Override
                public ForumThread mapRow(ResultSet resultSet, int i) throws SQLException {
                    ForumThread thread = new ForumThread();

                    thread.setTitle(resultSet.getString("title"));
                    thread.setOwner(resultSet.getString("owner"));

                    return thread;
                }
            });

            if (topicList.size() == 0)
                throw new DAOException("No threads in the database");

            return topicList;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public List<ForumThread> getThreadsOwnedByUser(String userName) throws DAOException {
        try {
            Map<String, String> namedParameters = Collections.singletonMap("owner", userName);
            String command = "SELECT * FROM thread WHERE owner = :owner";

            List<ForumThread> threadList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<ForumThread>() {
                @Override
                public ForumThread mapRow(ResultSet resultSet, int i) throws SQLException {
                    ForumThread thread = new ForumThread();

                    thread.setOwner(resultSet.getString("owner"));
                    thread.setTitle(resultSet.getString("title"));

                    return thread;
                }
            });

            if (threadList.size() == 0)
                throw new DAOException("No threads owned by :  " + userName);

            return threadList;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public ForumThread getThreadByTitle(String threadTitle) throws DAOException {
        try {
            Map<String, String> namedParameters = Collections.singletonMap("title", threadTitle);
            String command = "SELECT * FROM thread WHERE title = :title";

            List<ForumThread> threadList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<ForumThread>() {
                @Override
                public ForumThread mapRow(ResultSet resultSet, int i) throws SQLException {
                    ForumThread thread = new ForumThread();

                    thread.setTitle(resultSet.getString("title"));
                    thread.setOwner(resultSet.getString("owner"));

                    return thread;
                }
            });

            if (threadList.size() == 0)
                throw new DAOException("No thread with title :  " + threadTitle);

            return threadList.get(0);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public List<ForumThread> getThreadsForTopic(String topic) throws DAOException {
        try {
            Map<String, String> namedParameters = Collections.singletonMap("topic", topic);
            String command = "SELECT thread FROM topic_thread WHERE topic = :topic";

            List<String> threadTitleList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("thread");
                }
            });

            List<ForumThread> threadList = new ArrayList<>();

            for (String title : threadTitleList) {
                threadList.add(getThreadByTitle(title));
            }

            if (threadList.size() == 0)
                throw new DAOException("No threads with topic :  " + topic);

            return threadList;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void updateThread(String oldTitle, ForumThread newThread) throws DAOException {
        try {
            String command = "UPDATE thread SET title = :newTitle, owner = :newOwner WHERE title = :oldTitle";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("oldTitle", oldTitle);
            namedParameters.put("newTitle", newThread.getTitle());
            namedParameters.put("newOwner", newThread.getOwner());

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void deleteThreadByTitle(String threadToDelete) throws DAOException {
        try {
            String command = "DELETE FROM thread WHERE title = :title";
            Map<String, String> namedParameters = Collections.singletonMap("title", threadToDelete);

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    private void addThreadToTopic(String threadTitle, String topicTitle) throws DAOException {
        try {
            String command = "INSERT INTO topic_thread VALUES (:topicTitle, :threadTitle)";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("topicTitle", topicTitle);
            namedParameters.put("threadTitle", threadTitle);

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
