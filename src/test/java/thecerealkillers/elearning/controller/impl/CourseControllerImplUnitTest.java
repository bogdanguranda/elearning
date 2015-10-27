package thecerealkillers.elearning.controller.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import thecerealkillers.elearning.controller.CoursesControllerImpl;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.service.CoursesService;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerImplUnitTest {

    @Mock
    private CoursesService coursesService;

    @InjectMocks
    private CoursesController coursesController = new CoursesControllerImpl();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCourseTest() {
        Course course = new Course("titlu", "ceva", "ceva", "altceva", "altceva");
        Mockito.when(coursesService.get("titlu")).thenReturn(course);

        ResponseEntity<Course> responseEntity = coursesController.getCourse("titlu");

        Mockito.verify(coursesService, Mockito.times(1)).get("titlu");

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(course, responseEntity.getBody());
    }
}
