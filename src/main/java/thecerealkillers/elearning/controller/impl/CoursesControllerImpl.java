package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.exceptions.InvalidEnrollmentParams;
import thecerealkillers.elearning.controller.CoursesController;
import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.utilities.Constants;
import thecerealkillers.elearning.validator.Validator;
import thecerealkillers.elearning.model.AuditItem;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.model.Group;
import thecerealkillers.elearning.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * Modified by #Lucian and @Pi on 12/22/2015
 * Modifications Summary:
 * - added a call to addGroup in createCourse method
 * - added enroll function
 * - added unenroll function
 * Modified by Dani.
 */

@CrossOrigin
@RestController
public class CoursesControllerImpl implements CoursesController {

    private static final String GROUP = "GROUP_";

    @Autowired
    private CoursesService coursesService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCourses(@RequestHeader(value = "token") String token) {
        String actionName = "CoursesControllerImpl.getAllCourses";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    List<Course> courseList = coursesService.getAll();

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "", Constants.COURSES_GET_ALL, true));
                    return new ResponseEntity<>(courseList, HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "", Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
//            } catch (NotFoundException notFoundException) {
//	auditService.addEvent(new AuditItem(usernameForToken, actionName, "", notFoundException.getMessage(), false));
//                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "", serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public ResponseEntity createCourse(@RequestBody Course course,
                                       @RequestHeader(value = "token") String token) {
        String actionName = "CoursesControllerImpl.createCourse";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    Group group = new Group(GROUP + course.getTitle());
                    groupsService.addGroup(group);

                    coursesService.add(course);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, course.toString(), Constants.COURSES_CREATE, true));
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, course.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
//            } catch (NotFoundException notFoundException) {
//	auditService.addEvent(new AuditItem(usernameForToken, actionName, course.toString(), notFoundException.getMessage(), false));
//                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, course.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(value = "/courses", method = RequestMethod.DELETE)
    public ResponseEntity deleteCourse(@RequestParam(value = "title", required = true) String title,
                                       @RequestHeader(value = "token") String token) {
        String actionName = "CoursesControllerImpl.deleteCourse";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    coursesService.remove(title);
                    groupsService.removeGroup(GROUP + title);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, title, Constants.COURSES_DELETE, true));
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, title, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
//            } catch (NotFoundException notFoundException) {
//	auditService.addEvent(new AuditItem(usernameForToken, actionName, title, notFoundException.getMessage(), false));
//                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, title, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(value = "/courses/{title}", method = RequestMethod.GET)
    public ResponseEntity<?> getCourse(@PathVariable("title") String title,
                                       @RequestHeader(value = "token") String token) {
        String actionName = "CoursesControllerImpl.getCourse";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    Course course = coursesService.get(title);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, title, Constants.COURSES_GET, true));
                    return new ResponseEntity<>(course, HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, title, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
//            } catch (NotFoundException notFoundException) {
//	auditService.addEvent(new AuditItem(usernameForToken, actionName, title, notFoundException.getMessage(), false));
//                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, title, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Enroll a user to a course
     * Checks if the session specified by token is the same
     * with the session specified by username
     * Checks if user is already enrolled
     * Checks if course exists
     */
    @RequestMapping(value = "courses/enroll", method = RequestMethod.POST)
    public ResponseEntity enrollUserToCourse(@RequestHeader(value = "token") String token,
                                             @RequestParam(value = "title", required = true) String title,
                                             @RequestParam(value = "username", required = true) String username) {
        String actionName = "CoursesControllerImpl.enrollUserToCourse";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    Validator.validateEnrollment(title, username);
                    coursesService.checkEnrollmentCompatibility(token, username);
                    coursesService.enrollUserToCourse(title, username);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title +
                            " | Username = " + username, Constants.COURSES_ENROLL, true));
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title +
                            " | Username = " + username, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title +
                        " | Username = " + username, notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title +
                        " | Username = " + username, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (InvalidEnrollmentParams invalidEnrollmentParams) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title +
                        " | Username = " + username, invalidEnrollmentParams.getMessage(), false));
                return new ResponseEntity<>(invalidEnrollmentParams.getMessage(), HttpStatus.CONFLICT);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Checks if the session specified by token is the same
     * with the session specified by username
     * Checks if user is already unenrolled
     * Checks if course exists
     */
    @RequestMapping(value = "courses/unenroll", method = RequestMethod.POST)
    public ResponseEntity unEnrollUserFromCourse(@RequestHeader(value = "token") String token,
                                                 @RequestParam(value = "title", required = true) String title,
                                                 @RequestParam(value = "username", required = true) String username) {
        String actionName = "CoursesControllerImpl.unEnrollUserFromCourse";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    Validator.validateEnrollment(title, username);
                    coursesService.checkUnEnrollmentCompatibility(token, username);
                    coursesService.unEnrollUserFromCourse(title, username);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title +
                            " | Username = " + username, Constants.COURSES_WITHDRAW, true));
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title +
                            " | Username = " + username, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title +
                        " | Username = " + username, notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title +
                        " | Username = " + username, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (InvalidEnrollmentParams invalidEnrollmentParams) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Title = " + title +
                        " | Username = " + username, invalidEnrollmentParams.getMessage(), false));
                return new ResponseEntity<>(invalidEnrollmentParams.getMessage(), HttpStatus.CONFLICT);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Get users enrolled to course
     */
    @RequestMapping(value = "/courses/{title}/users", method = RequestMethod.GET)
    public ResponseEntity<?> getEnrolledUsers(@RequestHeader(value = "token") String token,
                                              @PathVariable("title") String title) {
        String actionName = "CoursesControllerImpl.getEnrolledUsers";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    List<String> users = coursesService.getEnrolled(title);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, title, Constants.COURSES_GET_ENROLLED, true));
                    return new ResponseEntity<>(users, HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, title, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
//            } catch (NotFoundException notFoundException) {
//	auditService.addEvent(new AuditItem(usernameForToken, actionName, title, notFoundException.getMessage(), false));
//                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, title, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            } catch (NotFoundException e) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, title, e.getMessage(), false));
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
