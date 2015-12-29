package thecerealkillers.elearning.service;

import org.springframework.stereotype.Service;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.ModuleFile;

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
}
