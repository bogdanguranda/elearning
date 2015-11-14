package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.MessagesDAO;
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
    public void add(Message message) {
        messagesDAO.add(message);
    }

    @Override
    public List<Message> getAll() {
        return messagesDAO.getAll();
    }

    @Override
    public List<Message> getMessagesBetweenUsers(String senderUsername, String receiverUsername) {
        return messagesDAO.getMessagesBetweenUsers(senderUsername, receiverUsername);
    }

    @Override
    public List<Message> getMessagesByUser(String username) {
        return messagesDAO.getMessagesByUser(username);
    }
}
