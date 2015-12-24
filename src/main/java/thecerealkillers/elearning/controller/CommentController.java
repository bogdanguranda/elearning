package thecerealkillers.elearning.controller;

import thecerealkillers.elearning.model.Comment;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


/**
 * Created by Dani
 */
public interface CommentController {

    @RequestMapping(value = "/comments/add/{threadTitle}", method = RequestMethod.POST)
    ResponseEntity createComment(
            @RequestBody Comment comment,
            @PathVariable("threadTitle") String threadTitle,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/comments/owner", method = RequestMethod.POST)
    ResponseEntity<?> getCommentByOwnerAndTimeStamp(
            @RequestBody Comment comment,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/comments/thread/{threadTitle}", method = RequestMethod.GET)
    ResponseEntity<?> getCommentsForThread(
            @PathVariable("threadTitle") String threadTitle,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/comments/update", method = RequestMethod.POST)
    ResponseEntity updateComment(
            @RequestBody Comment comment,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/comments/delete", method = RequestMethod.DELETE)
    ResponseEntity deleteComment(
            @RequestBody Comment comment,
            @RequestHeader(value = "token") String token);
}