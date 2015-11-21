package thecerealkillers.elearning.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.ForumThread;

import java.util.List;

public interface ForumThreadController {


    @RequestMapping(value = "/threads/topic/{topic}", method = RequestMethod.POST)
    ResponseEntity createThread(@RequestBody ForumThread newThread, @PathVariable("topic") String topic);

    @RequestMapping(value = "/threads", method = RequestMethod.GET)
    ResponseEntity<List<ForumThread>> getAll();

    @RequestMapping(value = "/threads/owner/{threadOwner}", method = RequestMethod.GET)
    ResponseEntity<List<ForumThread>> getThreadsOwnedByUser(@PathVariable("threadOwner") String userName);

    @RequestMapping(value = "/threads/title/{threadTitle}", method = RequestMethod.GET)
    ResponseEntity<ForumThread> getThreadByTitle(@PathVariable("threadTitle") String threadTitle);

    @RequestMapping(value = "/threads/topic/{threadTopic}", method = RequestMethod.GET)
    ResponseEntity<List<ForumThread>> getThreadsForTopic(@PathVariable("threadTopic") String threadTopic);

    @RequestMapping(value = "/threads/{oldTitle}", method = RequestMethod.POST)
    ResponseEntity updateThread(@PathVariable("oldTitle") String oldTitle, @RequestBody ForumThread newThread);

    @RequestMapping(value = "/threads", method = RequestMethod.DELETE)
    ResponseEntity deleteThreadByTitle(@RequestParam(value = "threadTitle", required = true) String threadTitle);
}