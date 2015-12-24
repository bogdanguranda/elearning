package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.CoursesDAO;
import thecerealkillers.elearning.dao.ModuleDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.model.Module;
import thecerealkillers.elearning.service.ModuleService;

import java.util.List;

/**
 * Created by cuvidk on 12/22/2015.
 */
@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleDAO moduleDAO;
    @Autowired
    private CoursesDAO coursesDAO;

    @Override
    public void storeModule(Module module) throws ServiceException {
        try {
            Course associatedCourse = new Course();
            associatedCourse.setTitle(module.getCourse());

            if (coursesDAO.isCourseExistent(associatedCourse)) {
                if (!moduleDAO.isModuleExistent(module)) {
                    moduleDAO.storeModule(module);
                } else {
                    throw new ServiceException(ServiceException.FAILED_MODULE_ALREADY_EXISTS);
                }
            } else {
                throw new ServiceException(ServiceException.FAILED_COURSE_INNEXISTENT);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(daoException.getMessage());
        }
    }

    @Override
    public void deleteModule(Module module) throws ServiceException {
        try {
            if (moduleDAO.isModuleExistent(module)) {
                moduleDAO.deleteModule(module);
            } else {
                throw new ServiceException(ServiceException.FAILED_MODULE_INNEXISTENT);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(daoException.getMessage());
        }
    }

    @Override
    public List<Module> getAll(String course) throws ServiceException {
        try {
            Course associatedCourse = new Course();
            associatedCourse.setTitle(course);

            if (coursesDAO.isCourseExistent(associatedCourse)) {
                return moduleDAO.getAll(course);
            } else {
                throw new ServiceException(ServiceException.FAILED_COURSE_INNEXISTENT);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(daoException.getMessage());
        }
    }

    @Override
    public Module get(String title, String course) throws ServiceException {
        try {
            Module module = new Module();
            module.setTitle(title);
            module.setCourse(course);

            if (moduleDAO.isModuleExistent(module)) {
                return moduleDAO.get(title, course);
            } else {
                throw new ServiceException(ServiceException.FAILED_MODULE_INNEXISTENT);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(daoException.getMessage());
        }
    }

    @Override
    public void update(Module module, String newTitle) throws ServiceException {
        try {
            if (moduleDAO.isModuleExistent(module)) {
                moduleDAO.update(module, newTitle);
            } else {
                throw new ServiceException(ServiceException.FAILED_MODULE_INNEXISTENT);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(daoException.getMessage());
        }
    }
}