package thecerealkillers.elearning.dao;

import thecerealkillers.elearning.model.SessionDM;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.model.UserStatus;

import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
public interface UserAdminDAO {
    /**
     * Adds a user in the database.
     * @param user
     */
    void addUser(User user);

    /**
     * Retrieves the user with the username
     * @username
     * @param username
     * @return - The user with username @username
     */
    User get(String username);

    /**
     * Retrieves the status of the user with
     * @username from DB.
     * @param username
     * @return - the status of the user.
     */
    UserStatus getUserStatus(String username);

    /**
     * Adds @session in the database.
     * @param session
     */
    void addSession(SessionDM session);

    /**
     * Retrieves the Session assigned to user,
     * if it exists one.
     * @param username
     * @return - The session of the user if exists, null else.
     */
    SessionDM getSession(String username);

    /**
     * Retrieves a list of all the
     * existing users in the database.
     */
    List<User> getAll();
}
