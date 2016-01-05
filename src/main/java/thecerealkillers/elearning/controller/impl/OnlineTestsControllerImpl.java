package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.OnlineTestsController;
import thecerealkillers.elearning.exceptions.InvalidOnlineTestException;
import thecerealkillers.elearning.exceptions.InvalidUsernameException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.AuditItem;
import thecerealkillers.elearning.model.OnlineTest;
import thecerealkillers.elearning.model.Question;
import thecerealkillers.elearning.model.UserPoints;
import thecerealkillers.elearning.service.*;
import thecerealkillers.elearning.utilities.Constants;
import thecerealkillers.elearning.validator.OnlineTestValidator;
import thecerealkillers.elearning.validator.Validator;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 29.12.2015.
 */

@RestController
@CrossOrigin
public class OnlineTestsControllerImpl implements OnlineTestsController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private OnlineTestsService onlineTestsService;
    @Autowired
    private CoursesService coursesService;
    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(value = "/professor/tests/create", method = RequestMethod.POST)
    public ResponseEntity createTest(@RequestHeader(value = "token") String token,
                                     @RequestParam(value = "title", required = true) String title,
                                     @RequestParam(value = "course", required = true) String course,
                                     @RequestParam(value = "attempts", required = true) String attempts,
                                     @RequestBody List<Question> questionList) {

        String actionName = "OnlineTestsControllerImpl.createTest";
        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {

                    OnlineTest onlineTest = new OnlineTest(title, course, attempts, questionList);
                    OnlineTestValidator.validate(onlineTest);

                    try {
                        coursesService.userIsOwner(usernameForToken, course);
                    } catch (ServiceException ex) {
                        auditService.addEvent(new AuditItem(usernameForToken, actionName, questionList.toString(), Constants.NO_PERMISSION, false));
                        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
                    }
                    onlineTestsService.createTest(onlineTest);
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, onlineTest.toString(), Constants.ONLINE_TEST_NEW_TEST, true));
                    return new ResponseEntity<>(HttpStatus.CREATED);

                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, questionList.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, questionList.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            } catch (InvalidOnlineTestException ex) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, questionList.toString(), ex.getMessage(), false));
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/professor/tests", method = RequestMethod.DELETE)
    public ResponseEntity deleteTest(@RequestHeader(value = "token") String token,
                                     @RequestParam(value = "title") String title,
                                     @RequestParam(value = "course") String course) {
        String actionName = "OnlineTestsControllerImpl.deleteTest";
        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {

                    OnlineTestValidator.validateDeleteTestInfo(title, course);
                    coursesService.userIsOwner(usernameForToken, course);
                    onlineTestsService.deleteTest(title, course);

                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, title + course, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (InvalidOnlineTestException exeption) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, title + course, exeption.getMessage(), false));
                return new ResponseEntity<>(exeption.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, title + course, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/professor/tests/{course}/{test}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentPoints(@RequestHeader(value = "token") String token,
                                              @PathVariable(value = "course") String course,
                                              @PathVariable(value = "test") String test,
                                              @RequestParam(value = "username") String username) {
        String actionName = "OnlineTestsControllerImpl.getStudentPoints";
        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);
            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    Validator.validateUsername(username);
                    if (!userService.exists(username)) {
                        auditService.addEvent(new AuditItem(usernameForToken, actionName, username, Constants.USER_NOT_EXISTS, false));
                        return new ResponseEntity<>(Constants.USER_NOT_EXISTS, HttpStatus.NOT_FOUND);
                    }
                    coursesService.userIsOwner(usernameForToken, course);
                    List<UserPoints> userPointsList = onlineTestsService.getStudentsPoints(course, test, username);
                    if (userPointsList.size() == 0) {
                        auditService.addEvent(new AuditItem(usernameForToken, actionName, username + course + test, Constants.USER_POINTS_LIST_EMPTY, true));
                        return new ResponseEntity<>(Constants.USER_POINTS_LIST_EMPTY, HttpStatus.OK);
                    } else {
                        auditService.addEvent(new AuditItem(usernameForToken, actionName, username + course + test, Constants.USER_POINTS_LIST, true));
                        return new ResponseEntity<>(userPointsList, HttpStatus.OK);
                    }
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, username, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (InvalidUsernameException e) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, username, e.getMessage(), false));
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/{course}/tests/{test}", method = RequestMethod.GET)
    public ResponseEntity<?> getOnlineTest(@RequestHeader(value = "token") String token,
                                           @PathVariable(value = "course") String course,
                                           @PathVariable(value = "test") String test) {
        String actionName = "OnlineTestsControllerImpl.getOnlineTest";
        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);
            if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, usernameForToken + course + test, Constants.USER_POINTS_LIST, true));
                return new ResponseEntity<>(onlineTestsService.getOnlineTest(course, test, usernameForToken, userRoleForToken), HttpStatus.OK);
            } else {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, usernameForToken, Constants.NO_PERMISSION, false));
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
