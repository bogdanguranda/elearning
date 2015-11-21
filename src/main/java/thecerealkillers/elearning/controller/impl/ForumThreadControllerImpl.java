package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.ForumThreadController;
import thecerealkillers.elearning.model.ForumThread;
import thecerealkillers.elearning.service.ForumThreadService;

import java.util.List;

@RestController
@CrossOrigin
public class ForumThreadControllerImpl implements ForumThreadController {

    @Autowired
    private ForumThreadService forumThreadService;

    @Override
    public ResponseEntity createThread(@RequestBody ForumThread newForumThread) {
        return null;
    }

    @Override
    public RequestEntity<List<ForumThread>> getThreadsOwnedByUser(@PathVariable("threadOwner") String userName) {
        return null;
    }

    @Override
    public RequestEntity<ForumThread> getThreadByTitle(@PathVariable("threadTitle") String threadTitle) {
        return null;
    }

    @Override
    public RequestEntity<List<ForumThread>> getThreadByTopic(@PathVariable("threadTopic") String threadTopic) {
        return null;
    }

    @Override
    public ResponseEntity updateThread(@PathVariable("oldTitle") String oldTitle, @RequestBody ForumThread newThread) {
        return null;
    }

    @Override
    public ResponseEntity deleteThreadByTitle(@RequestParam(value = "threadTitle", required = true) String threadTitle) {
        return null;
    }
}
