package thecerealkillers.elearning.service;

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
     */
    void addComment(String owner, String message, String threadTitle);

    /**
     * Retrieves the comment posted by @owner at @timeStamp
     *
     * @param owner
     * @param timeStamp
     * @return comment posted by @owner at @timeStamp
     */
    Comment getCommentByOwnerAndTimeStamp(String owner, Date timeStamp);

    /**
     * Retrieves a list wits all the comment that are posted on a thread
     *
     * @param threadTitle
     * @return comments posted in the thread with the title @threadTitle
     */
    List<Comment> getCommentsForThread(String threadTitle);

    /**
     * Updates the message posted by @owner at @timeStamp with the message stored by @newMessage
     *
     * @param owner
     * @param timeStamp
     * @param newMessage
     */
    void updateComment(String owner, Date timeStamp, String newMessage);

    /**
     * Deletes a comment posted by @owner at @timeStamp
     *
     * @param owner
     * @param timeStamp
     */
    void deleteComment(String owner, Date timeStamp);
}
