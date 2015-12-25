package thecerealkillers.elearning.service.impl;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.ForumThreadService;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.ForumThreadDAO;
import thecerealkillers.elearning.model.ForumThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Dani
 */
@Service
public class ForumThreadServiceImpl implements ForumThreadService {

    @Autowired
    private ForumThreadDAO forumThreadDAO;

    @Override
    public void add(ForumThread newForumThread, String topicTitle) throws ServiceException {
        try {
            forumThreadDAO.add(newForumThread, topicTitle);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.ADD_FORUM_THREAD);
        }
    }

    @Override
    public List<ForumThread> getAll() throws ServiceException {
        try {
            return forumThreadDAO.getAll();
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_ALL_THREADS);
        }
    }

    @Override
    public List<ForumThread> getThreadsOwnedByUser(String userName) throws ServiceException {
        try {
            return forumThreadDAO.getThreadsOwnedByUser(userName);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_THREADS_BY_OWNER);
        }
    }

    @Override
    public ForumThread getThreadByTitle(String threadTitle) throws ServiceException {
        try {
            return forumThreadDAO.getThreadByTitle(threadTitle);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_THREAD_BY_TITLE);
        }
    }

    @Override
    public List<ForumThread> getThreadsForTopic(String topic) throws ServiceException {
        try {
            return forumThreadDAO.getThreadsForTopic(topic);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_THREADS_TOPIC);
        }
    }

    @Override
    public void updateThread(String oldTitle, ForumThread newThread) throws ServiceException {
        try {
            forumThreadDAO.updateThread(oldTitle, newThread);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.UPDATE_THREAD);
        }
    }

    @Override
    public void deleteThreadByTitle(String threadToDelete) throws ServiceException {
        try {
            forumThreadDAO.deleteThreadByTitle(threadToDelete);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.DELETE_THREAD_BY_TITLE);
        }
    }
}
