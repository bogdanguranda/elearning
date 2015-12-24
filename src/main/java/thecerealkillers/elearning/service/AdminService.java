package thecerealkillers.elearning.service;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.AccountSuspensionInfo;
import thecerealkillers.elearning.model.AdminSignUpInfo;
import thecerealkillers.elearning.model.ChangeAccountTypeInfo;
import thecerealkillers.elearning.model.UserSignUpInfo;

import java.util.List;


/**
 * Created by Dani.
 */
public interface AdminService {

    void createAccount(AdminSignUpInfo newUser) throws ServiceException;

    void suspendAccount(AccountSuspensionInfo suspendInfo) throws ServiceException;

    void reactivateAccount(AccountSuspensionInfo reactivateInfo) throws ServiceException;

    void changeAccountType(ChangeAccountTypeInfo accountTypeInfo) throws ServiceException;

    List<String> getAudit() throws ServiceException;

    List<String> getAuditForUser(String username) throws ServiceException;
}
