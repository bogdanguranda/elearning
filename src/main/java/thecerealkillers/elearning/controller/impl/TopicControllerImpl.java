package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.controller.TopicController;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.AuditService;
import thecerealkillers.elearning.service.TopicService;
import thecerealkillers.elearning.utilities.Constants;
import thecerealkillers.elearning.model.AuditItem;
import thecerealkillers.elearning.model.Topic;

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
public class TopicControllerImpl implements TopicController {

    @Autowired
    private AuditService auditService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PermissionService permissionService;


    @Override
    @RequestMapping(value = "/topics", method = RequestMethod.POST)
    public ResponseEntity createTopic(@RequestBody Topic newTopic,
                                      @RequestHeader(value = "token") String token) {
        String actionName = "TopicControllerImpl.createTopic";

        try {
            if (!sessionService.isSessionActive(token)) {
                auditService.addEvent(new AuditItem(Constants.USERNAME_OF_MOCK_USER_ACCOUNT, actionName, newTopic.toString(), Constants.SESSION_EXPIRED, false));
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    topicService.add(newTopic);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, newTopic.toString(), Constants.TOPIC_CREATE_TOPIC, true));
                    return new ResponseEntity(HttpStatus.CREATED);

                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, newTopic.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, newTopic.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTopics(@RequestHeader(value = "token") String token) {
        String actionName = "TopicControllerImpl.getAllTopics";

        try {
            if (!sessionService.isSessionActive(token)) {
                auditService.addEvent(new AuditItem(Constants.USERNAME_OF_MOCK_USER_ACCOUNT, actionName, "", Constants.SESSION_EXPIRED, false));
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    List<Topic> topicList = topicService.getAll();

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "", Constants.TOPIC_GET_ALL, true));
                    return new ResponseEntity<>(topicList, HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "", Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "", serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "", notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/topics/{title}", method = RequestMethod.GET)
    public ResponseEntity<?> getTopicByTitle(@PathVariable("title") String title,
                                             @RequestHeader(value = "token") String token) {
        String actionName = "TopicControllerImpl.getTopicByTitle";

        try {
            if (!sessionService.isSessionActive(token)) {
                auditService.addEvent(new AuditItem(Constants.USERNAME_OF_MOCK_USER_ACCOUNT, actionName, title, Constants.SESSION_EXPIRED, false));
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    Topic topic = topicService.get(title);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, title, Constants.TOPIC_GET_BY_TITLE, true));
                    return new ResponseEntity<>(topic, HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, title, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, title, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, title, notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/topics/{title}", method = RequestMethod.POST)
    public ResponseEntity updateTopic(@PathVariable("title") String title,
                                      @RequestBody Topic newTopic,
                                      @RequestHeader(value = "token") String token) {
        String actionName = "TopicControllerImpl.updateTopic";

        try {
            if (!sessionService.isSessionActive(token)) {
                auditService.addEvent(new AuditItem(Constants.USERNAME_OF_MOCK_USER_ACCOUNT, actionName, "Title = " + title + " | New data = " +
                        newTopic.toString(), Constants.SESSION_EXPIRED, false));
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    topicService.update(title, newTopic);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title + " | New data = " +
                            newTopic.toString(), Constants.TOPIC_UPDATE, true));
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title + " | New data = " +
                            newTopic.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title + " | New data = " +
                        newTopic.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title + " | New data = " +
                        newTopic.toString(), notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/topics", method = RequestMethod.DELETE)
    public ResponseEntity deleteTopicByTitle(@RequestBody Topic topicToDelete,
                                             @RequestHeader(value = "token") String token) {
        String actionName = "TopicControllerImpl.deleteTopicByTitle";

        try {
            if (!sessionService.isSessionActive(token)) {
                auditService.addEvent(new AuditItem(Constants.USERNAME_OF_MOCK_USER_ACCOUNT, actionName, topicToDelete.toString(), Constants.SESSION_EXPIRED, false));
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    topicService.delete(topicToDelete.getTitle());

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, topicToDelete.toString(), Constants.TOPIC_DELETE, true));
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, topicToDelete.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, topicToDelete.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, topicToDelete.toString(), notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}