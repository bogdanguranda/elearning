package thecerealkillers.elearning.service;

import com.sun.org.apache.xpath.internal.operations.Mod;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Module;

import java.util.List;

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

    /**
     * Deletes the module @module from DB.
     * @param module
     * @throws ServiceException if DAOException encountered.
     */
    void deleteModule(Module module) throws ServiceException;

    /**
     * Returns a list with all the stored modules.
     * @return a list with all the modules.
     * @throws ServiceException if DAOException encountered.
     */
    List<Module> getAll() throws ServiceException;
}
