package thecerealkillers.elearning.service;

import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Message;

import java.util.List;

/**
 * Created by Lucian on 09.11.2015.
 */
public interface MessageService {

    void add(Message message) throws ServiceException;

    List<Message> getAll() throws ServiceException;

    List<Message> getMessagesBetweenUsers(String senderUsername, String receiverUsername) throws ServiceException;

    List<Message> getMessagesByUser(String username) throws ServiceException;

    void delete(String username) throws ServiceException;
}
