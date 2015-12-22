package thecerealkillers.elearning.service;

import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Module;

/**
 * Created by cuvidk on 12/22/2015.
 */
public interface ModuleService {
    /**
     * Stores the module @module in DB.
     * @param module
     * @throws ServiceException if @module already exists in DB /
     * if the course associated to @module doesn't exist in DB /
     * if DAOException encountered.
     */
    void storeModule(Module module) throws ServiceException;
}
