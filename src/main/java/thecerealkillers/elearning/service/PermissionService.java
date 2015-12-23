package thecerealkillers.elearning.service;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Permission;

import org.springframework.stereotype.Service;

/**
 * Created by Dani.
 */
@Service
public interface PermissionService {

    Permission getPermission(String operationName, String roleName) throws ServiceException;

    Boolean isOperationAvailable(String operationName, String userRole) throws ServiceException;
}
