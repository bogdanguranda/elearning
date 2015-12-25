package thecerealkillers.elearning.service.impl;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.ForumThreadService;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.ForumThreadDAO;
import thecerealkillers.elearning.model.ForumThread;

import org.springframework.beans.factory.annotation.Autowired;
import thecerealkillers.elearning.service.TopicService;
import thecerealkillers.elearning.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Dani
 */
@Service
public class ForumThreadServiceImpl implements ForumThreadService {

    @Autowired
    private ForumThreadDAO forumThreadDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;


    @Override
    public void add(ForumThread newForumThread, String topicTitle) throws ServiceException, NotFoundException {
        try {
            if (!exists(newForumThread.getTitle())) {
                if (userService.usernameExists(newForumThread.getOwner())) {
                    if (topicService.exists(topicTitle)) {
                        forumThreadDAO.add(newForumThread, topicTitle);
                    } else {
                        throw new NotFoundException(NotFoundException.NO_TOPIC);
                    }
                } else {
                    throw new NotFoundException(NotFoundException.NO_USER);
                }
            } else {
                throw new ServiceException(ServiceException.THREAD_ALREADY_EXISTS);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.ADD_FORUM_THREAD);
        }
    }

    @Override
    public List<ForumThread> getAll() throws ServiceException, NotFoundException {
        try {
            return forumThreadDAO.getAll();
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_ALL_THREADS);
        }
    }

    @Override
    public List<ForumThread> getThreadsOwnedByUser(String userName) throws ServiceException, NotFoundException {
        try {
            if (userService.usernameExists(userName)) {
                return forumThreadDAO.getThreadsOwnedByUser(userName);
            } else {
                throw new NotFoundException(NotFoundException.NO_USER);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_THREADS_BY_OWNER);
        }
    }

    @Override
    public ForumThread getThreadByTitle(String threadTitle) throws ServiceException, NotFoundException {
        try {
            if (exists(threadTitle)) {
                return forumThreadDAO.getThreadByTitle(threadTitle);
            } else {
                throw new NotFoundException(NotFoundException.NO_THREAD);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_THREAD_BY_TITLE);
        }
    }

    @Override
    public List<ForumThread> getThreadsForTopic(String topic) throws ServiceException, NotFoundException {
        try {
            if (topicService.exists(topic)) {
                return forumThreadDAO.getThreadsForTopic(topic);
            } else {
                throw new NotFoundException(NotFoundException.NO_TOPIC);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.GET_THREADS_TOPIC);
        }
    }

    @Override
    public void updateThread(String oldTitle, ForumThread newThread) throws ServiceException, NotFoundException {
        try {
            if (exists(oldTitle)) {
                if (!exists(newThread.getTitle())) {
                    if (userService.usernameExists(newThread.getOwner())) {
                        forumThreadDAO.updateThread(oldTitle, newThread);
                    } else {
                        throw new NotFoundException(NotFoundException.NO_USER);
                    }
                } else {
                    throw new ServiceException(ServiceException.THREAD_ALREADY_EXISTS);
                }
            } else {
                throw new NotFoundException(NotFoundException.NO_THREAD);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.UPDATE_THREAD);
        }
    }

    @Override
    public void deleteThreadByTitle(String threadToDelete) throws ServiceException, NotFoundException {
        try {
            if (exists(threadToDelete)) {
                forumThreadDAO.deleteThreadByTitle(threadToDelete);
            } else {
                throw new NotFoundException(NotFoundException.NO_THREAD);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.DELETE_THREAD_BY_TITLE);
        }
    }

    @Override
    public Boolean exists(String title) throws ServiceException {
        try {
            forumThreadDAO.getThreadByTitle(title);

            return true;
        } catch (NotFoundException e) {
            return false;
        } catch (DAOException e) {
            throw new ServiceException(ServiceException.GET_THREAD_BY_TITLE);
        }
    }
}
