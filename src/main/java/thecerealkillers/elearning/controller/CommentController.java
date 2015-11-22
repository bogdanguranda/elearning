package thecerealkillers.elearning.controller;

import thecerealkillers.elearning.model.Comment;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CommentController {

    @RequestMapping(value = "/comments/add/{owner}/{threadTitle}", method = RequestMethod.POST)
    ResponseEntity createComment(
            @RequestParam(value = "message", required = true) String message,
            @PathVariable("owner") String owner,
            @PathVariable("threadTitle") String threadTitle);

    @RequestMapping(value = "/comments/owner", method = RequestMethod.POST)
    ResponseEntity<Comment> getCommentByOwnerAndTimeStamp(@RequestBody Comment comment);

    @RequestMapping(value = "/comments/thread/{threadTitle}", method = RequestMethod.GET)
    ResponseEntity<List<Comment>> getCommentsForThread(@PathVariable("threadTitle") String threadTitle);

    @RequestMapping(value = "/comments/update", method = RequestMethod.POST)
    ResponseEntity updateComment(@RequestBody Comment comment);

    @RequestMapping(value = "/comments/delete", method = RequestMethod.DELETE)
    ResponseEntity deleteComment(@RequestBody Comment comment);
}