package thecerealkillers.elearning.service;


import thecerealkillers.elearning.exceptions.NotFoundException;
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

    void createAccount(AdminSignUpInfo newUser) throws ServiceException, NotFoundException;

    void suspendAccount(AccountSuspensionInfo suspendInfo) throws ServiceException, NotFoundException;

    void reactivateAccount(AccountSuspensionInfo reactivateInfo) throws ServiceException, NotFoundException;

    void changeAccountType(ChangeAccountTypeInfo accountTypeInfo) throws ServiceException, NotFoundException;
}
