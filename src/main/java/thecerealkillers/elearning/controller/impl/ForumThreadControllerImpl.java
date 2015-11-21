package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.ForumThreadController;
import thecerealkillers.elearning.model.ForumThread;
import thecerealkillers.elearning.service.ForumThreadService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ForumThreadControllerImpl implements ForumThreadController {

    @Autowired
    private ForumThreadService forumThreadService;

    @Override
    public ResponseEntity createThread(@RequestBody ForumThread newThread, @PathVariable("topic") String topic) {
        forumThreadService.add(newThread, topic);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ForumThread>> getAll() {
        List<ForumThread> threadList = forumThreadService.getAll();

        return new ResponseEntity<>(threadList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ForumThread>> getThreadsOwnedByUser(@PathVariable("threadOwner") String userName) {
        List<ForumThread> threadList = forumThreadService.getThreadsOwnedByUser(userName);

        return new ResponseEntity<>(threadList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ForumThread> getThreadByTitle(@PathVariable("threadTitle") String threadTitle) {
        ForumThread thread = forumThreadService.getThreadByTitle(threadTitle);

        return new ResponseEntity<>(thread, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ForumThread>> getThreadsForTopic(@PathVariable("threadTopic") String threadTopic) {
        List<ForumThread> threadList = forumThreadService.getThreadsForTopic(threadTopic);

        return new ResponseEntity<>(threadList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateThread(@PathVariable("oldTitle") String oldTitle, @RequestBody ForumThread newThread) {
        forumThreadService.updateThread(oldTitle, newThread);

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteThreadByTitle(@RequestParam(value = "threadTitle", required = true) String threadTitle) {
        forumThreadService.deleteThreadByTitle(threadTitle);

        return new ResponseEntity(HttpStatus.OK);
    }
}
