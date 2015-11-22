package thecerealkillers.elearning.controller.impl;

import thecerealkillers.elearning.controller.CommentController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.CommentService;
import thecerealkillers.elearning.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class CommentControllerImpl implements CommentController {

    @Autowired
    private CommentService commentService;

    @Override
    public ResponseEntity createComment(@RequestParam(value = "message", required = true) String message, @PathVariable("owner") String owner, @PathVariable("threadTitle") String threadTitle) {
        try {
            commentService.addComment(owner, message, threadTitle);

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity<Comment> getCommentByOwnerAndTimeStamp(@RequestBody Comment comment) {
        try {
            System.out.println("getCommentByOwnerAndTimeStamp    a");

            Comment com = commentService.getCommentByOwnerAndTimeStamp(comment.getOwner(), comment.getTimeStamp());

            return new ResponseEntity<>(com, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(new Comment(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Comment>> getCommentsForThread(@PathVariable("threadTitle") String threadTitle) {
        try {
            System.out.println("getCommentsForThread");
            List<Comment> commentList = commentService.getCommentsForThread(threadTitle);

            return new ResponseEntity<>(commentList, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity updateComment(@RequestBody Comment comment) {
        try {
            commentService.updateComment(comment.getOwner(), comment.getTimeStamp(), comment.getMessage());

            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity deleteComment(@RequestBody Comment comment) {
        try {
            commentService.deleteComment(comment.getOwner(), comment.getTimeStamp());

            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}