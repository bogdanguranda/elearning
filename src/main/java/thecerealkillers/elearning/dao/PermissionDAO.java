package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Permission;

import org.springframework.stereotype.Repository;


/**
 * Created by Dani.
 */
@Repository
public interface PermissionDAO {

    /**
     * Retrieves permission for an operation and role.
     *
     * @throws DAOException if database does not contain operation and or user role.
     */
    Permission getPermission(String operationName, String roleName) throws DAOException;
}
