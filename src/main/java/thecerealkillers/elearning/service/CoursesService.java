package thecerealkillers.elearning.service;

import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Course;

import java.util.List;

public interface CoursesService {

    void add(Course course) throws ServiceException;

    void remove(String title) throws ServiceException;

    void update(String title, Course updatedCourse) throws ServiceException;

    Course get(String title) throws ServiceException;

    List<Course> getAll() throws ServiceException;

    void enrollUserToCourse(String title, String username) throws ServiceException;

    void checkEnrollmentCompatibility(String token, String username) throws ServiceException;

    void checkUnEnrollmentCompatibility(String token, String username) throws ServiceException;

    void unEnrollUserFromCourse(String title, String username) throws ServiceException;

    List<String> getEnrolled(String title) throws ServiceException;
}
