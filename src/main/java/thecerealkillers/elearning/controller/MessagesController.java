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

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    ResponseEntity createMessage(@RequestBody Message message);

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    ResponseEntity<List<Message>> getAllMessages();

    @RequestMapping(value = "/messages/{senderUsername}/{receiverUsername}", method = RequestMethod.GET)
    ResponseEntity<List<Message>> getMessagesBetweenUsers(
            @PathVariable("senderUsername") String senderUsername,
            @PathVariable("receiverUsername") String receiverUsername);

    @RequestMapping(value = "/messages/{username}", method = RequestMethod.GET)
    ResponseEntity<List<Message>> getMessagesByUser(@PathVariable("username") String username);
}