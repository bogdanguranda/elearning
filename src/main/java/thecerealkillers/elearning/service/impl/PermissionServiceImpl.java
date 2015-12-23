package thecerealkillers.elearning.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.PermissionDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Permission;
import thecerealkillers.elearning.service.PermissionService;


/**
 * Created by Dani.
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionDAO permissionDAO;


    @Override
    public Permission getPermission(String operationName, String roleName) throws ServiceException {
        try {
            return permissionDAO.getPermission(operationName,roleName);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_GET_PERMISSION +daoException.getMessage());
        }
    }

    @Override
    public Boolean isOperationAvailable(String operationName, String userRole) throws ServiceException {
        try {
            Permission permission = getPermission(operationName, userRole);

            return permission.getPermission();
        } catch (ServiceException serviceException) {
            throw new ServiceException(ServiceException.FAILED_DET_PERMISSION + serviceException.getMessage());
        }
    }
}
