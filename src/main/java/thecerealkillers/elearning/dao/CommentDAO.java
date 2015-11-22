package thecerealkillers.elearning.dao;

import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Comment;

import java.util.Date;
import java.util.List;


public interface CommentDAO {

    /**
     * Adds a comment in the database
     *
     * @param owner
     * @param message
     * @param threadTitle
     * @throws DAOException
     */
    void addComment(String owner, String message, String threadTitle) throws DAOException;

    /**
     * Retrieves the comment posted by @owner at @timeStamp
     *
     * @param owner
     * @param timeStamp
     * @return comment posted by @owner at @timeStamp
     * @throws DAOException
     */
    Comment getCommentByOwnerAndTimeStamp(String owner, Date timeStamp) throws DAOException;

    /**
     * Retrieves a list wits all the comment that are posted on a thread
     *
     * @param threadTitle
     * @return comments posted in the thread with the title @threadTitle
     * @throws DAOException
     */
    List<Comment> getCommentsForThread(String threadTitle) throws DAOException;

    /**
     * Updates the message posted by @owner at @timeStamp with the message stored by @newMessage
     *
     * @param owner
     * @param timeStamp
     * @param newMessage
     * @throws DAOException
     */
    void updateComment(String owner, Date timeStamp, String newMessage) throws DAOException;

    /**
     * Deletes a comment posted by @owner at @timeStamp
     *
     * @param owner
     * @param timeStamp
     * @throws DAOException
     */
    void deleteComment(String owner, Date timeStamp) throws DAOException;
}
