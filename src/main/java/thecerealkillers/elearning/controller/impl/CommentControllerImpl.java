package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.controller.CommentController;
import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.ForumThreadIdentifier;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.model.CommentUpdateInfo;
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
    private PermissionService permissionService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SessionService sessionService;


    @Override
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public ResponseEntity createComment(@RequestBody Comment comment,
                                        @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CommentControllerImpl.createComment", crtUserRole)) {

                    commentService.addComment(comment);

                    return new ResponseEntity(HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/comments/{commentID}", method = RequestMethod.GET)
    public ResponseEntity<?> getComment(@PathVariable("commentID") Integer commentID,
                                        @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CommentControllerImpl.getComment", crtUserRole)) {
                    Comment com = commentService.getComment(commentID);

                    return new ResponseEntity<>(com, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/comments/thread", method = RequestMethod.POST)
    public ResponseEntity<?> getCommentsInThread(@RequestBody ForumThreadIdentifier threadInfo,
                                                 @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CommentControllerImpl.getCommentsInThread", crtUserRole)) {
                    List<Comment> commentList = commentService.getCommentsInThread(threadInfo.getTitle(), threadInfo.getTopic());

                    return new ResponseEntity<>(commentList, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/comments/update", method = RequestMethod.POST)
    public ResponseEntity updateComment(@RequestBody CommentUpdateInfo commentUpdateInfo,
                                        @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CommentControllerImpl.updateComment", crtUserRole)) {
                    commentService.updateComment(commentUpdateInfo.getCommentID(), commentUpdateInfo.getNewMessage());

                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/comments/{commentID}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable("commentID") Integer commentID,
                                        @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);
                String crtUsername = sessionService.getUsernameByToken(token);
                Comment comment = commentService.getComment(commentID);

                if (crtUsername.compareTo(comment.getOwner()) == 0) {
                    if (permissionService.isOperationAvailable("CommentControllerImpl.deleteComment", crtUserRole)) {
                        commentService.deleteComment(commentID);

                        return new ResponseEntity(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                    }
                } else {
                    return new ResponseEntity<>("Nice try :P.", HttpStatus.UNPROCESSABLE_ENTITY);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}