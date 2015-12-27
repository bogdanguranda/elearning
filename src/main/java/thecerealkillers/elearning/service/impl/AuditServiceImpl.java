package thecerealkillers.elearning.service.impl;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.service.AuditService;
import thecerealkillers.elearning.service.UserService;
import thecerealkillers.elearning.model.AuditItem;
import thecerealkillers.elearning.dao.AuditDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Dani.
 */
@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditDAO auditDAO;

    @Autowired
    private UserService userService;


    @Override
    public void addEvent(AuditItem auditItem) throws ServiceException {
        try {
            if (userService.exists(auditItem.getUsername())) {
                auditDAO.addEvent(auditItem);
            }
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.ADD_EVENT);
        }
    }
}
