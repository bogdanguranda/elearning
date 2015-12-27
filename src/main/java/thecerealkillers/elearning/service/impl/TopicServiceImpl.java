package thecerealkillers.elearning.service.impl;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.service.TopicService;
import thecerealkillers.elearning.dao.TopicDAO;
import thecerealkillers.elearning.model.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Dani
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDAO topicDAO;

    @Override
    public void add(Topic newTopic) throws ServiceException {
        try {
            if (!exists(newTopic.getTitle())) {
                topicDAO.add(newTopic);
            } else {
                throw new ServiceException(ServiceException.TOPIC_ALREADY_EXISTS);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_ADD_TOPIC);
        }
    }

    @Override
    public Topic get(String title) throws ServiceException, NotFoundException {
        try {
            return topicDAO.get(title);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_GET_TOPIC);
        }
    }

    @Override
    public List<Topic> getAll() throws ServiceException, NotFoundException {
        try {
            return topicDAO.getAll();
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_GET_ALL_TOPICS);
        }
    }

    @Override
    public void update(String title, Topic newTopicData) throws ServiceException, NotFoundException {
        try {
            if (exists(title)) {
                if (!exists(newTopicData.getTitle())) {
                    topicDAO.update(title, newTopicData);
                } else {
                    throw new ServiceException(ServiceException.TOPIC_ALREADY_EXISTS);
                }
            } else {
                throw new NotFoundException(NotFoundException.NO_TOPIC_WITH_TITLE);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_UPDATE_TOPIC);
        }
    }

    @Override
    public void delete(String title) throws ServiceException, NotFoundException {
        try {
            if (exists(title)) {
                topicDAO.delete(title);
            } else {
                throw new NotFoundException(NotFoundException.NO_TOPIC_WITH_TITLE);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DELETE_TOPIC);
        }
    }

    @Override
    public Boolean exists(String title) throws ServiceException {
        try {
            topicDAO.get(title);

            return true;
        } catch (NotFoundException e) {
            return false;
        } catch (DAOException e) {
            throw new ServiceException(ServiceException.FAILED_GET_TOPIC);
        }
    }
}
