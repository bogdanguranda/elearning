package thecerealkillers.elearning.dao;

import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.model.Course;

import java.util.List;

@Repository
public interface CoursesDAO {

    void add(Course course);

    void remove(String title);

    void update(String title, Course updatedCourse);

    Course get(String title);

    List<Course> getAll();
}
