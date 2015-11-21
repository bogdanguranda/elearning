package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.CoursesController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.service.CoursesService;
import thecerealkillers.elearning.service.SessionService;

import java.util.List;

@RestController
@CrossOrigin
public class CoursesControllerImpl implements CoursesController{

    @Autowired
    private CoursesService coursesService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAllCourses(@RequestHeader(value="token") String token) {
        try {
            sessionService.getSessionByToken(token);

            List<Course> courseList = coursesService.getAll();
            return new ResponseEntity<>(courseList, HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public ResponseEntity createCourse(@RequestBody Course course, @RequestHeader(value="token") String token) {
        try {
            sessionService.getSessionByToken(token);

            coursesService.add(course);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/courses", method = RequestMethod.DELETE)
    public ResponseEntity deleteCourse(@RequestParam(value="title", required=true) String title,
                                       @RequestHeader(value="token") String token) {
        try {
            sessionService.getSessionByToken(token);

            coursesService.remove(title);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/courses/{title}", method = RequestMethod.GET)
    public ResponseEntity<Course> getCourse(@PathVariable("title") String title, @RequestHeader(value="token") String token) {
        try {
            sessionService.getSessionByToken(token);

            Course course = coursesService.get(title);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
