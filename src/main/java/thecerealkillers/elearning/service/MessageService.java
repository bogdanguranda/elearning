package thecerealkillers.elearning.service;

import thecerealkillers.elearning.model.Message;

import java.util.List;

/**
 * Created by Lucian on 09.11.2015.
 */
public interface MessageService {

    void add(Message message);

    List<Message> getAll();

    List<Message> getMessagesBetweenUsers(String senderUsername, String receiverUsername);

    List<Message> getMessagesByUser(String username);

    void delete(String username);
}
