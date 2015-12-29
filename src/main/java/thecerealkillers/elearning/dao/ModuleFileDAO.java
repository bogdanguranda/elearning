package thecerealkillers.elearning.dao;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Module;
import thecerealkillers.elearning.model.ModuleFile;

import java.util.List;

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
     * Return the file with the name @fileName, the assocCourse @associatedCourse
     * and the assocModule @associatedModule.
     * @param fileName
     * @param associatedCourse
     * @param associatedModule
     * @return the corresponding ModuleFile.
     * @throws DAOException if DB error.
     */
    ModuleFile getFile(String fileName, String associatedCourse, String associatedModule) throws DAOException;


    /**
     * Returns all the files with the associated course = @associatedCourse
     * and the associated module = @associatedModule (Basically, all the files
     * associated to a module).
     * @param associatedCourse
     * @param associatedModule
     * @return a list of corresponding ModuleFiles.
     * @throws DAOException if DB error.
     */
    List<ModuleFile> getAll(String associatedCourse, String associatedModule) throws DAOException;

    /**
     * Checks if file is stored in DB.
     * @param file
     * @return true if it is stored already in DB, false else.
     * @throws DAOException if DB error.
     */
    boolean isModuleFileExistent(ModuleFile file) throws DAOException;
}
