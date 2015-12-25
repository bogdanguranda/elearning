package thecerealkillers.elearning.service;

import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.ForumThread;

import java.util.List;


/**
 * Created by Dani
 */
public interface ForumThreadService {

    /**
     * Adds a new thread in the database.
     *
     * @param newForumThread
     * @param topicTitle
     */
    void add(ForumThread newForumThread, String topicTitle) throws ServiceException, NotFoundException;

    /**
     * Retrieves all the threads from the database.
     *
     * @return list with all threads stored in the database
     */
    List<ForumThread> getAll() throws ServiceException, NotFoundException;

    /**
     * Retrieves all the threads owned by a user
     *
     * @param userName
     * @return list with all threads owned by the user with username @userName
     */
    List<ForumThread> getThreadsOwnedByUser(String userName) throws ServiceException, NotFoundException;

    /**
     * Retrieves a thread by it's title
     *
     * @param threadTitle
     * @return thread with title @threadTitle
     */
    ForumThread getThreadByTitle(String threadTitle) throws ServiceException, NotFoundException;

    /**
     * Retrieves all the thread that are in a topic
     *
     * @param topic
     * @return thread with title @topic
     */
    List<ForumThread> getThreadsForTopic(String topic) throws ServiceException, NotFoundException;

    /**
     * Updates a thread
     *
     * @param oldTitle
     * @param newThread
     */
    void updateThread(String oldTitle, ForumThread newThread) throws ServiceException, NotFoundException;

    /**
     * Deletes a thread
     *
     * @param threadToDelete
     */
    void deleteThreadByTitle(String threadToDelete) throws ServiceException, NotFoundException;

    Boolean exists(String title) throws ServiceException;
}
