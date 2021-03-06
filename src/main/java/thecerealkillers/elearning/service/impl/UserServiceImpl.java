package thecerealkillers.elearning.service.impl;


import thecerealkillers.elearning.service.UserRoleService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.UserService;
import thecerealkillers.elearning.dao.UserStatusDAO;
import thecerealkillers.elearning.exceptions.*;
import thecerealkillers.elearning.utilities.*;
import thecerealkillers.elearning.dao.UserDAO;
import thecerealkillers.elearning.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by cuvidk on 11/8/2015.
 * Modified by Dani.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserStatusDAO userStatusDAO;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SessionService sessionService;


    private String siteUrl = "https://localhost:8080/";
    private String validationUrl = siteUrl + "users/confirmation/create/";
    private String changeUrl = siteUrl + "users/password/reset/";
    private int validationLinkExpTimeH = 24; // hours
    private int changeLinkExpTimeH = 15; // minutes
    private int CONVERT_TO_HOUR = 60 * 60 * 1000;
    private int CONVERT_TO_MIN = 60 * 1000;


    @Override
    public String authenticate(UserLoginInfo user) throws ServiceException, NotFoundException {
        try {
            String username = user.getUsername();
            User userData = userDAO.get(username);

            if (PasswordExpert.verifyPassword(user.getPassword(), userData.getSalt(), userData.getHash())
                    && userStatusDAO.isAccountActivated(username)) {
                return sessionService.startOrGetSession(username);
            }

            throw new ServiceException(ServiceException.FAILED_LOG_IN);
        } catch (PasswordExpertException passwordException) {
            throw new ServiceException(ServiceException.FAILED_PASSWORD_LOG_IN);

        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_LOG_IN);
        }
    }

    @Override
    public String addAccount(UserSignUpInfo signUpInfo, String userRole) throws ServiceException {
        try {
            String username = signUpInfo.getUsername();
            String email = signUpInfo.getEmail();

            if (userRole.compareTo(Constants.ADMIN) == 0 && Constants.ROLE_LIST.contains(userRole))
                throw new ServiceException(ServiceException.INVALID_USER_ROLE);

            if (userDAO.isAvailable(username, email)) {
                PasswordInfo passInfo = PasswordExpert.newPassword(signUpInfo.getPassword());
                String newToken = TokenGenerator.generate();

                userDAO.signUp(new User(username, signUpInfo.getFirstName(), signUpInfo.getLastName(),
                        email, passInfo.getHash(), passInfo.getSalt()));
                userStatusDAO.add(new UserStatus(username, newToken));
                userRoleService.addRole(username, userRole);

                return newToken;
            }

            throw new ServiceException(ServiceException.FAILED_ACCOUNT_CREATION);
        } catch (PasswordExpertException passwordException) {
            throw new ServiceException(ServiceException.FAILED_PASSWORD_SIGN_UP);
        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_SIGN_UP);
        }
    }

    @Override
    public void signUp(UserSignUpInfo signUpInfo) throws ServiceException {
        try {
            String username = signUpInfo.getUsername();
            String email = signUpInfo.getEmail();
            String token = addAccount(signUpInfo, Constants.STUDENT);   // Creates the user account and returns the token with which to activate the account.

            String userRealName = signUpInfo.getFirstName() + " " + signUpInfo.getLastName();
            String tmpUrl = validationUrl + username + "/?id=" + token;

            EmailExpert.sendMailValidation(email, userRealName, tmpUrl);

        } catch (EmailException emailException) {
            try {
                deleteUserAccount(signUpInfo.getUsername());
            } catch (DAOException dao_ex) {
                throw new ServiceException(ServiceException.FAILED_DAO_DELETE_ACCOUNT);
            }

            throw new ServiceException(ServiceException.FAILED_EMAIL_SIGN_UP);
        }
    }

    @Override
    public void validateUserAccount(String userName, String token) throws ServiceException, NotFoundException {
        try {
            if (!(userStatusDAO.isAccountActivated(userName))) {
                int tokenValidation = validateCriticalToken(token, CONVERT_TO_HOUR, userName, validationLinkExpTimeH);

                if (tokenValidation == 1)
                    deleteUserAccount(userName);
                else if (tokenValidation == 3)
                    userStatusDAO.activateAccount(userName);
            }

            User user = userDAO.get(userName);
            String userRealName = user.getFirstName() + " " + user.getLastName();

            EmailExpert.sendAccountCreated(user.getEmail(), userRealName);
        } catch (EmailException emailException) {
            throw new ServiceException(ServiceException.FAILED_EMAIL_EMAIL_VALIDATION);

        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_EMAIL_VALIDATION);
        }

    }

    /**
     * In order to keep things simple i use user_status table to store token and the token creation time, this is  not a
     * problem because:
     * - token and token timestamp are used only one time for a user account, at the moment of user's email address validation
     * - a password reset can not happen before a email address is validated
     *
     * @param userName = username of the the user that requested a password reset
     * @throws ServiceException
     */
    @Override
    public void resetPasswordRequest(String userName) throws ServiceException, NotFoundException {
        try {
            if (userStatusDAO.isAccountActivated(userName)) {
                User user = userDAO.get(userName);

                String newToken = TokenGenerator.generate();
                userStatusDAO.update(userName, newToken);

                String userRealName = user.getFirstName() + " " + user.getLastName();
                String tmpUrl = changeUrl + userName + "/?id=" + newToken;

                EmailExpert.sendPasswordResetRequest(user.getEmail(), userRealName, tmpUrl);
            } else {
                throw new ServiceException(ServiceException.CAN_NOT_RESET_PASSWORD);
            }
        } catch (EmailException emailException) {
            throw new ServiceException(ServiceException.FAILED_EMAIL_RESET_REQUEST);

        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_RESET_REQUEST);
        }
    }

    @Override
    public void resetPasswordRequestHandler(String username, String token) throws ServiceException, NotFoundException {
        try {
            if (userStatusDAO.isAccountActivated(username)) {
                if (validateCriticalToken(token, CONVERT_TO_MIN, username, changeLinkExpTimeH) == 3) {
                    String newPassword = PasswordExpert.generatePassword();
                    User user = userDAO.get(username);

                    String userRealName = user.getFirstName() + " " + user.getLastName();
                    EmailExpert.sendPasswordReset(user.getEmail(), username, newPassword, userRealName);

                    passwordChange(user, newPassword);
                }
            } else {
                throw new ServiceException(ServiceException.CAN_NOT_RESET_PASSWORD);
            }
        } catch (PasswordExpertException passwordException) {
            throw new ServiceException(ServiceException.FAILED_GENERATE_PASSWORD);

        } catch (EmailException emailException) {
            throw new ServiceException(ServiceException.FAILED_EMAIL_PASSWORD_RESET);

        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_PASSWORD_RESET);
        }
    }

    @Override
    public void setPassword(String username) throws ServiceException, NotFoundException, EmailException {
        try {
            if (userStatusDAO.isAccountActivated(username)) {
                String newPassword = PasswordExpert.generatePassword();
                User user = userDAO.get(username);

                String userRealName = user.getFirstName() + " " + user.getLastName();
                EmailExpert.sendPasswordSet(user.getEmail(), username, newPassword, userRealName);

                passwordChange(user, newPassword);
            } else {
                throw new ServiceException(ServiceException.CAN_NOT_RESET_PASSWORD);
            }
        } catch (PasswordExpertException passwordException) {
            throw new ServiceException(ServiceException.FAILED_GENERATE_PASSWORD);

        } catch (EmailException emailException) {
            throw emailException;

        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_PASSWORD_RESET);
        }
    }

    @Override
    public void changePassword(PasswordChange passwordChange) throws ServiceException, NotFoundException {
        try {
            User user = userDAO.get(passwordChange.getUsername());
            String userRealName = user.getFirstName() + " " + user.getLastName();
            authenticate(new UserLoginInfo(passwordChange.getUsername(), passwordChange.getOldPassword()));

            EmailExpert.sendPasswordChanged(user.getEmail(), userRealName);

            passwordChange(user, passwordChange.getNewPassword());
        } catch (PasswordExpertException passwordException) {
            throw new ServiceException(ServiceException.FAILED_AUTHENTICATE_PASSWORD_CHANGE);

        } catch (EmailException emailException) {
            throw new ServiceException(ServiceException.FAILED_EMAIL_PASSWORD_CHANGE);

        } catch (DAOException daoException) {
            throw new ServiceException(ServiceException.FAILED_DAO_PASSWORD_CHANGE);
        }
    }

    @Override
    public User get(String username) throws ServiceException, NotFoundException {
        try {
            User user = userDAO.get(username);

            user.setSalt("");
            user.setHash("");

            return user;
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException, NotFoundException {
        try {
            return userDAO.getAllUsers();
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public void deleteUserAccount(String username) throws DAOException {
        userDAO.deleteAccount(username);
        userStatusDAO.accountDeleted(username);
    }

    @Override
    public Boolean exists(String username) throws ServiceException{
        try {
            return !userDAO.isUsernameAvailable(username);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }


    private void passwordChange(User user, String newPassword) throws PasswordExpertException, DAOException {
        PasswordInfo passInfo = PasswordExpert.newPassword(newPassword);

        userDAO.changePassword(user.getUsername(), passInfo.getSalt(), passInfo.getHash());
        userStatusDAO.update(user.getUsername(), "");
    }

    /**
     * Verifies @token
     *
     * @param token            = token that will be checked
     * @param conversion       = conversion unit
     * @param username         = the username for witch to compare @token with the token stored in the database
     * @param timeTokenIsValid = time limit in which the token is valid
     * @return * 1 if token is expired
     * * 2 if @token does not match with the one in the database for user with username equal to @userName
     * * 3 if token is valid
     * @throws DAOException
     */
    private int validateCriticalToken(String token, int conversion, String username, int timeTokenIsValid) throws DAOException {
        UserStatus userStatus = userStatusDAO.get(username);
        Date creationTime = userStatus.getSignUpTimestamp();
        Date crtTime = new Date();

        long difference = crtTime.getTime() - creationTime.getTime();

        if ((difference / conversion) % 60 >= timeTokenIsValid)
            return 1;

        if (token.compareTo(userStatus.getToken()) != 0)
            return 2;

        return 3;
    }
}
