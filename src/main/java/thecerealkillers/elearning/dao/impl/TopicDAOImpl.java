package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.TopicDAO;
import thecerealkillers.elearning.model.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TopicDAOImpl implements TopicDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void add(Topic newTopic) {
        String command = "INSERT INTO topic VALUES (:title)";

        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("title", newTopic.getTitle());

        namedParameterJdbcTemplate.update(command, namedParameters);
    }

    @Override
    public Topic get(String title) {
        Map<String, String> namedParameters = Collections.singletonMap("title", title);
        String command = "SELECT * FROM topic WHERE title = :title";

        List<Topic> topicList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<Topic>() {
            @Override
            public Topic mapRow(ResultSet resultSet, int i) throws SQLException {
                Topic topic = new Topic();

                topic.setTitle(resultSet.getString("title"));

                return topic;
            }
        });

        return topicList.get(0);
    }

    @Override
    public List<Topic> getAll() {
        String command = "SELECT * FROM topic";
        List<Topic> topicList;

        topicList = namedParameterJdbcTemplate.query(command, new RowMapper<Topic>() {
            @Override
            public Topic mapRow(ResultSet resultSet, int i) throws SQLException {
                Topic topic = new Topic();

                topic.setTitle(resultSet.getString("title"));

                return topic;
            }
        });

        return topicList;
    }

    @Override
    public void update(String title, Topic newTopicData) {
        String command = "UPDATE topic SET title = :newTitle WHERE title = :title";

        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("newTitle", newTopicData.getTitle());
        namedParameters.put("title", title);

        namedParameterJdbcTemplate.update(command, namedParameters);
    }

    @Override
    public void delete(String title) {
        String command = "DELETE FROM topic WHERE title = :title";
        Map<String, String> namedParameters = Collections.singletonMap("title", title);

        namedParameterJdbcTemplate.update(command, namedParameters);
    }
}
