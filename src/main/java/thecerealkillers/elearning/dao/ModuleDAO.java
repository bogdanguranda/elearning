package thecerealkillers.elearning.dao;

import com.sun.org.apache.xpath.internal.operations.Mod;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Module;

import java.util.List;

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

    /**
     * Returns all the modules from the DB.
     * @return A list of Module.
     * @throws DAOException if DB error.
     */
    List<Module> getAll() throws DAOException;

    /**
     * Returns the module with the title @title that is
     * associated with course @course.
     * @param title
     * @param course
     * @return The corresponding module.
     * @throws DAOException if DB error.
     */
    Module get(String title, String course) throws DAOException;
}
