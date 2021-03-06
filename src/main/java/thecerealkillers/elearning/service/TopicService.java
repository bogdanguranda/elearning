package thecerealkillers.elearning.service;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Topic;

import java.util.List;


/**
 * Created by Dani
 */
public interface TopicService {

    /**
     * Adds a topic in the database.
     *
     * @throws ServiceException
     */
    void add(Topic newTopic) throws ServiceException;

    /**
     * Retrieves a topic by title.
     *
     * @return The topic with title @title
     * @throws ServiceException
     */
    Topic get(String title) throws ServiceException, NotFoundException;

    /**
     * Returns all the topics in the database.
     *
     * @return all topics that are stored in the database.
     * @throws ServiceException
     */
    List<Topic> getAll() throws ServiceException, NotFoundException;

    /**
     * Updates the data for a topic by title.
     *
     * @throws ServiceException
     */
    void update(String title, Topic newTopicData) throws ServiceException, NotFoundException;

    /**
     * Deletes a topic by title.
     *
     * @throws ServiceException
     */
    void delete(String title) throws ServiceException, NotFoundException;

    /**
     * Checks if a topic with the title = @title exists in the database.
     *
     * @throws ServiceException
     */
    Boolean exists(String title) throws ServiceException;
}
