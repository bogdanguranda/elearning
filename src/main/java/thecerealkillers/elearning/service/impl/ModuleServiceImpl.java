package thecerealkillers.elearning.service.impl;

import thecerealkillers.elearning.dao.CoursesDAO;
import thecerealkillers.elearning.dao.ModuleDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Course;
import thecerealkillers.elearning.model.Module;
import thecerealkillers.elearning.service.ModuleService;

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
                throw new ServiceException(ServiceException.FAILED_COURSE_INEXISTENT);
            }
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }
}