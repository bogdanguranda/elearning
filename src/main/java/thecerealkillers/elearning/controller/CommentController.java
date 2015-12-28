package thecerealkillers.elearning.controller;


import thecerealkillers.elearning.model.ForumThreadIdentifier;
import thecerealkillers.elearning.model.CommentUpdateInfo;
import thecerealkillers.elearning.model.Comment;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


/**
 * Created by Dani
 */
public interface CommentController {


    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    ResponseEntity createComment(
            @RequestBody Comment comment,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/comments/{commentID}", method = RequestMethod.GET)
    ResponseEntity<?> getComment(
            @PathVariable("commentID") Integer commentID,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/comments/thread", method = RequestMethod.GET)
    ResponseEntity<?> getCommentsInThread(@RequestParam(value = "topicTitle") String topicTitle,
                                          @RequestParam(value = "threadTitle") String threadTitle,
                                          @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/comments/update", method = RequestMethod.POST)
    ResponseEntity updateComment(
            @RequestBody CommentUpdateInfo commentUpdateInfo,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/comments/{commentID}", method = RequestMethod.DELETE)
    ResponseEntity deleteComment(
            @PathVariable("commentID") Integer commentID,
            @RequestHeader(value = "token") String token);
}