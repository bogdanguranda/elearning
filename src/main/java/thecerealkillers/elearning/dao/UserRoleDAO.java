package thecerealkillers.elearning.dao;


import thecerealkillers.elearning.exceptions.DAOException;


/**
 * Created by Lucian and Dani.
 */
public interface UserRoleDAO {

    /**
     * If @role is valid the role of the user with username equal to @username will be @role.
     *
     * @param username = username of the user for which to set the role
     * @param role     = role that the user will have
     * @throws DAOException
     */
    void addRole(String username, String role) throws DAOException;

    /**
     * Changes the role of an user account.
     *
     * @param username = User for which to change the role
     * @param role = new role of the user
     * @throws DAOException
     */
    void changeRole(String username, String role) throws DAOException;

    /**
     * Get role for specific username
     *
     * @param username = username of the user for which to return the role.
     * @return string role
     * @throws DAOException
     */
    String getRole(String username) throws DAOException;
}
