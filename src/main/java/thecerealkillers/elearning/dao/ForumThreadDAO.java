package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.model.ForumThread;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.model.ForumThreadIdentifier;

import java.util.List;


/**
 * Created by Dani
 */
@Repository
public interface ForumThreadDAO {

    /**
     * Adds a new thread in the database.
     *
     * @throws DAOException
     */
    void add(ForumThread newForumThread) throws DAOException;


    /**
     * Retrieves all the threads from the database.
     *
     * @return list with all threads stored in the database
     * @throws DAOException
     */
    List<ForumThread> getAll() throws DAOException, NotFoundException;

    /**
     * Retrieves all the threads owned by a user
     *
     * @return list with all threads owned by the user with username @userName
     * @throws DAOException
     */
    List<ForumThread> getThreadsOwnedByUser(String userName) throws DAOException, NotFoundException;

    /**
     * Retrieves a thread by it's title
     *
     * @return thread with title @threadTitle
     * @throws DAOException
     */
    ForumThread getThread(String threadTitle, String topicTitle) throws DAOException, NotFoundException;


    /**
     * Retrieves all the thread that are in a topic
     *
     * @return thread with title @topic
     * @throws DAOException
     */
    List<ForumThread> getThreadsInTopic(String topic) throws DAOException, NotFoundException;

    /**
     * Updates a thread
     *
     * @throws DAOException
     */
    void updateThread(String oldTitle, ForumThread newThread) throws DAOException;

    /**
     * Deletes a thread
     *
     * @throws DAOException
     */
    void deleteThread(ForumThreadIdentifier threadToDelete) throws DAOException;
}
