package thecerealkillers.elearning.service;

import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Topic;

import java.util.List;


public interface TopicService {

    /**
     * Adds a topic in the database.
     *
     * @param newTopic
     * @throws ServiceException
     */
    void add(Topic newTopic) throws ServiceException;

    /**
     * Retrieves a topic by title.
     *
     * @param title
     * @return The topic with title @title
     * @throws ServiceException
     */
    Topic get(String title) throws ServiceException;

    /**
     * Returns all the topics in the database.
     *
     * @return all topics that are stored in the database.
     * @throws ServiceException
     */
    List<Topic> getAll() throws ServiceException;

    /**
     * Updates the data for a topic by title.
     *
     * @param title
     * @param newTopicData
     * @throws ServiceException
     */
    void update(String title, Topic newTopicData) throws ServiceException;

    /**
     * Deletes a topic by title.
     *
     * @param title
     * @throws ServiceException
     */
    void delete(String title) throws ServiceException;
}
