package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.model.Topic;

import java.util.List;


/**
 * Created by Dani
 */
@Repository
public interface TopicDAO {

    /**
     * Adds a topic in the database.
     *
     * @param newTopic
     * @throws DAOException
     */
    void add(Topic newTopic) throws DAOException;

    /**
     * Retrieves a topic by title.
     *
     * @param title
     * @return The topic with title @title
     * @throws DAOException
     */
    Topic get(String title) throws DAOException;

    /**
     * Returns all the topics in the database.
     *
     * @return all topics that are stored in the database.
     * @throws DAOException
     */
    List<Topic> getAll() throws DAOException;

    /**
     * Updates the data for a topic by title.
     *
     * @param title
     * @param newTopicData
     * @throws DAOException
     */
    void update(String title, Topic newTopicData) throws DAOException;

    /**
     * Deletes a topic by title.
     *
     * @param title
     * @throws DAOException
     */
    void delete(String title) throws DAOException;
}
