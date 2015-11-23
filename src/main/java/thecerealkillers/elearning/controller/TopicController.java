package thecerealkillers.elearning.controller;

import thecerealkillers.elearning.model.Topic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Dani
 */
public interface TopicController {

    @RequestMapping(value = "/topics", method = RequestMethod.POST)
    ResponseEntity createTopic(@RequestBody Topic newTopic, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    ResponseEntity<List<Topic>> getAllTopics(@RequestHeader(value = "token") String token);

    @RequestMapping(value = "/topics/{title}", method = RequestMethod.GET)
    ResponseEntity<Topic> getTopicByTitle(@PathVariable("title") String title, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/topics/{title}", method = RequestMethod.POST)
    ResponseEntity updateTopic(@PathVariable("title") String title, @RequestBody Topic newTopic, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/topics", method = RequestMethod.DELETE)
    ResponseEntity deleteTopicByTitle(@RequestParam(value = "title", required = true) String title, @RequestHeader(value = "token") String token);
}