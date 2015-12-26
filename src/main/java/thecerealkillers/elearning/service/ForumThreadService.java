package thecerealkillers.elearning.service;

import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.ForumThread;
import thecerealkillers.elearning.model.ForumThreadIdentifier;

import java.util.List;


/**
 * Created by Dani
 */
public interface ForumThreadService {

    /**
     * Adds a new thread in the database.
     *
     * @throws ServiceException
     * @throws NotFoundException
     */
    void add(ForumThread newForumThread) throws ServiceException, NotFoundException;


    /**
     * Retrieves a thread by it's title
     *
     * @return thread with title @threadTitle
     * @throws ServiceException
     * @throws NotFoundException
     */
    ForumThread getThread(String threadTitle, String topic) throws ServiceException, NotFoundException;


    /**
     * Retrieves all the thread that are in a topic
     *
     * @return thread with title @topic
     */
    List<ForumThread> getThreadsInTopic(String topic) throws ServiceException, NotFoundException;


    /**
     * Retrieves all the threads from the database.
     *
     * @return list with all threads stored in the database
     */
    List<ForumThread> getAll() throws ServiceException, NotFoundException;


    /**
     * Retrieves all the threads owned by a user
     *
     * @return list with all threads owned by the user with username @userName
     * @throws ServiceException
     * @throws NotFoundException
     */
    List<ForumThread> getThreadsOwnedByUser(String userName) throws ServiceException, NotFoundException;


    /**
     * Updates a thread
     *
     * @throws ServiceException
     * @throws NotFoundException
     */
    void updateThread(String newTitle, ForumThread threadToUpdate) throws ServiceException, NotFoundException;


    /**
     * Deletes a thread
     *
     * @throws ServiceException
     * @throws NotFoundException
     */
    void deleteThread(ForumThreadIdentifier threadToDelete) throws ServiceException, NotFoundException;

    /**
     * Returns true if thread exists, false otherwise.
     *
     * @throws ServiceException
     */
    Boolean exists(String title, String topic) throws ServiceException;
}
