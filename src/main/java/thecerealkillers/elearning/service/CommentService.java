package thecerealkillers.elearning.service;

import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Comment;

import java.util.Date;
import java.util.List;


public interface CommentService {

    /**
     * Adds a comment in the database
     *
     * @param owner
     * @param message
     * @param threadTitle
     * @throws ServiceException
     */
    void addComment(String owner, String message, String threadTitle) throws ServiceException;

    /**
     * Retrieves the comment posted by @owner at @timeStamp
     *
     * @param owner
     * @param timeStamp
     * @return comment posted by @owner at @timeStamp
     * @throws ServiceException
     */
    Comment getCommentByOwnerAndTimeStamp(String owner, Date timeStamp) throws ServiceException;

    /**
     * Retrieves a list wits all the comment that are posted on a thread
     *
     * @param threadTitle
     * @return comments posted in the thread with the title @threadTitle
     * @throws ServiceException
     */
    List<Comment> getCommentsForThread(String threadTitle) throws ServiceException;

    /**
     * Updates the message posted by @owner at @timeStamp with the message stored by @newMessage
     *
     * @param owner
     * @param timeStamp
     * @param newMessage
     * @throws ServiceException
     */
    void updateComment(String owner, Date timeStamp, String newMessage) throws ServiceException;

    /**
     * Deletes a comment posted by @owner at @timeStamp
     *
     * @param owner
     * @param timeStamp
     * @throws ServiceException
     */
    void deleteComment(String owner, Date timeStamp) throws ServiceException;
}
