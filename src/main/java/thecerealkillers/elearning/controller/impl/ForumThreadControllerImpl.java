package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.controller.ForumThreadController;
import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.ForumThreadIdentifier;
import thecerealkillers.elearning.service.ForumThreadService;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.model.ForumThread;


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
public class ForumThreadControllerImpl implements ForumThreadController {

    @Autowired
    private ForumThreadService forumThreadService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PermissionService permissionService;


    @Override
    @RequestMapping(value = "/threads/create", method = RequestMethod.POST)
    public ResponseEntity createThread(@RequestBody ForumThread newThread,
                                       @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ForumThreadControllerImpl.createThread", crtUserRole)) {
                    forumThreadService.add(newThread);

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
    @RequestMapping(value = "/thread", method = RequestMethod.POST)
    public ResponseEntity<?> getThread(@RequestBody ForumThreadIdentifier threadIdentifier,
                                       @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ForumThreadControllerImpl.getThread", crtUserRole)) {
                    ForumThread thread = forumThreadService.getThread(threadIdentifier.getTitle(), threadIdentifier.getTopic());

                    return new ResponseEntity<>(thread, HttpStatus.OK);
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
    @RequestMapping(value = "/threads/topic/{topicTitle}", method = RequestMethod.GET)
    public ResponseEntity<?> getThreadsInTopic(@PathVariable("topicTitle") String topicTitle,
                                               @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ForumThreadControllerImpl.getThreadsInTopic", crtUserRole)) {
                    List<ForumThread> threadList = forumThreadService.getThreadsInTopic(topicTitle);

                    return new ResponseEntity<>(threadList, HttpStatus.OK);
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
    @RequestMapping(value = "/threads/owner/{threadOwner}", method = RequestMethod.GET)
    public ResponseEntity<?> getThreadsOwnedByUser(@PathVariable("threadOwner") String userName,
                                                   @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ForumThreadControllerImpl.getThreadsOwnedByUser", crtUserRole)) {
                    List<ForumThread> threadList = forumThreadService.getThreadsOwnedByUser(userName);

                    return new ResponseEntity<>(threadList, HttpStatus.OK);
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
    @RequestMapping(value = "/threads/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ForumThreadControllerImpl.getAll", crtUserRole)) {
                    List<ForumThread> threadList = forumThreadService.getAll();

                    return new ResponseEntity<>(threadList, HttpStatus.OK);
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
    @RequestMapping(value = "/threads/update/{newTitle}", method = RequestMethod.POST)
    public ResponseEntity updateThread(@PathVariable("newTitle") String newTitle,
                                       @RequestBody ForumThread newThread,
                                       @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ForumThreadControllerImpl.updateThread", crtUserRole)) {
                    forumThreadService.updateThread(newTitle, newThread);

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
    @RequestMapping(value = "/threads", method = RequestMethod.DELETE)
    public ResponseEntity deleteThread(@RequestBody ForumThreadIdentifier threadToDelete,
                                       @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ForumThreadControllerImpl.deleteThread", crtUserRole)) {
                    forumThreadService.deleteThread(threadToDelete);

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
}
