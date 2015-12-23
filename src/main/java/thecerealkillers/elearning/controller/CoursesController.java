package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.Course;

import java.util.List;

@RestController
public interface CoursesController {

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    ResponseEntity<List<Course>> getAllCourses(@RequestHeader(value = "token") String token);

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    ResponseEntity createCourse(@RequestBody Course course, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/courses", method = RequestMethod.DELETE)
    ResponseEntity deleteCourse(@RequestParam(value = "title", required = true) String title,
                                @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/courses/{title}", method = RequestMethod.GET)
    ResponseEntity<Course> getCourse(@PathVariable("title") String title, @RequestHeader(value = "token") String token);

    @RequestMapping(value = "courses/enroll", method = RequestMethod.POST)
    ResponseEntity enrollUserToCourse(@RequestHeader(value = "token") String token,
                                      @RequestParam(value = "title", required = true) String title,
                                      @RequestParam(value = "username", required = true) String username);

    @RequestMapping(value = "courses/unenroll", method = RequestMethod.POST)
    ResponseEntity unEnrollUserFromCourse(@RequestHeader(value = "token") String token,
                                          @RequestParam(value = "title", required = true) String title,
                                          @RequestParam(value = "username", required = true) String username);

    @RequestMapping(value = "/courses/{title}/users", method = RequestMethod.GET)
    ResponseEntity<List<String>> getEnrolledUsers(@RequestHeader(value = "token") String token,
                                                  @PathVariable("title") String title);
}
