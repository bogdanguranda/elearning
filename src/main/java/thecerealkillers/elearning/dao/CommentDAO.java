package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Comment;

import org.springframework.stereotype.Repository;

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
    void addComment(Comment comment) throws DAOException;

    /**
     * Retrieves the comment posted by @owner at @timeStamp
     *
     * @return comment posted by @owner at @timeStamp
     * @throws DAOException
     */
    Comment getComment(Integer commentID) throws DAOException, NotFoundException;

    /**
     * Retrieves a list wits all the comment that are posted on a thread
     *
     * @return comments posted in the thread with the title @threadTitle
     * @throws DAOException
     */
    List<Comment> getCommentsInThread(String threadTitle, String topicTitle) throws DAOException;

    /**
     * Updates the message posted by @owner at @timeStamp with the message stored by @newMessage
     *
     * @throws DAOException
     */
    void updateComment(Integer commentID, String newMessage) throws DAOException;

    /**
     * Deletes a comment posted by @owner at @timeStamp
     *
     * @throws DAOException
     */
    void deleteComment(Integer commentID) throws DAOException;
}
