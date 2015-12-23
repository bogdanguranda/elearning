package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Permission;

import org.springframework.stereotype.Repository;


/**
 * Created by Dani.
 */
@Repository
public interface PermissionDAO {

    Permission getPermission(String operationName, String roleName) throws DAOException;
}
