package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Course;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CoursesDAO {

    /**
     * Adds course @course to DB.
     *
     * @throws DAOException if DB problems / etc.
     */
    void add(Course course) throws DAOException;

    /**
     * Removes course with title @title.
     *
     * @throws DAOException if DB problems / etc.
     */
    void remove(String title) throws DAOException;

    /**
     * NOT IMPLEMENTED YET
     *
     * @throws DAOException
     */
    void update(String title, Course updatedCourse) throws DAOException;

    /**
     * Retrieves the course with title @title.
     *
     * @return The course with title @title.
     * @throws DAOException if the course is inexistent / DB problems / etc.
     */
    Course get(String title) throws DAOException;

    /**
     * Retrieves a list of all courses.
     *
     * @return a list of all courses.
     * @throws DAOException if DB problems / etc.
     */
    List<Course> getAll() throws DAOException;

    /**
     * Checks if the course @course is existent in DB.
     *
     * @return true if it exists, false else.
     * @throws DAOException if DB problems.
     */
    boolean isCourseExistent(Course course) throws DAOException;

    /**
     * Enrolls an user.
     *
     * @throws DAOException
     */
    void enrollUser(String courseTitle, String username) throws DAOException;

    /**
     * Checks if user is enrolled.
     *
     * @throws DAOException
     */
    boolean userIsEnrolled(String courseTitle, String username) throws DAOException;

    /**
     * Un enrolls an user.
     *
     * @throws DAOException
     */
    void unEnrollUser(String title, String username) throws DAOException;

    /**
     * Gets a list of enrolled users.
     *
     * @throws DAOException
     */
    List<String> getEnrolled(String title) throws DAOException;

    boolean userIsOwner(String usernameForToken, Course course) throws DAOException;
}
