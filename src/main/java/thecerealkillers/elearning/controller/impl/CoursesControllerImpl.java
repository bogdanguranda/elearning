package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.CoursesController;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.service.CoursesService;

import java.util.List;

@RestController
@CrossOrigin
public class CoursesControllerImpl implements CoursesController{

    @Autowired
    private CoursesService coursesService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courseList = coursesService.getAll();

        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public ResponseEntity createCourse(@RequestBody Course course) {
        coursesService.add(course);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.DELETE)
    public ResponseEntity deleteCourse(@RequestParam(value="title", required=true) String title) {
        coursesService.remove(title);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/courses/{title}", method = RequestMethod.GET)
    public ResponseEntity<Course> getCourse(@PathVariable("title") String title) {
        Course course = coursesService.get(title);

        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}
