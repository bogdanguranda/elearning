package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.Message;

import java.util.List;

/**
 * Created by Lucian on 10.11.2015.
 */

@RestController
public interface MessagesController {

    /**
     * Create new Message
     * @param message
     * @return
     */
    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    ResponseEntity createMessage(@RequestBody Message message);

    /**
     * Gets all messages from DB
     * @return
     */
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    ResponseEntity<List<Message>> getAllMessages();

    /**
     * Gets all messages between 2 users
     * @param senderUsername
     * @param receiverUsername
     * @return
     */
    @RequestMapping(value = "/messages/{senderUsername}/{receiverUsername}", method = RequestMethod.GET)
    ResponseEntity<List<Message>> getMessagesBetweenUsers(
            @PathVariable("senderUsername") String senderUsername,
            @PathVariable("receiverUsername") String receiverUsername);

    /**
     * Gets all messages sended by a specific user
     * @param username
     * @return
     */
    @RequestMapping(value = "/messages/{username}", method = RequestMethod.GET)
    ResponseEntity<List<Message>> getMessagesByUser(@PathVariable("username") String username);

    /**
     * Delete all messages sended by specific user
     * @param username
     * @return
     */
    @RequestMapping(value = "/messages", method = RequestMethod.DELETE)
    ResponseEntity deleteUserMessages(@RequestParam(value = "username", required = true) String username);
}