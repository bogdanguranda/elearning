package thecerealkillers.elearning.service;

import thecerealkillers.elearning.model.Course;

import java.util.List;

public interface CoursesService {

    void add(Course course);

    void remove(String title);

    void update(String title, Course updatedCourse);

    Course get(String title);

    List<Course> getAll();
}
