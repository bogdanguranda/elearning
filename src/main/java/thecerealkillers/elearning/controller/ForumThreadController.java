package thecerealkillers.elearning.controller;

import thecerealkillers.elearning.model.ForumThread;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


/**
 * Created by Dani
 */
public interface ForumThreadController {

    @RequestMapping(value = "/threads/topic/{topic}", method = RequestMethod.POST)
    ResponseEntity createThread(@RequestBody ForumThread newThread, @PathVariable("topic") String topic, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/threads", method = RequestMethod.GET)
    ResponseEntity<List<ForumThread>> getAll(@RequestHeader(value = "token") String token);

    @RequestMapping(value = "/threads/owner/{threadOwner}", method = RequestMethod.GET)
    ResponseEntity<List<ForumThread>> getThreadsOwnedByUser(@PathVariable("threadOwner") String userName, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/threads/title/{threadTitle}", method = RequestMethod.GET)
    ResponseEntity<ForumThread> getThreadByTitle(@PathVariable("threadTitle") String threadTitle, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/threads/topic/{threadTopic}", method = RequestMethod.GET)
    ResponseEntity<List<ForumThread>> getThreadsForTopic(@PathVariable("threadTopic") String threadTopic, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/threads/{oldTitle}", method = RequestMethod.POST)
    ResponseEntity updateThread(@PathVariable("oldTitle") String oldTitle, @RequestBody ForumThread newThread, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/threads", method = RequestMethod.DELETE)
    ResponseEntity deleteThreadByTitle(@RequestParam(value = "threadTitle", required = true) String threadTitle, @RequestHeader(value = "token") String token);
}