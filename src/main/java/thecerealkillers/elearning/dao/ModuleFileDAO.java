package thecerealkillers.elearning.dao;

import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.ModuleFile;

/**
 * Created by cuvidk on 12/29/2015.
 */
public interface ModuleFileDAO {
    /**
     * Stores the file @file in DB.
     * @param file
     * @throws DAOException if DB error.
     */
    void storeFile(ModuleFile file) throws DAOException;
}
