package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.controller.MessagesController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.MessageService;
import thecerealkillers.elearning.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * Created by Lucian on 10.11.2015.
 * Modified by Dani
 */

@RestController
@CrossOrigin
public class MessageControllerImpl implements MessagesController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ResponseEntity createMessage(@RequestBody Message message, @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("MessageControllerImpl.createMessage", crtUserRole)) {
                    messageService.add(message);
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

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMessages(@RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("MessageControllerImpl.getAllMessages", crtUserRole)) {
                    List<Message> messageList = messageService.getAll();
                    return new ResponseEntity<>(messageList, HttpStatus.OK);
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

    @RequestMapping(value = "/messages/{senderUsername}/{receiverUsername}", method = RequestMethod.GET)
    public ResponseEntity<?> getMessagesBetweenUsers(@PathVariable("senderUsername") String senderUsername,
                                                     @PathVariable("receiverUsername") String receiverUsername,
                                                     @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("MessageControllerImpl.getMessagesBetweenUsers", crtUserRole)) {
                    List<Message> messagesBetweenUsers = messageService.getMessagesBetweenUsers(senderUsername, receiverUsername);
                    return new ResponseEntity<>(messagesBetweenUsers, HttpStatus.ACCEPTED);
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

    @RequestMapping(value = "/messages/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getMessagesByUser(@PathVariable("username") String username,
                                               @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("MessageControllerImpl.getMessagesByUser", crtUserRole)) {
                    List<Message> messagesByUser = messageService.getMessagesByUser(username);
                    return new ResponseEntity<>(messagesByUser, HttpStatus.ACCEPTED);
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

    @RequestMapping(value = "/messages", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserMessages(@RequestParam(value = "username", required = true) String
                                                     username,
                                             @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("MessageControllerImpl.deleteUserMessages", crtUserRole)) {
                    messageService.delete(username);
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
