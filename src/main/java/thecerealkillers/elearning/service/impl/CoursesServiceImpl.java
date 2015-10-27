package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import thecerealkillers.elearning.dao.CoursesDAO;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.service.CoursesService;

import java.util.List;

@Controller
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesDAO coursesDAO;

    @Override
    public void add(Course course) {
        coursesDAO.add(course);
    }

    @Override
    public void remove(String title) {
        coursesDAO.remove(title);
    }

    @Override
    public void update(String title, Course updatedCourse) {

    }

    @Override
    public Course get(String title) {
        return coursesDAO.get(title);
    }

    @Override
    public List<Course> getAll() {
        return coursesDAO.getAll();
    }
}
