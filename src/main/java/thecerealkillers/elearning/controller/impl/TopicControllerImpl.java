package thecerealkillers.elearning.controller.impl;

import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.controller.TopicController;
import thecerealkillers.elearning.service.TopicService;
import thecerealkillers.elearning.model.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class TopicControllerImpl implements TopicController {

    @Autowired
    private TopicService topicService;

    @Override
    public ResponseEntity createTopic(@RequestBody Topic newTopic) {
        try {
            topicService.add(newTopic);

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(service_exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity<List<Topic>> getAllTopics() {
        try {
            List<Topic> topicList = topicService.getAll();

            return new ResponseEntity<>(topicList, HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Topic> getTopicByTitle(@PathVariable("title") String title) {
        try {
            Topic topic = topicService.get(title);

            return new ResponseEntity<>(topic, HttpStatus.CREATED);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(new Topic(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity updateTopic(@PathVariable("title") String title, @RequestBody Topic newTopic) {
        try {
            topicService.update(title, newTopic);

            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(service_exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity deleteTopicByTitle(@RequestParam(value = "title", required = true) String title) {
        try {
            topicService.delete(title);

            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(service_exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
