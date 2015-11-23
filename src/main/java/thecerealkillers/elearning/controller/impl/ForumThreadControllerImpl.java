package thecerealkillers.elearning.controller.impl;

import thecerealkillers.elearning.controller.ForumThreadController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.ForumThreadService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.model.ForumThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * Created by Dani
 */
@RestController
@CrossOrigin
public class ForumThreadControllerImpl implements ForumThreadController {

    @Autowired
    private ForumThreadService forumThreadService;
    @Autowired
    private SessionService sessionService;

    @Override
    public ResponseEntity createThread(@RequestBody ForumThread newThread, @PathVariable("topic") String topic, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            forumThreadService.add(newThread, topic);

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity<List<ForumThread>> getAll(@RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            List<ForumThread> threadList = forumThreadService.getAll();

            return new ResponseEntity<>(threadList, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ForumThread>> getThreadsOwnedByUser(@PathVariable("threadOwner") String userName, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            List<ForumThread> threadList = forumThreadService.getThreadsOwnedByUser(userName);

            return new ResponseEntity<>(threadList, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ForumThread> getThreadByTitle(@PathVariable("threadTitle") String threadTitle, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            ForumThread thread = forumThreadService.getThreadByTitle(threadTitle);

            return new ResponseEntity<>(thread, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ForumThread>> getThreadsForTopic(@PathVariable("threadTopic") String threadTopic, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            List<ForumThread> threadList = forumThreadService.getThreadsForTopic(threadTopic);

            return new ResponseEntity<>(threadList, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity updateThread(@PathVariable("oldTitle") String oldTitle, @RequestBody ForumThread newThread, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            forumThreadService.updateThread(oldTitle, newThread);

            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity deleteThreadByTitle(@RequestParam(value = "threadTitle", required = true) String threadTitle, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            forumThreadService.deleteThreadByTitle(threadTitle);

            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
