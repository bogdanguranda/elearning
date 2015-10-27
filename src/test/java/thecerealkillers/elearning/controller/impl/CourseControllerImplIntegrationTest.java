package thecerealkillers.elearning.controller.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import thecerealkillers.elearning.controller.CoursesControllerImpl;
import thecerealkillers.elearning.model.Course;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml"})
public class CourseControllerImplIntegrationTest {

//    @Autowired(required = true)
//    private CoursesController coursesController = new CoursesControllerImpl();
//
//    @Test
//    public void getCourseTest() {
//        Course course = new Course("titlu", "ceva", "ceva", "altceva", "altceva");
//        coursesController.createCourse(course);
//
//        ResponseEntity<Course> responseEntity = coursesController.getCourse("titlu");
//
//        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        Assert.assertEquals(course, responseEntity.getBody());
//    }
}
