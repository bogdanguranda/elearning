package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.CommentController;
import thecerealkillers.elearning.model.Comment;
import thecerealkillers.elearning.service.CommentService;

import java.util.List;

@RestController
@CrossOrigin
public class CommentControllerImpl implements CommentController {

    @Autowired
    private CommentService commentService;

    @Override
    public ResponseEntity createComment(@RequestParam(value = "message", required = true) String message, @PathVariable("owner") String owner, @PathVariable("threadTitle") String threadTitle) {
        commentService.addComment(owner, message, threadTitle);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Comment> getCommentByOwnerAndTimeStamp(@RequestBody Comment comment) {
        System.out.println("getCommentByOwnerAndTimeStamp    a");

            Comment com = commentService.getCommentByOwnerAndTimeStamp(comment.getOwner(), comment.getTimeStamp());

            return new ResponseEntity<>(com, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Comment>> getCommentsForThread(@PathVariable("threadTitle") String threadTitle) {
        System.out.println("getCommentsForThread");
        List<Comment> commentList = commentService.getCommentsForThread(threadTitle);

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateComment(@RequestBody Comment comment) {
        commentService.updateComment(comment.getOwner(), comment.getTimeStamp(), comment.getMessage());

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteComment(@RequestBody Comment comment) {
        commentService.deleteComment(comment.getOwner(), comment.getTimeStamp());

        return new ResponseEntity(HttpStatus.OK);
    }
}