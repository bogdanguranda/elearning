package thecerealkillers.elearning.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.CoursesDAO;
import thecerealkillers.elearning.dao.SessionDAO;
import thecerealkillers.elearning.dao.UserDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.service.CoursesService;
import thecerealkillers.elearning.utilities.Constants;

import java.util.List;


@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesDAO coursesDAO;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private UserDAO usersDAO;

    @Override
    public void add(Course course) throws ServiceException {
        try {
            coursesDAO.add(course);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public void remove(String title) throws ServiceException {
        try {
            coursesDAO.get(title);
            coursesDAO.remove(title);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public void update(String title, Course updatedCourse) throws ServiceException {
        throw new ServiceException("NOT IMPLEMENTED YET");
    }

    @Override
    public Course get(String title) throws ServiceException {
        try {
            return coursesDAO.get(title);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public List<Course> getAll() throws ServiceException {
        try {
            return coursesDAO.getAll();
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public void enrollUserToCourse(String courseTitle, String username) throws ServiceException, NotFoundException {
        try {
            coursesDAO.get(courseTitle);
            usersDAO.get(username);
            if (!coursesDAO.userIsEnrolled(courseTitle, username)) {
                coursesDAO.enrollUser(courseTitle, username);
            } else {
                throw new ServiceException(ServiceException.USER_ALREADY_ENROLLED);
            }
        } catch (DAOException daoEX) {
            throw new ServiceException(daoEX.getMessage());
        }
    }

    @Override
    public void checkEnrollmentCompatibility(String token, String username) throws ServiceException {
        try {
            if (!sessionDAO.getSessionByToken(token).equals(sessionDAO.getSessionByUser(username))) {
                throw new ServiceException(ServiceException.CANNOT_ENROLL_OTHER_USER);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void checkUnEnrollmentCompatibility(String token, String username) throws ServiceException {
        try {
            if (!sessionDAO.getSessionByToken(token).equals(sessionDAO.getSessionByUser(username))) {
                throw new ServiceException(ServiceException.CANNOT_UNENROLL_OTHER_USER);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void unEnrollUserFromCourse(String title, String username) throws ServiceException, NotFoundException {
        try {
            usersDAO.get(username);
            coursesDAO.get(title);
            if (coursesDAO.userIsEnrolled(title, username)) {
                coursesDAO.unEnrollUser(title, username);
            } else {
                throw new ServiceException(ServiceException.USER_NOT_ENROLLED);
            }
        } catch (DAOException daoEX) {
            throw new ServiceException(daoEX.getMessage());
        }
    }

    @Override
    public List<String> getEnrolled(String title) throws ServiceException {
        try {
            coursesDAO.get(title);
            return coursesDAO.getEnrolled(title);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public void userIsOwner(String usernameForToken, String title) throws ServiceException {
        Course course = new Course();
        course.setTitle(title);
        try {
            if (coursesDAO.isCourseExistent(course)) {
                if (!coursesDAO.userIsOwner(usernameForToken, course)) {
                    throw new ServiceException(Constants.USER_NOT_OWNER_TO_COURSE);
                }
            } else {
                throw new ServiceException(Constants.COURSE_NOT_FOUND);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}