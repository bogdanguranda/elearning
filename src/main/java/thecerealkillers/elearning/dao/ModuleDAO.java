package thecerealkillers.elearning.dao;

import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Module;

/**
 * Created by cuvidk on 12/22/2015.
 */
public interface ModuleDAO {
    /**
     * Stores the module @module in the DB.
     * @param module
     * @throws DAOException if DB error.
     */
    void storeModule(Module module) throws DAOException;

    /**
     * Removes the module @module from DB.
     * @param module
     * @throws DAOException if DB error.
     */
    void deleteModule(Module module) throws DAOException;
}
