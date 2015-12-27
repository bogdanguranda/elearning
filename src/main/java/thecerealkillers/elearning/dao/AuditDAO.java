package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.AuditItem;

import org.springframework.stereotype.Repository;


/**
 * Created by Dani.
 */
@Repository
public interface AuditDAO {

    /**
     * Adds an event in the Audit table
     *
     * @throws DAOException
     */
    void addEvent(AuditItem auditItem) throws DAOException;
}
