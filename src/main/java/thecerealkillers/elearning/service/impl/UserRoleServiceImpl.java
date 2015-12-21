package thecerealkillers.elearning.service.impl;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.dao.impl.UserRoleDAOImpl;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.service.UserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Lucian and Dani.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDAOImpl userRoleDAO;

    @Override
    public void addRole(String username, String roleName) throws ServiceException {
        if (roleName.compareTo("administrator") == 0)
            throw new ServiceException(ServiceException.INVALID_USER_ROLE);

        try {
            userRoleDAO.addRole(username, roleName);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_ADD_ROLE + daoException.getMessage());
        }
    }

    @Override
    public String getRole(String username) throws ServiceException {
        try {
            return userRoleDAO.getRole(username);
        } catch (DAOException e) {
            throw new ServiceException(ServiceException.FAILED_GET_ROLE);
        }
    }

}
