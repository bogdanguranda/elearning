package thecerealkillers.elearning.dao.impl;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.TopicDAO;
import thecerealkillers.elearning.model.Topic;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Dani
 */
@Repository
public class TopicDAOImpl implements TopicDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void add(Topic newTopic) throws DAOException {
        try {
            String command = "INSERT INTO topic VALUES (:title)";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", newTopic.getTitle());

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public Topic get(String title) throws DAOException, NotFoundException {
        try {
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

            if (topicList.size() == 0)
                throw new NotFoundException(NotFoundException.NO_TOPIC);

            return topicList.get(0);
        } catch (NotFoundException notFound){
            throw notFound;

        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }

    }

    @Override
    public List<Topic> getAll() throws DAOException, NotFoundException {
        try {
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

            if (topicList.size() == 0)
                throw new NotFoundException(NotFoundException.NO_TOPICS);

            return topicList;
        } catch (NotFoundException notFound){
            throw notFound;

        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void update(String title, Topic newTopicData) throws DAOException {
        try {
            String command = "UPDATE topic SET title = :newTitle WHERE title = :title";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("newTitle", newTopicData.getTitle());
            namedParameters.put("title", title);

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void delete(String title) throws DAOException {
        try {
            String command = "DELETE FROM topic WHERE title = :title";
            Map<String, String> namedParameters = Collections.singletonMap("title", title);

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
