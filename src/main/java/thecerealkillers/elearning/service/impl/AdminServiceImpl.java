package thecerealkillers.elearning.service.impl;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.AccountSuspensionInfo;
import thecerealkillers.elearning.model.AdminSignUpInfo;
import thecerealkillers.elearning.model.ChangeAccountTypeInfo;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.UserSignUpInfo;
import thecerealkillers.elearning.service.AdminService;
import thecerealkillers.elearning.dao.UserStatusDAO;
import thecerealkillers.elearning.dao.UserRoleDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.service.UserService;

import java.util.List;


/**
 * Created by Dani.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private UserStatusDAO userStatusDAO;

    @Override
    public void createAccount(AdminSignUpInfo newUser) throws ServiceException {
        try {
            userService.addAccount(new UserSignUpInfo(newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(),
                    "", newUser.getEmail()), newUser.getUserRole());

            userStatusDAO.activateAccount(newUser.getUsername());
            userService.setPassword(newUser.getUsername());

        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_ROLE_CHG + daoException.getMessage());
        } catch (ServiceException serviceException){
            try {
                userService.deleteUserAccount(newUser.getUsername());
            } catch (DAOException dao_ex) {
                throw new ServiceException(ServiceException.FAILED_DAO_DELETE_ACCOUNT + dao_ex.getMessage());
            }

            throw new ServiceException(serviceException.getMessage());
        }
    }

    @Override
    public void suspendAccount(AccountSuspensionInfo suspendInfo) throws ServiceException {
        try {
            userStatusDAO.suspendAccount(suspendInfo.getAccountUsername());
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_ROLE_CHG + daoException.getMessage());
        }
    }

    @Override
    public void reactivateAccount(AccountSuspensionInfo reactivateInfo) throws ServiceException {
        try {
            userStatusDAO.activateAccount(reactivateInfo.getAccountUsername());
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_ROLE_CHG + daoException.getMessage());
        }
    }

    @Override
    public void changeAccountType(ChangeAccountTypeInfo accountTypeInfo) throws ServiceException {
        try {
            userRoleDAO.changeRole(accountTypeInfo.getAccountUsername(), accountTypeInfo.getNewAccountType());
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_ROLE_CHG + daoException.getMessage());
        }
    }

    @Override
    public List<String> getAudit() throws ServiceException {
        //TODO getAudit

        throw new ServiceException(ServiceException.NOT_IMPLEMENTED);
    }

    @Override
    public List<String> getAuditForUser(String username) throws ServiceException {
        //TODO getAuditForUser

        throw new ServiceException(ServiceException.NOT_IMPLEMENTED);
    }
}
