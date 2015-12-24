package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.model.Comment;

import java.util.Date;
import java.util.List;


/**
 * Created by Dani
 */
@Repository
public interface CommentDAO {

    /**
     * Adds a comment in the database
     *
     * @throws DAOException
     */
    void addComment(String owner, String message, String threadTitle) throws DAOException;

    /**
     * Retrieves the comment posted by @owner at @timeStamp
     *
     * @return comment posted by @owner at @timeStamp
     * @throws DAOException
     */
    Comment getCommentByOwnerAndTimeStamp(String owner, Date timeStamp) throws DAOException;

    /**
     * Retrieves a list wits all the comment that are posted on a thread
     *
     * @return comments posted in the thread with the title @threadTitle
     * @throws DAOException
     */
    List<Comment> getCommentsForThread(String threadTitle) throws DAOException;

    /**
     * Updates the message posted by @owner at @timeStamp with the message stored by @newMessage
     *
     * @throws DAOException
     */
    void updateComment(String owner, Date timeStamp, String newMessage) throws DAOException;

    /**
     * Deletes a comment posted by @owner at @timeStamp
     *
     * @throws DAOException
     */
    void deleteComment(String owner, Date timeStamp) throws DAOException;
}
