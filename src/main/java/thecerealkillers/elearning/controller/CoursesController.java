package thecerealkillers.elearning.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.Course;


/**
 * Modified by Dani.
 */
public interface CoursesController {

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    ResponseEntity<?> getAllCourses(@RequestHeader(value = "token") String token);

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    ResponseEntity createCourse(@RequestBody Course course, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/courses", method = RequestMethod.DELETE)
    ResponseEntity deleteCourse(@RequestParam(value = "title", required = true) String title,
                                @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/courses/{title}", method = RequestMethod.GET)
    ResponseEntity<?> getCourse(@PathVariable("title") String title, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "courses/enroll", method = RequestMethod.POST)
    ResponseEntity enrollUserToCourse(@RequestHeader(value = "token") String token,
                                      @RequestParam(value = "title", required = true) String title,
                                      @RequestParam(value = "username", required = true) String username);

    @RequestMapping(value = "courses/unenroll", method = RequestMethod.POST)
    ResponseEntity unEnrollUserFromCourse(@RequestHeader(value = "token") String token,
                                          @RequestParam(value = "title", required = true) String title,
                                          @RequestParam(value = "username", required = true) String username);

    @RequestMapping(value = "/courses/{title}/users", method = RequestMethod.GET)
    ResponseEntity<?> getEnrolledUsers(@RequestHeader(value = "token") String token,
                                       @PathVariable("title") String title);

    @RequestMapping(value = "/courses/{user}/titles", method = RequestMethod.GET)
    ResponseEntity<?> getAttendedCourses(@RequestHeader(value = "token") String token,
                                         @PathVariable("user") String user);
}
