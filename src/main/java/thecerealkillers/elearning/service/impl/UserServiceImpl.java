package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.UserDAO;
import thecerealkillers.elearning.model.*;
import thecerealkillers.elearning.service.UserService;
import thecerealkillers.elearning.utilities.PasswordExpert;
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

    @Override
    public String authenticate(UserLoginInfo user) throws NoSuchAlgorithmException {
        String username = user.getUsername();

        User userData = userAdminDAO.get(username);
        UserStatus userStatus = userAdminDAO.getUserStatus(username);
        if (userData != null && userStatus != null) {
            if (PasswordExpert.verifyPassword(user.getPassword(), userData.getSalt(), userData.getHash())
                    && userStatus.getActive()) {
                SessionDM session = userAdminDAO.getSession(username);
                if (session != null)
                    return session.getToken();
                else {
                    String token = TokenGenerator.generate();
                    session = new SessionDM(username, token, null);
                    userAdminDAO.addSession(session);
                    return token;
                }
            }
        }
        return null;
    }

    @Override
    public String signUp(UserSignUpInfo signUpInfo) throws NoSuchProviderException, NoSuchAlgorithmException {
        User user = userAdminDAO.get(signUpInfo.getUsername());

        //TODO: ALSO CHECK IF THE EMAIL IS UNIQUE AT DB LEVEL !!!
        if (user == null) {
            PasswordInfo passInfo = PasswordExpert.newPassword(signUpInfo.getPassword());

            user = new User();
            user.setUsername(signUpInfo.getUsername());
            user.setFirstName(signUpInfo.getFirstName());
            user.setLastName(signUpInfo.getLastName());
            user.setEmail(signUpInfo.getEmail());
            user.setSalt(passInfo.getSalt());
            user.setHash(passInfo.getHash());

            userAdminDAO.signUp(user);
        } else
            return "This username is already registered in the data base";
        return "";
    }

    @Override
    public User get(String username) {
        return userAdminDAO.get(username);
    }

    @Override
    public List<User> getAll() {
        return userAdminDAO.getAll();
    }
}
