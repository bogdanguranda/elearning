package thecerealkillers.elearning.service.impl;

import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.service.TopicService;
import thecerealkillers.elearning.dao.TopicDAO;
import thecerealkillers.elearning.model.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDAO topicDAO;

    @Override
    public void add(Topic newTopic) throws ServiceException {
        try {
            topicDAO.add(newTopic);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public Topic get(String title) throws ServiceException {
        try {
            return topicDAO.get(title);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public List<Topic> getAll() throws ServiceException {
        try {
            return topicDAO.getAll();
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public void update(String title, Topic newTopicData) throws ServiceException {
        try {
            topicDAO.update(title, newTopicData);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public void delete(String title) throws ServiceException {
        try {
            topicDAO.delete(title);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }
}
