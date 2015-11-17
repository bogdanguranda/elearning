package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.MessagesController;
import thecerealkillers.elearning.model.Message;
import thecerealkillers.elearning.service.MessageService;

import java.util.List;

/**
 * Created by Lucian on 10.11.2015.
 */

@RestController
public class MessageControllerImpl implements MessagesController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ResponseEntity createMessage(@RequestBody Message message) {
        messageService.add(message);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messageList = messageService.getAll();

        return new ResponseEntity<>(messageList, HttpStatus.OK);
    }

    @RequestMapping(value = "/messages/{senderUsername}/{receiverUsername}", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getMessagesBetweenUsers(
            @PathVariable("senderUsername") String senderUsername, @PathVariable("receiverUsername") String receiverUsername) {
        List<Message> messagesBetweenUsers = messageService.getMessagesBetweenUsers(senderUsername, receiverUsername);

        return new ResponseEntity<>(messagesBetweenUsers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/messages/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable("username") String username) {
        List<Message> messagesByUser = messageService.getMessagesByUser(username);

        return new ResponseEntity<>(messagesByUser, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserMessages(@RequestParam(value = "username", required = true) String username) {
        messageService.delete(username);

        return new ResponseEntity(HttpStatus.OK);
    }
}
