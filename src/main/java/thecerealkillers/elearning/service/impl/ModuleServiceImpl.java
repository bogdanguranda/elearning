package thecerealkillers.elearning.service.impl;

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
public class ModuleServiceImpl implements ModuleService {
    private ModuleDAO moduleDAO;
    private CoursesDAO coursesDAO;

    @Override
    public void storeModule(Module module) throws ServiceException {
        try {
            Course associatedCourse = new Course();
            associatedCourse.setTitle(module.getCourse());

            if (coursesDAO.isCourseExistent(associatedCourse)) {
                if (moduleDAO.isModuleExistent(module)) {
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
            //I don't make any checks here since the DELETE cmd
            //won't fail for any params passed. From client-side
            //the user will not be able to make a delete request for
            //an innexistent module, and as for the ones that make a
            //request from cURL for example, well their request won't
            //fail neither and this way i'm exposing less security holes.
            //GG. Story of my life. KTHXBYE.
            moduleDAO.deleteModule(module);
        } catch (DAOException daoException) {
            throw new ServiceException(daoException.getMessage());
        }
    }

    @Override
    public List<Module> getAll() throws ServiceException {
        try {
            return moduleDAO.getAll();
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
}