package thecerealkillers.elearning.dao;

import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.SessionDM;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.model.UserStatus;

import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
public interface UserDAO {
    /**
     * Adds a user in the database.
     * @param user
     * @throws DAOException if DB problems / other weird problems.
     */
    void signUp(User user) throws DAOException;

    /**
     * Retrieves the user with the username
     * @username
     * @param username
     * @return - The user with username @username
     * @throws DAOException if inexistent username / DB problems /
     * other weird problems.
     */
    User get(String username) throws DAOException;

    /**
     * Checks if username is available.
     * @param username
     * @return true if username is available.
     * @throws DAOException if username not available / DB problems/ etc.
     */
    boolean isUsernameAvailable(String username) throws DAOException;

    /**
     * Checks if email is available.
     * @param email
     * @return true if email is available.
     * @throws DAOException if email not available / DB problems / etc.
     */
    boolean isEmailAvailable(String email) throws DAOException;

    /**
     * Retrieves the status of the user with @username from DB.
     * @param username
     * @return - the status of the user.
     * @throws DAOException if inexistent username / DB problems /
     * other weird problems.
     */
    UserStatus getUserStatus(String username) throws DAOException;

    /**
     * Retrieves a list of all the
     * existing users in the database.
     * @return - a list with all users in DB.
     * @throws DAOException if DB problems / other weird problems.
     */
    List<User> getAll() throws DAOException;
}
