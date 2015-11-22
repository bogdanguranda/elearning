package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.CommentController;
import thecerealkillers.elearning.model.Comment;
import thecerealkillers.elearning.service.CommentService;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class CommentControllerImpl implements CommentController {

    @Autowired
    private CommentService commentService;

    @Override
    public ResponseEntity createThread(@RequestParam(value = "message", required = true) String message, @PathVariable("owner") String owner, @PathVariable("threadTitle") String threadTitle) {
        commentService.addComment(owner,message,threadTitle);

        System.out.println("createThread");

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Comment> getCommentByOwnerAndTimeStamp(@PathVariable("owner") String owner, @RequestParam(value = "timeStamp", required = true) Date timeStamp) {
        Comment comment = commentService.getCommentByOwnerAndTimeStamp(owner, timeStamp);

        System.out.println("getCommentByOwnerAndTimeStamp");

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Comment>> getCommentsForThread(@PathVariable("threadTitle") String threadTitle) {
        System.out.println("getCommentsForThread");
        List<Comment> commentList = commentService.getCommentsForThread(threadTitle);

        System.out.println("getCommentsForThread");

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateComment(@PathVariable("owner") String owner, @PathVariable("timeStamp") Date timeStamp, @RequestParam(value = "message", required = true) String message) {
        commentService.updateComment(owner, timeStamp, message);

        System.out.println("updateComment");

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteComment(@PathVariable("owner") String owner, @PathVariable("timeStamp") Date timeStamp) {
        commentService.deleteComment(owner, timeStamp);

        System.out.println("deleteComment");

        return new ResponseEntity(HttpStatus.OK);
    }
}