package thecerealkillers.elearning.controller;


import thecerealkillers.elearning.model.ForumThreadIdentifier;
import thecerealkillers.elearning.model.ForumThread;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


/**
 * Created by Dani
 */
public interface ForumThreadController {

    @RequestMapping(value = "/threads", method = RequestMethod.POST)
    ResponseEntity createThread(
            @RequestBody ForumThread newThread,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/threads/{topicTitle}{threadTitle}", method = RequestMethod.POST)
    ResponseEntity<?> getThread(@RequestParam(value = "topicTitle") String topicTitle,
                                @RequestParam(value = "threadTitle") String threadTitle,
                                @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/threads/topic/{topicTitle}", method = RequestMethod.GET)
    ResponseEntity<?> getThreadsInTopic(
            @PathVariable("topicTitle") String topicTitle,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/threads/owner/{threadOwner}", method = RequestMethod.GET)
    ResponseEntity<?> getThreadsOwnedByUser(
            @PathVariable("threadOwner") String userName,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/threads", method = RequestMethod.GET)
    ResponseEntity<?> getAll(
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/threads/update/{newTitle}", method = RequestMethod.POST)
    ResponseEntity updateThread(
            @PathVariable("newTitle") String newTitle,
            @RequestBody ForumThread thread,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/threads", method = RequestMethod.DELETE)
    ResponseEntity deleteThread(
            @RequestBody ForumThreadIdentifier threadToDelete,
            @RequestHeader(value = "token") String token);
}