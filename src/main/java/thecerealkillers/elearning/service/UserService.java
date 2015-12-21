package thecerealkillers.elearning.service;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.PasswordChange;
import thecerealkillers.elearning.model.UserSignUpInfo;
import thecerealkillers.elearning.model.UserLoginInfo;
import thecerealkillers.elearning.model.User;

import java.util.List;


/**
 * Created by cuvidk on 11/8/2015.
 * Modified by Dani
 * - Methods added : validateUserAccount, resetPasswordRequest, resetPassword, changePassword
 */
public interface UserService {
    /**
     * Retrives a token if the @loginInfo.user existent in the database.
     *
     * @param loginInfo = stores user and password
     * @return - A token as a String.
     * @throws ServiceException if DAOException was caught /
     *                          other weird exception.
     */
    String authenticate(UserLoginInfo loginInfo) throws ServiceException;

    /**
     * This should create a user if it's not already
     * existent in the database.
     *
     * @param signUpInfo = stored user's data
     * @throws ServiceException if DAOException was caught / etc.
     */
    void signUp(UserSignUpInfo signUpInfo) throws ServiceException;


    /**
     * Retrieves the user with username
     *
     * @param username = username of the user for which to return data
     * @return - the user with username @username
     */
    User get(String username) throws ServiceException;

    /**
     * Retrieves a list of all the
     * existing users in the database.
     */
    List<User> getAllUsers() throws ServiceException;

    /**
     * Checks if the token received is right and activates his account
     *
     * @param userName = username of the user for which was requested a account activation
     * @param token    = token send in email to make sure action is right
     * @throws ServiceException
     */
    void validateUserAccount(String userName, String token) throws ServiceException;

    /**
     * Sends a email to the user with username = @username with a token to make
     * sure the password reset action is right
     *
     * @param userName = username for the user on which behalf was requested a
     *                 password reset
     * @throws ServiceException
     */
    void resetPasswordRequest(String userName) throws ServiceException;

    /**
     * Checks if the token received is right and changes user's hash accordingly to
     * his new password (@newPassword)
     *
     * @param userName = username of the user that changed his password
     * @param token    = token send in email to make sure action is right
     * @throws ServiceException
     */
    void resetPassword(String userName, String token) throws ServiceException;

    /**
     * Initiates a authentication procedure with the old password, if it's successful will changes users hash
     * accordingly to his new password
     *
     * @param passwordChange = data required for a password change
     * @throws ServiceException
     */
    void changePassword(PasswordChange passwordChange) throws ServiceException;
}
