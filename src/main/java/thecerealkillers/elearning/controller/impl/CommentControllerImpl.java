package thecerealkillers.elearning.controller.impl;

import thecerealkillers.elearning.controller.CommentController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.CommentService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.model.Comment;

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
public class CommentControllerImpl implements CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private SessionService sessionService;

    @Override
    public ResponseEntity createComment(@RequestParam(value = "message", required = true) String message, @PathVariable("owner") String owner, @PathVariable("threadTitle") String threadTitle, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            commentService.addComment(owner, message, threadTitle);

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity<Comment> getCommentByOwnerAndTimeStamp(@RequestBody Comment comment, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            Comment com = commentService.getCommentByOwnerAndTimeStamp(comment.getOwner(), comment.getTimeStamp());

            return new ResponseEntity<>(com, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Comment>> getCommentsForThread(@PathVariable("threadTitle") String threadTitle, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            List<Comment> commentList = commentService.getCommentsForThread(threadTitle);

            return new ResponseEntity<>(commentList, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity updateComment(@RequestBody Comment comment, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            commentService.updateComment(comment.getOwner(), comment.getTimeStamp(), comment.getMessage());

            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity deleteComment(@RequestBody Comment comment, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            commentService.deleteComment(comment.getOwner(), comment.getTimeStamp());

            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}