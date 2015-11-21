package thecerealkillers.elearning.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.ForumThread;

import java.util.List;

public interface ForumThreadController {

    @RequestMapping(value = "/threads", method = RequestMethod.POST)
    ResponseEntity createThread(@RequestBody ForumThread newForumThread);

    @RequestMapping(value = "/threads/{threadOwner}", method = RequestMethod.GET)
    RequestEntity<List<ForumThread>> getThreadsOwnedByUser(@PathVariable("threadOwner") String userName);

    @RequestMapping(value = "/threads/{threadTitle}", method = RequestMethod.GET)
    RequestEntity<ForumThread> getThreadByTitle(@PathVariable("threadTitle") String threadTitle);

    @RequestMapping(value = "/threads/{threadTopic}", method = RequestMethod.GET)
    RequestEntity<List<ForumThread>> getThreadByTopic(@PathVariable("threadTopic") String threadTopic);

    @RequestMapping(value = "/threads/{oldTitle}", method = RequestMethod.POST)
    ResponseEntity updateThread(@PathVariable("oldTitle") String oldTitle, @RequestBody ForumThread newThread);

    @RequestMapping(value = "/threads", method = RequestMethod.DELETE)
    ResponseEntity deleteThreadByTitle(@RequestParam(value = "threadTitle", required = true) String threadTitle);
}
