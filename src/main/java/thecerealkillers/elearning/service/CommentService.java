package thecerealkillers.elearning.service;

import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Comment;

import java.util.Date;
import java.util.List;


/**
 * Created by Dani
 */
public interface CommentService {


    /**
     * Adds a comment in the database
     *
     * @throws ServiceException
     * @throws NotFoundException
     */
    void addComment(Comment comment) throws ServiceException, NotFoundException;


    /**
     * Retrieves the comment posted by @owner at @timeStamp in @threadTitle
     *
     * @return comment posted by @owner at @timeStamp
     * @throws ServiceException
     * @throws NotFoundException
     */
    Comment getComment(Integer commentID) throws ServiceException, NotFoundException;


    /**
     * Retrieves a list wits all the comment that are posted on a thread
     *
     * @return comments posted in the thread with the title @threadTitle
     * @throws ServiceException
     * @throws NotFoundException
     */
    List<Comment> getCommentsInThread(String threadTitle, String topicTitle) throws ServiceException, NotFoundException;


    /**
     * Updates the message posted by @owner at @timeStamp with the message stored by @newMessage
     *
     * @throws ServiceException
     * @throws NotFoundException
     */
    void updateComment(Integer commentID, String newMessage) throws ServiceException, NotFoundException;


    /**
     * Deletes a comment posted by @owner at @timeStamp in @threadTitle
     *
     * @throws ServiceException
     * @throws NotFoundException
     */
    void deleteComment(Integer commentID) throws ServiceException, NotFoundException;


    /**
     * Returns true if database contains a comment posted by owner at timestamp in @threadTitle
     *
     * @throws ServiceException
     * @throws NotFoundException
     */
    Boolean exists(Integer commentID) throws ServiceException, NotFoundException;
}
