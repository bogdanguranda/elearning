package thecerealkillers.elearning.service;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.AuditItem;


/**
 * Created by Dani.
 */
public interface AuditService {


    /**
     * Adds an event in the Audit table
     */
    void addEvent(AuditItem auditItem) throws ServiceException;
}
