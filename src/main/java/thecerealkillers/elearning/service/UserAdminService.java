package thecerealkillers.elearning.service;

import org.springframework.stereotype.Service;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.model.UserOM;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
public interface UserAdminService {
    /**
     * Retrives a token if the @user is
     * existent in the database.
     * @param user
     * @return - A token as a String.
     */
    String authenticate(UserOM user) throws NoSuchAlgorithmException;

    /**
     * Retrives the user with username
     * @username.
     * @param username
     * @return - the user with username @username
     */
    User get(String username);

    /**
     * Retrieves a list of all the
     * existing users in the database.
     */
    List<User> getAll();
}
