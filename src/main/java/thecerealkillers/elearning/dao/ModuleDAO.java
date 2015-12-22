package thecerealkillers.elearning.dao;

import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Module;

/**
 * Created by cuvidk on 12/22/2015.
 */
public interface ModuleDAO {
    /**
     * Stores the module @module in the db
     * @param module
     * @throws DAOException if DB error.
     */
    void storeModule(Module module) throws DAOException;
}
