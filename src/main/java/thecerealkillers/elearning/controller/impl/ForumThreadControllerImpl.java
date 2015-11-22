package thecerealkillers.elearning.controller.impl;

import thecerealkillers.elearning.controller.ForumThreadController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.ForumThreadService;
import thecerealkillers.elearning.model.ForumThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class ForumThreadControllerImpl implements ForumThreadController {

    @Autowired
    private ForumThreadService forumThreadService;

    @Override
    public ResponseEntity createThread(@RequestBody ForumThread newThread, @PathVariable("topic") String topic) {
        try {
            forumThreadService.add(newThread, topic);

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity<List<ForumThread>> getAll() {
        try {
            List<ForumThread> threadList = forumThreadService.getAll();

            return new ResponseEntity<>(threadList, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ForumThread>> getThreadsOwnedByUser(@PathVariable("threadOwner") String userName) {
        try {
            List<ForumThread> threadList = forumThreadService.getThreadsOwnedByUser(userName);

            return new ResponseEntity<>(threadList, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ForumThread> getThreadByTitle(@PathVariable("threadTitle") String threadTitle) {
        try {
            ForumThread thread = forumThreadService.getThreadByTitle(threadTitle);

            return new ResponseEntity<>(thread, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(new ForumThread(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ForumThread>> getThreadsForTopic(@PathVariable("threadTopic") String threadTopic) {
        try {
            List<ForumThread> threadList = forumThreadService.getThreadsForTopic(threadTopic);

            return new ResponseEntity<>(threadList, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity updateThread(@PathVariable("oldTitle") String oldTitle, @RequestBody ForumThread newThread) {
        try {
            forumThreadService.updateThread(oldTitle, newThread);

            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity deleteThreadByTitle (@RequestParam(value = "threadTitle", required = true) String threadTitle) {
        try {
            forumThreadService.deleteThreadByTitle(threadTitle);

            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
