package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.controller.CommentController;
import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.ForumThreadIdentifier;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.model.CommentUpdateInfo;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.CommentService;
import thecerealkillers.elearning.service.AuditService;
import thecerealkillers.elearning.utilities.Constants;
import thecerealkillers.elearning.model.AuditItem;
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

    @Autowired
    private AuditService auditService;


    @Override
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public ResponseEntity createComment(@RequestBody Comment comment,
                                        @RequestHeader(value = "token") String token) {
        String actionName = "CommentControllerImpl.createComment";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    commentService.addComment(comment);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, comment.toString2(), Constants.COMMENT_ADD, true));
                    return new ResponseEntity(HttpStatus.CREATED);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, comment.toString2(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, comment.toString2(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, comment.toString2(), notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/comments/{commentID}", method = RequestMethod.GET)
    public ResponseEntity<?> getComment(@PathVariable("commentID") Integer commentID,
                                        @RequestHeader(value = "token") String token) {
        String actionName = "CommentControllerImpl.getComment";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    Comment com = commentService.getComment(commentID);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, commentID.toString(), Constants.COMMENT_GET, true));
                    return new ResponseEntity<>(com, HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, commentID.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, commentID.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, commentID.toString(), notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/comments/thread", method = RequestMethod.POST)
    public ResponseEntity<?> getCommentsInThread(@RequestBody ForumThreadIdentifier threadInfo,
                                                 @RequestHeader(value = "token") String token) {
        String actionName = "CommentControllerImpl.getCommentsInThread";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    List<Comment> commentList = commentService.getCommentsInThread(threadInfo.getTitle(), threadInfo.getTopic());

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, threadInfo.toString(), Constants.COMMENT_GET_IN_THREAD, true));
                    return new ResponseEntity<>(commentList, HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, threadInfo.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, threadInfo.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, threadInfo.toString(), notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/comments/update", method = RequestMethod.POST)
    public ResponseEntity updateComment(@RequestBody CommentUpdateInfo commentUpdateInfo,
                                        @RequestHeader(value = "token") String token) {
        String actionName = "CommentControllerImpl.updateComment";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    commentService.updateComment(commentUpdateInfo.getCommentID(), commentUpdateInfo.getNewMessage());

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, commentUpdateInfo.toString(), Constants.COMMENT_UPDATE, true));
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, commentUpdateInfo.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, commentUpdateInfo.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, commentUpdateInfo.toString(), notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/comments/{commentID}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable("commentID") Integer commentID,
                                        @RequestHeader(value = "token") String token) {
        String actionName = "CommentControllerImpl.deleteComment";
        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                Comment comment = commentService.getComment(commentID);

                if (usernameForToken.compareTo(comment.getOwner()) == 0) {
                    if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                        commentService.deleteComment(commentID);

                        auditService.addEvent(new AuditItem(usernameForToken, actionName, commentID.toString(), Constants.COMMENT_DELETE, true));
                        return new ResponseEntity(HttpStatus.OK);
                    } else {
                        auditService.addEvent(new AuditItem(usernameForToken, actionName, commentID.toString(), Constants.NO_PERMISSION, false));
                        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                    }
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, commentID.toString(), Constants.TOKEN_DIFFERENT_USERNAME, false));
                    return new ResponseEntity<>(Constants.TOKEN_DIFFERENT_USERNAME, HttpStatus.UNPROCESSABLE_ENTITY);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, commentID.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, commentID.toString(), notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}