package thecerealkillers.elearning.dao;

import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Module;

import java.util.List;

/**
 * Created by cuvidk on 12/22/2015.
 */
@Repository
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

    /**
     * Renames the module @module
     * to @newTitle.
     * @param module
     * @param newTitle
     * @throws DAOException if DB error.
     */
    void update(Module module, String newTitle) throws DAOException;

    /**
     * Checks to see if @module already exists
     * in DB.
     * @param module
     * @return true if it exists, false else
     * @throws DAOException if DB error.
     */
    boolean isModuleExistent(Module module) throws DAOException;
}
