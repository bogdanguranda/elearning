package thecerealkillers.elearning.service;

import org.springframework.stereotype.Service;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.ModuleFile;

import java.util.List;

/**
 * Created by cuvidk on 12/29/2015.
 */

@Service
public interface ModuleFileService {
    /**
     * Stores the file @file.
     * @param file
     * @throws ServiceException if DAOException encountered,
     * if file is already stored, if the associated course / module
     * doesn't exist.
     */
    void storeFile(ModuleFile file) throws ServiceException;

    /**
     * Deletes the file @file.
     * @param file
     * @throws ServiceException if file is innexistent /
     * if DAOException encountered.
     */
    void deleteFile(ModuleFile file) throws ServiceException;

    /**
     * Gets the corresponding file.
     * @param fileName
     * @param associatedCourse
     * @param associatedModule
     * @return corresponding ModuleFile.
     * @throws ServiceException if DAOException encountered /
     * if the file is non-existent.
     */
    ModuleFile getFile(String fileName, String associatedCourse, String associatedModule) throws ServiceException;

    /**
     * Gets a list of all corresponding module-files.
     * @param associatedCourse
     * @param associatedModule
     * @return a list of ModuleFile.
     * @throws ServiceException if DAOException encountered /
     * if no such module / course.
     */
    List<ModuleFile> getAll(String associatedCourse, String associatedModule) throws ServiceException;

    /**
     * Renames the file @file to @newName.
     * @param file
     * @param newName
     * @throws ServiceException if DAOException encountered /
     * if no such file as @file.
     */
    void renameFile(ModuleFile file, String newName) throws ServiceException;
}
