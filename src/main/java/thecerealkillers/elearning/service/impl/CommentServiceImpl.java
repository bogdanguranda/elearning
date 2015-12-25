package thecerealkillers.elearning.service.impl;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.service.CommentService;
import thecerealkillers.elearning.dao.CommentDAO;
import thecerealkillers.elearning.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by Dani
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public void addComment(String owner, String message, String threadTitle) throws ServiceException {
        try {
            commentDAO.addComment(owner, message, threadTitle);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.ADD_COMMENT);
        }
    }

    @Override
    public Comment getCommentByOwnerAndTimeStamp(String owner, Date timeStamp) throws ServiceException {
        try {
            return commentDAO.getCommentByOwnerAndTimeStamp(owner, timeStamp);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_COMMENT_USER_TIME);
        }
    }

    @Override
    public List<Comment> getCommentsForThread(String threadTitle) throws ServiceException {
        try {
            return commentDAO.getCommentsForThread(threadTitle);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_COMMENTS_THREAD);
        }
    }

    @Override
    public void updateComment(String owner, Date timeStamp, String newMessage) throws ServiceException {
        try {
            commentDAO.updateComment(owner, timeStamp, newMessage);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.UPDATE_COMMENT);
        }
    }

    @Override
    public void deleteComment(String owner, Date timeStamp) throws ServiceException {
        try {
            commentDAO.deleteComment(owner, timeStamp);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.DELETE_COMMENT);
        }
    }
}
