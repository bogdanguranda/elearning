package thecerealkillers.elearning.dao;

import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Course;

import java.util.List;

@Repository
public interface CoursesDAO {

    /**
     * Adds course @course to DB.
     * @param course
     * @throws DAOException if DB problems / etc.
     */
    void add(Course course) throws DAOException;

    /**
     * Removes course with title @title.
     * @param title
     * @throws DAOException if DB problems / etc.
     */
    void remove(String title) throws DAOException;

    /**
     * NOT IMPLEMENTED YET
     * @param title
     * @param updatedCourse
     * @throws DAOException
     */
    void update(String title, Course updatedCourse) throws DAOException;

    /**
     * Retrieves the course with title @title.
     * @param title
     * @return The course with title @title.
     * @throws DAOException if the course is inexistent / DB problems / etc.
     */
    Course get(String title) throws DAOException;

    /**
     * Retrieves a list of all courses.
     * @return a list of all courses.
     * @throws DAOException if DB problems / etc.
     */
    List<Course> getAll() throws DAOException;
}
