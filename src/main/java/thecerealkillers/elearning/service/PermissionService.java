package thecerealkillers.elearning.service;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Permission;

import org.springframework.stereotype.Service;

/**
 * Created by Dani.
 */
@Service
public interface PermissionService {

    /**
     * Retrieves permission for an operation and role.
     *
     * @throws ServiceException if database does not contain operation and or user role.
     */
    Permission getPermission(String operationName, String roleName) throws ServiceException;


    /**
     * Return true if the operation with the name @operationName can be executed by an user
     * with role = @userRole.
     *
     * @throws ServiceException
     */
    Boolean isOperationAvailable(String operationName, String userRole) throws ServiceException;
}
