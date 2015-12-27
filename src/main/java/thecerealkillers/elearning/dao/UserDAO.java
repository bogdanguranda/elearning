package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.model.User;

import java.util.List;


/**
 * Created by cuvidk on 11/8/2015.
 * Modified by Dani.
 */
//@Repository
public interface UserDAO {
    /**
     * Adds a user in the database.
     *
     * @throws DAOException if DB problems / other weird problems.
     */
    void signUp(User user) throws DAOException;

    /**
     * Retrieves the user with the username
     *
     * @return - The user with username @username
     * @throws DAOException if inexistent username / DB problems /
     *                      other weird problems.
     */
    User get(String username) throws DAOException, NotFoundException;

    /**
     * Changes password for the user with the username @userNames
     */
    void changePassword(String username, String newSalt, String newHash) throws DAOException;

    /**
     * Retrieves a list of all the
     * existing users in the database.
     *
     * @return - a list with all users in DB.
     * @throws DAOException if DB problems / other weird problems.
     */
    List<User> getAllUsers() throws DAOException, NotFoundException;

    /**
     * Deletes the user account with the username @userName
     *
     * @throws DAOException
     */
    void deleteAccount(String username) throws DAOException;

    /**
     * Checks if username and email are available.
     *
     * @return true if username and email are available.
     * @throws DAOException
     */
    Boolean isAvailable(String username, String email) throws DAOException;

    /**
     * Returns true if the database doesn't contain a user with the username = @username, false otherwise
     *
     * @throws DAOException
     */
    Boolean isUsernameAvailable(String username) throws DAOException;

    /**
     * Returns true if the database doesn't contain a user with the email = @email, false otherwise
     *
     * @throws DAOException
     */
    Boolean isEmailAvailable(String email) throws DAOException;
}
