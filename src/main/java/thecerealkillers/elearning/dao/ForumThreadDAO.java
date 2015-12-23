package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.ForumThread;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Dani
 */
@Repository
public interface ForumThreadDAO {

    /**
     * Adds a new thread in the database.
     *
     * @param newForumThread
     * @param topicTitle
     * @throws DAOException
     */
    void add(ForumThread newForumThread, String topicTitle) throws DAOException;

    /**
     * Retrieves all the threads from the database.
     *
     * @return list with all threads stored in the database
     * @throws DAOException
     */
    List<ForumThread> getAll() throws DAOException;

    /**
     * Retrieves all the threads owned by a user
     *
     * @param userName
     * @return list with all threads owned by the user with username @userName
     * @throws DAOException
     */
    List<ForumThread> getThreadsOwnedByUser(String userName) throws DAOException;

    /**
     * Retrieves a thread by it's title
     *
     * @param threadTitle
     * @return thread with title @threadTitle
     * @throws DAOException
     */
    ForumThread getThreadByTitle(String threadTitle) throws DAOException;

    /**
     * Retrieves all the thread that are in a topic
     *
     * @param topic
     * @return thread with title @topic
     * @throws DAOException
     */
    List<ForumThread> getThreadsForTopic(String topic) throws DAOException;

    /**
     * Updates a thread
     *
     * @param oldTitle
     * @param newThread
     * @throws DAOException
     */
    void updateThread(String oldTitle, ForumThread newThread) throws DAOException;

    /**
     * Deletes a thread
     *
     * @param threadToDelete
     * @throws DAOException
     */
    void deleteThreadByTitle(String threadToDelete) throws DAOException;
}
