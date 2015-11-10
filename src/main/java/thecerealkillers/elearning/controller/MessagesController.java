package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import thecerealkillers.elearning.model.Message;

import java.util.List;

/**
 * Created by Lucian on 10.11.2015.
 */

@RestController
public interface MessagesController {

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    ResponseEntity<List<Message>> getAllMessages();


}
