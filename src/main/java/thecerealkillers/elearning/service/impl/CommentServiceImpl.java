package thecerealkillers.elearning.service.impl;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.service.CommentService;
import thecerealkillers.elearning.dao.CommentDAO;
import thecerealkillers.elearning.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.service.ForumThreadService;
import thecerealkillers.elearning.service.TopicService;
import thecerealkillers.elearning.service.UserService;

import java.util.List;


/**
 * Created by Dani
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ForumThreadService forumThreadService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentDAO commentDAO;


    @Override
    public void addComment(Comment comment) throws ServiceException, NotFoundException {
        try {
            if (topicService.exists(comment.getTopic())) {
                if (forumThreadService.exists(comment.getThread(), comment.getTopic())) {
                    if (userService.usernameExists(comment.getOwner())) {
                        commentDAO.addComment(comment);
                    } else {
                        throw new NotFoundException(NotFoundException.NO_USER);
                    }
                } else {
                    throw new NotFoundException(NotFoundException.NO_THREAD);
                }
            } else {
                throw new NotFoundException(NotFoundException.NO_TOPIC);
            }

        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.ADD_COMMENT);
        }
    }

    @Override
    public Comment getComment(Integer commentID) throws ServiceException, NotFoundException {
        try {
            return commentDAO.getComment(commentID);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_COMMENT_USER_TIME);
        }
    }

    @Override
    public List<Comment> getCommentsInThread(String threadTitle, String topicTitle) throws ServiceException, NotFoundException {
        try {
            if (topicService.exists(topicTitle)) {
                if (forumThreadService.exists(threadTitle, topicTitle)) {
                    return commentDAO.getCommentsInThread(threadTitle, topicTitle);
                } else {
                    throw new NotFoundException(NotFoundException.NO_THREAD);
                }
            } else {
                throw new NotFoundException(NotFoundException.NO_TOPIC);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_COMMENTS_THREAD);
        }
    }

    @Override
    public void updateComment(Integer commentID, String newMessage) throws ServiceException, NotFoundException {
        try {
            if (exists(commentID)) {
                commentDAO.updateComment(commentID, newMessage);
            } else {
                throw new NotFoundException(NotFoundException.NO_COMMENT);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.UPDATE_COMMENT);
        }
    }

    @Override
    public void deleteComment(Integer commentID) throws ServiceException, NotFoundException {
        try {
            if (exists(commentID)) {
                commentDAO.deleteComment(commentID);
            } else {
                throw new NotFoundException(NotFoundException.NO_COMMENT);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.DELETE_COMMENT);
        }
    }

    @Override
    public Boolean exists(Integer commentID) throws ServiceException, NotFoundException {
        try {
            commentDAO.getComment(commentID);

            return true;
        } catch (NotFoundException e) {
            return false;
        } catch (DAOException e) {
            throw new ServiceException(ServiceException.GET_COMMENT_USER_TIME);
        }
    }
}
