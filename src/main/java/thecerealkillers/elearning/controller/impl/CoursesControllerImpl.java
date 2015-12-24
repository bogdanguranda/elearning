package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.exceptions.InvalidEnrollmentParams;
import thecerealkillers.elearning.controller.CoursesController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.CoursesService;
import thecerealkillers.elearning.service.GroupsService;
import thecerealkillers.elearning.validator.Validator;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.model.Group;

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
    private PermissionService permissionService;


    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCourses(@RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CoursesControllerImpl.getAllCourses", crtUserRole)) {
                    List<Course> courseList = coursesService.getAll();

                    return new ResponseEntity<>(courseList, HttpStatus.OK);
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

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public ResponseEntity createCourse(@RequestBody Course course, @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CoursesControllerImpl.createCourse", crtUserRole)) {
                    Group group = new Group(GROUP + course.getTitle());
                    groupsService.addGroup(group);

                    coursesService.add(course);

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

    @RequestMapping(value = "/courses", method = RequestMethod.DELETE)
    public ResponseEntity deleteCourse(@RequestParam(value = "title", required = true) String title,
                                       @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CoursesControllerImpl.deleteCourse", crtUserRole)) {
                    coursesService.remove(title);
                    groupsService.removeGroup(GROUP + title);

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

    @RequestMapping(value = "/courses/{title}", method = RequestMethod.GET)
    public ResponseEntity<?> getCourse(@PathVariable("title") String title, @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CoursesControllerImpl.getCourse", crtUserRole)) {
                    Course course = coursesService.get(title);

                    return new ResponseEntity<>(course, HttpStatus.OK);
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
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CoursesControllerImpl.enrollUserToCourse", crtUserRole)) {
                    Validator.validateEnrollment(title, username);
                    coursesService.checkEnrollmentCompatibility(token, username);
                    coursesService.enrollUserToCourse(title, username);

                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (InvalidEnrollmentParams invalidEnrollmentParams) {
            return new ResponseEntity<>(invalidEnrollmentParams.getMessage(), HttpStatus.CONFLICT);
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
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CoursesControllerImpl.unEnrollUserFromCourse", crtUserRole)) {
                    Validator.validateEnrollment(title, username);
                    coursesService.checkUnEnrollmentCompatibility(token, username);
                    coursesService.unEnrollUserFromCourse(title, username);

                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (InvalidEnrollmentParams invalidEnrollmentParams) {
            return new ResponseEntity<>(invalidEnrollmentParams.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Get users enrolled to course
     */
    @RequestMapping(value = "/courses/{title}/users", method = RequestMethod.GET)
    public ResponseEntity<?> getEnrolledUsers(@RequestHeader(value = "token") String token,
                                              @PathVariable("title") String title) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("CoursesControllerImpl.getEnrolledUsers", crtUserRole)) {
                    List<String> users = coursesService.getEnrolled(title);

                    return new ResponseEntity<>(users, HttpStatus.OK);
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
