package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.Topic;

import java.util.List;

public interface TopicController {
    @RequestMapping(value = "/topics", method = RequestMethod.POST)
    ResponseEntity createTopic(@RequestBody Topic newTopic);

    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    ResponseEntity<List<Topic>> getAllTopics();

    @RequestMapping(value = "/topics/{title}", method = RequestMethod.GET)
    ResponseEntity<Topic> getTopicByTitle(@PathVariable("title") String title);

    @RequestMapping(value = "/topics/{title}", method = RequestMethod.POST)
    ResponseEntity updateTopic(@PathVariable("title") String title, @RequestBody Topic newTopic);

    @RequestMapping(value = "/topics", method = RequestMethod.DELETE)
    ResponseEntity deleteTopicByTitle(@RequestParam(value = "title", required = true) String title);
}