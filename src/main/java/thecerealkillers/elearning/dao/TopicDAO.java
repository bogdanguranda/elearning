package thecerealkillers.elearning.dao;

import thecerealkillers.elearning.model.Topic;

import java.util.List;

public interface TopicDAO {
    /**
     * Adds a topic in the database.
     *
     * @param newTopic
     */
    void add(Topic newTopic);

    /**
     * Retrieves a topic by title.
     *
     * @param title
     * @return The topic with title @title
     */
    Topic get(String title);

    /**
     * Returns all the topics in the database.
     *
     * @return all topics that are stored in the database.
     */
    List<Topic> getAll();

    /**
     * Updates the data for a topic by title.
     *
     * @param title
     * @param newTopicData
     */
    void update(String title, Topic newTopicData);

    /**
     * Deletes a topic by title.
     *
     * @param title
     */
    void delete(String title);
}
