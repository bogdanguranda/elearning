package thecerealkillers.elearning.dao;

import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.ModuleFile;

/**
 * Created by cuvidk on 12/29/2015.
 */
@Repository
public interface ModuleFileDAO {
    /**
     * Stores the file @file in DB.
     * @param file
     * @throws DAOException if DB error.
     */
    void storeFile(ModuleFile file) throws DAOException;

    /**
     * Removes file @file from DB.
     * @param file
     * @throws DAOException if DB error.
     */
    void deleteFile(ModuleFile file) throws DAOException;

    /**
     * Checks if file is stored in DB.
     * @param file
     * @return true if it is stored already in DB, false else.
     * @throws DAOException if DB error.
     */
    boolean isModuleFileExistent(ModuleFile file) throws DAOException;
}
