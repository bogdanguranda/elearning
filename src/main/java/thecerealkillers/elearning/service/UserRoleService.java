package thecerealkillers.elearning.service;


import thecerealkillers.elearning.exceptions.ServiceException;


/**
 * Created by Lucian and Dani.
 */
public interface UserRoleService {

    /**
     * If @roleName is valid the role of the user with username equal to @username will be @roleName.
     *
     * @param username = username of the user for which to set the role
     * @param roleName = role that the user will have
     * @throws ServiceException
     */
    void addRole(String username, String roleName) throws ServiceException;

    /**
     * Get role for specific username
     *
     * @param username = username of the user for which to return the role.
     * @return string role
     * @throws ServiceException
     */
    String getRole(String username) throws ServiceException;
}
