package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.User;

import java.util.List;


/**
 * Created by cuvidk on 11/8/2015.
 * Modified by Dani.
 */
public interface UserDAO {
    /**
     * Adds a user in the database.
     *
     * @param user
     * @throws DAOException if DB problems / other weird problems.
     */
    void signUp(User user) throws DAOException;

    /**
     * Retrieves the user with the username
     *
     * @param username
     * @return - The user with username @username
     * @throws DAOException if inexistent username / DB problems /
     *                      other weird problems.
     * @username
     */
    User get(String username) throws DAOException;

    /**
     * Checks if username is available.
     *
     * @param username
     * @return true if username is available.
     * @throws DAOException if username not available / DB problems/ etc.
     */
    boolean isUsernameAvailable(String username) throws DAOException;

    /**
     * Checks if email is available.
     *
     * @param email
     * @return true if email is available.
     * @throws DAOException if email not available / DB problems / etc.
     */
    boolean isEmailAvailable(String email) throws DAOException;

    /**
     * Changes password for the user with the username @userNames
     *
     * @param username
     * @param newPassword
     */
    void changePassword(String username, String newPassword) throws DAOException;

    /**
     * Retrieves a list of all the
     * existing users in the database.
     *
     * @return - a list with all users in DB.
     * @throws DAOException if DB problems / other weird problems.
     */
    List<User> getAllUsers() throws DAOException;

    /**
     * Deletes the user account with the username @userName
     *
     * @param username
     * @throws DAOException
     */
    void deleteAccount(String username) throws DAOException;
}
