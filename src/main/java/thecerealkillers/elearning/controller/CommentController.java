package thecerealkillers.elearning.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import thecerealkillers.elearning.model.Comment;

import java.util.Date;
import java.util.List;

public interface CommentController {
    @RequestMapping(value = "/comments/add/{owner}/{threadTitle}", method = RequestMethod.POST)
    ResponseEntity createThread(
            @RequestParam(value = "message", required = true) String message,
            @PathVariable("owner") String owner,
            @PathVariable("threadTitle") String threadTitle);

    @RequestMapping(value = "/comments/owner/{owner}", method = RequestMethod.GET)
    ResponseEntity<Comment> getCommentByOwnerAndTimeStamp(
            @PathVariable("owner") String owner,
            @RequestParam(value = "timeStamp", required = true) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date timeStamp);

    @RequestMapping(value = "/comments/thread/{threadTitle}", method = RequestMethod.GET)
    ResponseEntity<List<Comment>> getCommentsForThread(
            @PathVariable("threadTitle") String threadTitle);

    @RequestMapping(value = "/comments/update/{owner}/{timeStamp}", method = RequestMethod.POST)
    ResponseEntity updateComment(
            @PathVariable("owner") String owner,
            @PathVariable("timeStamp") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date timeStamp,
            @RequestParam(value = "message", required = true) String message);

    @RequestMapping(value = "/comments/delete/{owner}/{timeStamp}", method = RequestMethod.DELETE)
    ResponseEntity deleteComment(
            @PathVariable("owner") String owner,
            @PathVariable("timeStamp") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date timeStamp);
}