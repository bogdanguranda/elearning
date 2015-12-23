package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.controller.CommentController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.CommentService;
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

    @Autowired
    private PermissionService permissionService;


    @Override
    @RequestMapping(value = "/comments/add/{owner}/{threadTitle}", method = RequestMethod.POST)
    public ResponseEntity createComment(@RequestParam(value = "message", required = true) String message,
                                        @PathVariable("owner") String owner, @PathVariable("threadTitle") String threadTitle,
                                        @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CommentControllerImpl.createComment", crtUserRole)) {

                    commentService.addComment(owner, message, threadTitle);

                    return new ResponseEntity(HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/comments/owner", method = RequestMethod.POST)
    public ResponseEntity<?> getCommentByOwnerAndTimeStamp(@RequestBody Comment comment, @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CommentControllerImpl.getCommentByOwnerAndTimeStamp", crtUserRole)) {
                    Comment com = commentService.getCommentByOwnerAndTimeStamp(comment.getOwner(), comment.getTimeStamp());

                    return new ResponseEntity<>(com, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/comments/thread/{threadTitle}", method = RequestMethod.GET)
    public ResponseEntity<?> getCommentsForThread(@PathVariable("threadTitle") String threadTitle,
                                                  @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CommentControllerImpl.getCommentsForThread", crtUserRole)) {
                    List<Comment> commentList = commentService.getCommentsForThread(threadTitle);

                    return new ResponseEntity<>(commentList, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/comments/update", method = RequestMethod.POST)
    public ResponseEntity updateComment(@RequestBody Comment comment, @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CommentControllerImpl.updateComment", crtUserRole)) {
                    commentService.updateComment(comment.getOwner(), comment.getTimeStamp(), comment.getMessage());

                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/comments/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@RequestBody Comment comment, @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CommentControllerImpl.deleteComment", crtUserRole)) {
                    commentService.deleteComment(comment.getOwner(), comment.getTimeStamp());

                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}