package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.ForumThreadDAO;
import thecerealkillers.elearning.model.ForumThread;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class ForumThreadDAOImpl implements ForumThreadDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void add(ForumThread newForumThread, String topicTitle) {
        String command = "INSERT INTO thread VALUES (:threadTitle, :owner)";

        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("threadTitle", newForumThread.getTitle());
        namedParameters.put("owner", newForumThread.getOwner());

        namedParameterJdbcTemplate.update(command, namedParameters);

        addThreadToTopic(newForumThread.getTitle(), topicTitle);
    }

    @Override
    public List<ForumThread> getAll() {
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

        return topicList;
    }

    @Override
    public List<ForumThread> getThreadsOwnedByUser(String userName) {
        Map<String, String> namedParameters = Collections.singletonMap("owner", userName);
        String command = "SELECT * FROM thread WHERE owner = :owner";

        List<ForumThread> threadList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<ForumThread>() {
            @Override
            public ForumThread mapRow(ResultSet resultSet, int i) throws SQLException {
                ForumThread thread = new ForumThread();

                thread.setTitle(resultSet.getString("title"));
                thread.setOwner(resultSet.getString("owner"));

                return thread;
            }
        });

        return threadList;
    }

    @Override
    public ForumThread getThreadByTitle(String threadTitle) {
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

        return threadList.get(0);
    }

    @Override
    public List<ForumThread> getThreadsForTopic(String topic) {
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

        return threadList;
    }

    @Override
    public void updateThread(String oldTitle, ForumThread newThread) {
        String command = "UPDATE thread SET title = :newTitle, owner = :newOwner WHERE title = :oldTitle";

        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("oldTitle", oldTitle);
        namedParameters.put("newTitle", newThread.getTitle());
        namedParameters.put("newOwner", newThread.getOwner());

        namedParameterJdbcTemplate.update(command, namedParameters);
    }

    @Override
    public void deleteThreadByTitle(String threadToDelete) {
        String command = "DELETE FROM thread WHERE title = :title";
        Map<String, String> namedParameters = Collections.singletonMap("title", threadToDelete);

        namedParameterJdbcTemplate.update(command, namedParameters);
    }

    private void addThreadToTopic(String threadTitle, String topicTitle) {
        String command = "INSERT INTO topic_thread VALUES (:topicTitle, :threadTitle)";

        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("topicTitle", topicTitle);
        namedParameters.put("threadTitle", threadTitle);

        namedParameterJdbcTemplate.update(command, namedParameters);
    }
}
