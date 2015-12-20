package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.CoursesDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.service.CoursesService;

import java.util.List;

@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesDAO coursesDAO;

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
}
