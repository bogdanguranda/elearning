package thecerealkillers.elearning.dao;

import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.model.Message;

import java.util.List;

/**
 * Created by Lucian on 10.11.2015.
 */

@Repository
public interface MessagesDAO {

    void add(Message message);

    List<Message> getAll();

    List<Message> getMessagesBetweenUsers(String senderUsername, String receiverUsername);

    List<Message> getMessagesByUser(String username);

    void delete(String username);
}
