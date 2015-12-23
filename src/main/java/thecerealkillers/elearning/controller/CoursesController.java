package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.Course;


/**
 * Modified by Dani.
 */
@RestController
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
}
