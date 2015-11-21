package thecerealkillers.elearning.service;

import thecerealkillers.elearning.model.ForumThread;

import java.util.List;

public interface ForumThreadService {
    /**
     * Adds a new thread in the database.
     * @param newForumThread
     */
    void add(ForumThread newForumThread);

    /**
     * Retrieves all the threads owned by a user
     * @param userName
     * @return list with all threads owned by the user with username @userName
     */
    List<ForumThread> getThreadsOwnedByUser (String userName);

    /**
     * Retrieves a thread by it's title
     * @param threadTitle
     * @return thread with title @threadTitle
     */
    ForumThread getThreadByTitle (String threadTitle);

    /**
     * Retrieves a thread by it's topic
     * @param threadTopic
     * @return thread with title @threadTopic
     */
    ForumThread getThreadByTopic (String threadTopic);

    /**
     * Updates a thread
     * @param oldTitle
     * @param newThread
     */
    public void updateThread(String oldTitle, ForumThread newThread);

    /**
     * Deletes a thread
     * @param threadToDelete
     */
    void deleteThreadByTitle (ForumThread threadToDelete);
}
