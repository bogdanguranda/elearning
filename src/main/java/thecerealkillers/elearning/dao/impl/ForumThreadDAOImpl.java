package thecerealkillers.elearning.dao.impl;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.ForumThreadDAO;
import thecerealkillers.elearning.model.ForumThread;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.apache.tomcat.jdbc.pool.DataSource;
import thecerealkillers.elearning.model.ForumThreadIdentifier;

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
    public void add(ForumThread newForumThread) throws DAOException {
        try {
            String command = "INSERT INTO thread (`title`, `topic`, `owner`) VALUES (:title, :topic, :owner);";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", newForumThread.getTitle());
            namedParameters.put("topic", newForumThread.getTopic());
            namedParameters.put("owner", newForumThread.getOwner());

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public List<ForumThread> getAll() throws DAOException, NotFoundException {
        try {
            String command = "SELECT * FROM thread";
            List<ForumThread> threadList;

            threadList = namedParameterJdbcTemplate.query(command, new RowMapper<ForumThread>() {
                @Override
                public ForumThread mapRow(ResultSet resultSet, int i) throws SQLException {
                    ForumThread thread = new ForumThread();

                    thread.setTitle(resultSet.getString("title"));
                    thread.setTopic(resultSet.getString("topic"));
                    thread.setOwner(resultSet.getString("owner"));

                    return thread;
                }
            });

            if (threadList.size() == 0)
                throw new NotFoundException(NotFoundException.NO_THREADS);

            return threadList;
        } catch (NotFoundException notFound){
            throw notFound;

        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public List<ForumThread> getThreadsOwnedByUser(String userName) throws DAOException, NotFoundException {
        try {
            Map<String, String> namedParameters = Collections.singletonMap("owner", userName);
            String command = "SELECT * FROM thread WHERE owner = :owner";

            List<ForumThread> threadList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<ForumThread>() {
                @Override
                public ForumThread mapRow(ResultSet resultSet, int i) throws SQLException {
                    ForumThread thread = new ForumThread();

                    thread.setTopic(resultSet.getString("topic"));
                    thread.setTitle(resultSet.getString("title"));
                    thread.setOwner(resultSet.getString("owner"));

                    return thread;
                }
            });

            if (threadList.size() == 0)
                throw new NotFoundException(NotFoundException.NO_THREADS_BY_OWNER);

            return threadList;
        } catch (NotFoundException notFound){
            throw notFound;

        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public ForumThread getThread(String threadTitle, String topicTitle) throws DAOException, NotFoundException {
        try {
            String command = "SELECT * FROM thread WHERE title = :title AND topic = :topic;";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("title", threadTitle);
            namedParameters.put("topic", topicTitle);

            List<ForumThread> threadList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<ForumThread>() {
                @Override
                public ForumThread mapRow(ResultSet resultSet, int i) throws SQLException {
                    ForumThread thread = new ForumThread();

                    thread.setTitle(resultSet.getString("title"));
                    thread.setOwner(resultSet.getString("owner"));
                    thread.setTopic(resultSet.getString("topic"));

                    return thread;
                }
            });

            if (threadList.size() == 0)
                throw new NotFoundException(NotFoundException.NO_THREAD);

            return threadList.get(0);
        } catch (NotFoundException notFound){
            throw notFound;

        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public List<ForumThread> getThreadsInTopic(String topic) throws DAOException, NotFoundException {
        try {
            Map<String, String> namedParameters = Collections.singletonMap("topic", topic);
            String command = "SELECT * FROM thread WHERE topic = :topic";

            List<ForumThread> threadList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<ForumThread>() {
                @Override
                public ForumThread mapRow(ResultSet resultSet, int i) throws SQLException {
                    ForumThread thread = new ForumThread();

                    thread.setOwner(resultSet.getString("owner"));
                    thread.setTitle(resultSet.getString("title"));
                    thread.setTopic(resultSet.getString("topic"));

                    return thread;
                }
            });

            return threadList;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void updateThread(String newTitle, ForumThread thread) throws DAOException {
        try {
            String command = "UPDATE thread SET title = :newTitle WHERE title = :oldTitle AND topic = :topic";

            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("oldTitle", thread.getTitle());
            namedParameters.put("newTitle", newTitle);
            namedParameters.put("topic", thread.getTopic());

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void deleteThread(ForumThreadIdentifier threadToDelete) throws DAOException {
        try {
            String command = "DELETE FROM thread WHERE title = :title AND topic = :topic";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("title", threadToDelete.getTitle());
            namedParameters.put("topic", threadToDelete.getTopic());

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
