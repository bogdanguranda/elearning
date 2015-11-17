package thecerealkillers.elearning.service;

import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.model.UserLoginInfo;
import thecerealkillers.elearning.model.UserSignUpInfo;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
public interface UserService {
    /**
     * Retrives a token if the @loginInfo.user is
     * existent in the database.
     * @param loginInfo
     * @return - A token as a String.
     */
    String authenticate(UserLoginInfo loginInfo) throws NoSuchAlgorithmException;

    /**
     * This should create a user if it's not already
     * existent in the database.
     * @param signUpInfo
     * @return a string containing info about what went wrong,
     * empty else.
     */
    String signUp(UserSignUpInfo signUpInfo) throws NoSuchProviderException, NoSuchAlgorithmException;

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
