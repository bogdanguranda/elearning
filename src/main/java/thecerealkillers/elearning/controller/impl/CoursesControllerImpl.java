package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.CoursesController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.model.Group;
import thecerealkillers.elearning.service.CoursesService;
import thecerealkillers.elearning.service.GroupsService;
import thecerealkillers.elearning.service.SessionService;

import java.util.List;

/**
 * Modified by #Lucian and @Pi on 12/22/2015
 * Modifications Summary:
 * - added a call to addGroup in createCourse method
 * - added enroll function
 * - added unenroll function
 */

@RestController
@CrossOrigin
public class CoursesControllerImpl implements CoursesController {

    private static final String GROUP = "GROUP_";
    @Autowired
    private CoursesService coursesService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private GroupsService groupsService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAllCourses(@RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            List<Course> courseList = coursesService.getAll();
            return new ResponseEntity<>(courseList, HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public ResponseEntity createCourse(@RequestBody Course course, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);
            coursesService.add(course);

            Group group = new Group(GROUP + course.getTitle());
            groupsService.addGroup(group);
            groupsService.addCourseGroup(course, group);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/courses", method = RequestMethod.DELETE)
    public ResponseEntity deleteCourse(@RequestParam(value = "title", required = true) String title,
                                       @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            coursesService.remove(title);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/courses/{title}", method = RequestMethod.GET)
    public ResponseEntity<Course> getCourse(@PathVariable("title") String title, @RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);

            Course course = coursesService.get(title);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Enroll a user to a course
     * Checks if the session specified by token is the same
     * with the session specified by username
     * Checks if user is already enrolled
     *
     * @param token
     * @param title
     * @param username
     * @return
     */
    @RequestMapping(value = "courses/enroll", method = RequestMethod.POST)
    public ResponseEntity enrollUserToCourse(@RequestHeader(value = "token") String token,
                                             @RequestParam(value = "title", required = true) String title,
                                             @RequestParam(value = "username", required = true) String username) {
        try {
            coursesService.checkEnrollmentCompatibility(token, username);
            coursesService.enrollUserToCourse(title, username);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException serviceEX) {
            return new ResponseEntity<>(serviceEX.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Checks if the session specified by token is the same
     * with the session specified by username
     * Checks if user is already unenrolled
     *
     * @param token
     * @param title
     * @param username
     * @return
     */
    @RequestMapping(value = "courses/unenroll", method = RequestMethod.POST)
    public ResponseEntity unEnrollUserFromCourse(@RequestHeader(value = "token") String token,
                                                 @RequestParam(value = "title", required = true) String title,
                                                 @RequestParam(value = "username", required = true) String username) {
        try {
            coursesService.checkUnEnrollmentCompatibility(token, username);
            coursesService.unEnrollUserFromCourse(title, username);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException serviceEX) {
            return new ResponseEntity<>(serviceEX.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get users enrolled to course
     *
     * @param token
     * @param title
     * @return
     */
    @RequestMapping(value = "/courses/{title}/users", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getEnrolledUsers(@RequestHeader(value = "token") String token,
                                                         @PathVariable("title") String title) {
        try {
            sessionService.getSessionByToken(token);

            List<String> users = coursesService.getEnrolled(title);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
