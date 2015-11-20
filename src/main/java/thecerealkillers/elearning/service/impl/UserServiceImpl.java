package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.SessionDAO;
import thecerealkillers.elearning.dao.UserDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.*;
import thecerealkillers.elearning.service.UserService;
import thecerealkillers.elearning.utilities.PasswordExpert;
import thecerealkillers.elearning.utilities.PasswordInfo;
import thecerealkillers.elearning.utilities.TokenGenerator;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userAdminDAO;
    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public String authenticate(UserLoginInfo user) throws ServiceException {
        try {
            String username = user.getUsername();

            User userData = userAdminDAO.get(username);
            UserStatus userStatus = userAdminDAO.getUserStatus(username);
            if (PasswordExpert.verifyPassword(user.getPassword(), userData.getSalt(), userData.getHash())
                    && userStatus.getActive()) {
                if (sessionDAO.isSessionAvailable(username))
                    return sessionDAO.getSessionByUser(username).getToken();
                else {
                    String token = TokenGenerator.generate();
                    SessionDM session = new SessionDM(username, token, null);
                    sessionDAO.addSession(session);
                    return token;
                }
            }
            throw new ServiceException("Wrong password / user not active.");
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        } catch (NoSuchAlgorithmException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    @Override
    public void signUp(UserSignUpInfo signUpInfo) throws ServiceException {
        try {
            if (userAdminDAO.isEmailAvailable(signUpInfo.getEmail()) &&
                    userAdminDAO.isUsernameAvailable(signUpInfo.getUsername())) {
                PasswordInfo passInfo = PasswordExpert.newPassword(signUpInfo.getPassword());

                User user = new User();
                user.setUsername(signUpInfo.getUsername());
                user.setFirstName(signUpInfo.getFirstName());
                user.setLastName(signUpInfo.getLastName());
                user.setEmail(signUpInfo.getEmail());
                user.setSalt(passInfo.getSalt());
                user.setHash(passInfo.getHash());

                userAdminDAO.signUp(user);
            }
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        } catch (NoSuchAlgorithmException exception) {
            throw new ServiceException(exception.getMessage());
        } catch (NoSuchProviderException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    @Override
    public User get(String username) throws ServiceException {
        try {
            return userAdminDAO.get(username);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userAdminDAO.getAll();
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }
}
