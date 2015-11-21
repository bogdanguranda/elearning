package thecerealkillers.elearning.dao;

import thecerealkillers.elearning.model.ForumThread;

import java.util.List;

public interface ForumThreadDAO {
    /**
     * Adds a new thread in the database.
     *
     * @param newForumThread
     * @param topicTitle
     */
    void add(ForumThread newForumThread, String topicTitle);

    /**
     * Retrieves all the threads from the database.
     *
     * @return list with all threads stored in the database
     */
    List<ForumThread> getAll();

    /**
     * Retrieves all the threads owned by a user
     *
     * @param userName
     * @return list with all threads owned by the user with username @userName
     */
    List<ForumThread> getThreadsOwnedByUser(String userName);

    /**
     * Retrieves a thread by it's title
     *
     * @param threadTitle
     * @return thread with title @threadTitle
     */
    ForumThread getThreadByTitle(String threadTitle);

    /**
     * Retrieves all the thread that are in a topic
     *
     * @param topic
     * @return thread with title @topic
     */
    List<ForumThread> getThreadsForTopic(String topic);

    /**
     * Updates a thread
     *
     * @param oldTitle
     * @param newThread
     */
    void updateThread(String oldTitle, ForumThread newThread);

    /**
     * Deletes a thread
     *
     * @param threadToDelete
     */
    void deleteThreadByTitle(String threadToDelete);
}
