package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.CoursesDAO;
import thecerealkillers.elearning.dao.ModuleDAO;
import thecerealkillers.elearning.dao.ModuleFileDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.model.Module;
import thecerealkillers.elearning.model.ModuleFile;
import thecerealkillers.elearning.service.ModuleFileService;

import java.util.List;

/**
 * Created by cuvidk on 12/29/2015.
 */
@Service
public class ModuleFileServiceImpl implements ModuleFileService {
    @Autowired
    private ModuleDAO moduleDAO;
    @Autowired
    private CoursesDAO coursesDAO;
    @Autowired
    private ModuleFileDAO moduleFileDAO;

    @Override
    public void storeFile(ModuleFile file) throws ServiceException {
        try {
            Course course = new Course();
            course.setTitle(file.getAssociatedCourse());
            if (coursesDAO.isCourseExistent(course)) {
                Module module = new Module();
                module.setTitle(file.getAssociatedModule());
                module.setCourse(file.getAssociatedCourse());
                if (moduleDAO.isModuleExistent(module)) {
                    if (!moduleFileDAO.isModuleFileExistent(file)) {
                        moduleFileDAO.storeFile(file);
                    } else {
                        throw new ServiceException(ServiceException.FAILED_MODULE_FILE_ALREADY_EXISTS);
                    }
                } else {
                    throw new ServiceException(ServiceException.FAILED_MODULE_INNEXISTENT);
                }
            } else {
                throw new ServiceException(ServiceException.FAILED_COURSE_INNEXISTENT);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(daoException.getMessage());
        }
    }

    @Override
    public void deleteFile(ModuleFile file) throws ServiceException {
        try {
            if (moduleFileDAO.isModuleFileExistent(file)) {
                moduleFileDAO.deleteFile(file);
            } else {
                throw new ServiceException(ServiceException.FAILED_NO_SUCH_MODULE_FILE);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(daoException.getMessage());
        }
    }

    @Override
    public ModuleFile getFile(String fileName, String associatedCourse, String associatedModule) throws ServiceException {
        try {
            ModuleFile file = new ModuleFile();
            file.setName(fileName);
            file.setAssociatedModule(associatedModule);
            file.setAssociatedCourse(associatedCourse);

            if (moduleFileDAO.isModuleFileExistent(file)) {
                return moduleFileDAO.getFile(fileName, associatedCourse, associatedModule);
            } else {
                throw new ServiceException(ServiceException.FAILED_NO_SUCH_MODULE_FILE);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(daoException.getMessage());
        }
    }

    @Override
    public List<ModuleFile> getAll(String associatedCourse, String associatedModule) throws ServiceException {
        try {
            Course course = new Course();
            course.setTitle(associatedCourse);
            if (coursesDAO.isCourseExistent(course)) {
                Module module = new Module();
                module.setTitle(associatedModule);
                module.setCourse(associatedCourse);
                if (moduleDAO.isModuleExistent(module)) {
                    return moduleFileDAO.getAll(associatedCourse, associatedModule);
                } else {
                    throw new ServiceException(ServiceException.FAILED_MODULE_INNEXISTENT);
                }
            } else {
                throw new ServiceException(ServiceException.FAILED_COURSE_INNEXISTENT);
            }
        } catch (DAOException daoException) {
                throw new ServiceException(daoException.getMessage());
        }
    }
}
