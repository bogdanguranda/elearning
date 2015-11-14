package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.UserAdminDAO;
import thecerealkillers.elearning.model.SessionDM;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.model.UserOM;
import thecerealkillers.elearning.service.UserAdminService;
import thecerealkillers.elearning.utilities.PasswordExpert;
import thecerealkillers.elearning.utilities.TokenGenerator;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private UserAdminDAO userAdminDAO;

    @Override
    public String authenticate(UserOM user) throws NoSuchAlgorithmException {
        String username = user.getUsername();

        User userData = userAdminDAO.get(username);
        if (userData != null) {
            if (PasswordExpert.verifyPassword(user.getPassword(), userData.getSalt(), userData.getHash())) {
                SessionDM session = userAdminDAO.getSession(username);
                if (session != null)
                    return session.getToken();
                else {
                    String token = TokenGenerator.generate();
                    session = new SessionDM(username, token, new Timestamp(System.currentTimeMillis()));
                    userAdminDAO.addSession(session);
                    return token;
                }
            }
        }
        return null;
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
