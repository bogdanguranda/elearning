package thecerealkillers.elearning.dao;

import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Message;

import java.util.List;

/**
 * Created by Lucian on 10.11.2015.
 */

@Repository
public interface MessagesDAO {

    void add(Message message) throws DAOException;

    List<Message> getAll() throws DAOException;

    List<Message> getMessagesBetweenUsers(String senderUsername, String receiverUsername) throws DAOException;

    List<Message> getMessagesByUser(String username) throws DAOException;

    void delete(String username) throws DAOException;
}
