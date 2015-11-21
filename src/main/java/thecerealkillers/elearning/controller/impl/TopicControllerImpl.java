package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.TopicController;
import thecerealkillers.elearning.model.Topic;
import thecerealkillers.elearning.service.TopicService;

import java.util.List;

@RestController
@CrossOrigin
public class TopicControllerImpl implements TopicController {

    @Autowired
    private TopicService topicService;

    @Override
    public ResponseEntity createTopic(@RequestBody Topic newTopic) {
        topicService.add(newTopic);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topicList = topicService.getAll();

        return new ResponseEntity<>(topicList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Topic> getTopicByTitle(@PathVariable("title") String title) {
        Topic topic = topicService.get(title);

        return new ResponseEntity<Topic>(topic, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateTopic(@PathVariable("title") String title, @RequestBody Topic newTopic) {
        topicService.update(title, newTopic);

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteTopicByTitle(@RequestParam(value = "title", required = true) String title) {
        topicService.delete(title);

        return new ResponseEntity(HttpStatus.OK);
    }
}
