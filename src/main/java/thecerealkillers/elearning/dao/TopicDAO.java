package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Topic;

import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Dani
 */
@Repository
public interface TopicDAO {

    /**
     * Adds a topic in the database.
     *
     * @throws DAOException
     */
    void add(Topic newTopic) throws DAOException;

    /**
     * Retrieves a topic by title.
     *
     * @return The topic with title @title
     * @throws DAOException, NotFoundException
     */
    Topic get(String title) throws DAOException, NotFoundException;

    /**
     * Returns all the topics in the database.
     *
     * @return all topics that are stored in the database.
     * @throws DAOException, NotFoundException
     */
    List<Topic> getAll() throws DAOException, NotFoundException;

    /**
     * Updates the data for a topic by title.
     *
     * @throws DAOException
     */
    void update(String title, Topic newTopicData) throws DAOException;

    /**
     * Deletes a topic by title.
     *
     * @throws DAOException
     */
    void delete(String title) throws DAOException;
}
