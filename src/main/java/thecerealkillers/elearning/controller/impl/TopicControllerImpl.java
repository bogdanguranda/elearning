package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.controller.TopicController;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.TopicService;
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
    private TopicService topicService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PermissionService permissionService;


    @Override
    @RequestMapping(value = "/topics", method = RequestMethod.POST)
    public ResponseEntity createTopic(@RequestBody Topic newTopic,
                                      @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("TopicControllerImpl.createTopic", crtUserRole)) {
                    topicService.add(newTopic);

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
    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTopics(@RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("TopicControllerImpl.getAllTopics", crtUserRole)) {
                    List<Topic> topicList = topicService.getAll();

                    return new ResponseEntity<>(topicList, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/topics/{title}", method = RequestMethod.GET)
    public ResponseEntity<?> getTopicByTitle(@PathVariable("title") String title,
                                             @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("TopicControllerImpl.getTopicByTitle", crtUserRole)) {
                    Topic topic = topicService.get(title);

                    return new ResponseEntity<>(topic, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/topics/{title}", method = RequestMethod.POST)
    public ResponseEntity updateTopic(@PathVariable("title") String title,
                                      @RequestBody Topic newTopic,
                                      @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("TopicControllerImpl.updateTopic", crtUserRole)) {
                    topicService.update(title, newTopic);

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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/topics", method = RequestMethod.DELETE)
    public ResponseEntity deleteTopicByTitle(@RequestBody Topic newTopic,
                                             @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("TopicControllerImpl.deleteTopicByTitle", crtUserRole)) {
                    topicService.delete(newTopic.getTitle());

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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}