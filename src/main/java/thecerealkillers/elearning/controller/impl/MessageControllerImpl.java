package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import thecerealkillers.elearning.controller.MessagesController;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.model.Message;
import thecerealkillers.elearning.service.MessageService;

import java.util.List;

/**
 * Created by Lucian on 10.11.2015.
 */

@RestController
public class MessageControllerImpl implements MessagesController{

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messageList = messageService.getAll();

        return new ResponseEntity<>(messageList, HttpStatus.OK);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ResponseEntity createMessage(@RequestBody Message message) {
         messageService.add(message);

        return new ResponseEntity(HttpStatus.OK);
    }

}
