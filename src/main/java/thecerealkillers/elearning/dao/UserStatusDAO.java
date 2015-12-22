package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.UserStatus;


/**
 * Created by Dani
 */
public interface UserStatusDAO {

    /**
     * Adds the status of a user in the database.
     *
     * @param userStatus
     * @throws DAOException
     */
    void add(UserStatus userStatus) throws DAOException;

    /**
     * Retrieves the status of the user with @username from DB.
     *
     * @param username
     * @return - the status of the user.
     * @throws DAOException if inexistent username / DB problems /
     *                      other weird problems.
     */
    UserStatus get(String username) throws DAOException;

    /**
     * Changes the account status to activated for the user with the username @userName.
     *
     * @param username
     */
    void activateAccount(String username) throws DAOException;

    /**
     * Suspends the account for the user with the username @userName.
     *
     * @param username
     */
    void suspendAccount(String username) throws DAOException;

    /**
     * Deletes the account status for the user with username @userName
     *
     * @param username
     */
    void accountDeleted(String username) throws DAOException;

    /**
     * Returns true if the account with the username @userName is activated, false if not
     *
     * @param username
     * @return
     * @throws DAOException
     */
    boolean isAccountActivated(String username) throws DAOException;

    /**
     * Updates token and timestamp for the user with the username equal to @username
     * USER ACTIVE STATUS IS NOT CHANGED!
     *
     * @param username
     * @param newToken
     */
    void update(String username, String newToken) throws DAOException;
}
