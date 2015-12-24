package thecerealkillers.elearning.controller;


import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.Message;
import org.springframework.http.ResponseEntity;


/**
 * Created by Lucian on 10.11.2015.
 * Modified by Dani.
 */
@RestController
public interface MessagesController {

    /**
     * Create new Message
     */
    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    ResponseEntity createMessage(
            @RequestBody Message message,
            @RequestHeader(value = "token") String token);


    /**
     * Gets all messages from DB
     */
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    ResponseEntity<?> getAllMessages(
            @RequestHeader(value = "token") String token);


    /**
     * Gets all messages between 2 users
     */
    @RequestMapping(value = "/messages/{senderUsername}/{receiverUsername}", method = RequestMethod.GET)
    ResponseEntity<?> getMessagesBetweenUsers(
            @PathVariable("senderUsername") String senderUsername,
            @PathVariable("receiverUsername") String receiverUsername,
            @RequestHeader(value = "token") String token);


    /**
     * Gets all messages sent by a specific user
     */
    @RequestMapping(value = "/messages/{username}", method = RequestMethod.GET)
    ResponseEntity<?> getMessagesByUser(
            @PathVariable("username") String username,
            @RequestHeader(value = "token") String token);


    /**
     * Deletes all messages sent by specific user
     */
    @RequestMapping(value = "/messages", method = RequestMethod.DELETE)
    ResponseEntity deleteUserMessages(
            @RequestParam(value = "username", required = true) String username,
            @RequestHeader(value = "token") String token);
}