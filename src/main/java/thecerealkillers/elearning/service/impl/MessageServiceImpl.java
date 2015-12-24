package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.MessagesDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Message;
import thecerealkillers.elearning.service.MessageService;

import java.util.List;

/**
 * Created by Lucian on 10.11.2015.
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessagesDAO messagesDAO;

    @Override
    public void add(Message message) throws ServiceException {
        try {
            messagesDAO.add(message);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<Message> getAll() throws ServiceException {
        try {
            return messagesDAO.getAll();
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<Message> getMessagesBetweenUsers(String senderUsername, String receiverUsername) throws ServiceException {
        try {
            return messagesDAO.getMessagesBetweenUsers(senderUsername, receiverUsername);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<Message> getMessagesByUser(String username) throws ServiceException {
        try {
            return messagesDAO.getMessagesByUser(username);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void delete(String username) throws ServiceException {
        try {
            messagesDAO.delete(username);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
