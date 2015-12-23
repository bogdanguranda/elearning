package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.CoursesDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.service.CoursesService;
import thecerealkillers.elearning.service.SessionService;

import java.util.List;

@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesDAO coursesDAO;
    @Autowired
    private SessionService sessionService;

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
    public void enrollUserToCourse(String courseTitle, String username) throws ServiceException {
        try {
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
        if (!sessionService.getSessionByToken(token).equals(sessionService.getSessionByUser(username))) {
            throw new ServiceException(ServiceException.CANNOT_ENROLL_OTHER_USER);
        }
    }

    @Override
    public void checkUnEnrollmentCompatibility(String token, String username) throws ServiceException {
        if (!sessionService.getSessionByToken(token).equals(sessionService.getSessionByUser(username))) {
            throw new ServiceException(ServiceException.CANNOT_UNENROLL_OTHER_USER);
        }
    }

    @Override
    public void unEnrollUserFromCourse(String title, String username) throws ServiceException {
        try {
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
            return coursesDAO.getEnrolled(title);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }
}
