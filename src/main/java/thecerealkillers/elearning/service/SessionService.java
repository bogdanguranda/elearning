package thecerealkillers.elearning.service;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.SessionDM;


/**
 * Created by cuvidk on 11/20/2015.
 * Modified by Dani.
 */
public interface SessionService {

    /**
     * Adds @session in the database.
     *
     * @throws ServiceException if DAOException was caught  other weird problem.
     */
    void addSession(SessionDM session) throws ServiceException;


    /**
     * Retrieves the Session assigned to user,
     * if it exists one.
     *
     * @return - The session of the user if exists.
     * @throws ServiceException if DAOException was caught other weird problem.
     */
    SessionDM getSessionByUser(String username) throws ServiceException;


    /**
     * Checks if @token is an valid token in Session table in DB.
     *
     * @return - the associated session to token
     * @throws ServiceException if DAOException was caught other weird problem.
     */
    SessionDM getSessionByToken(String token) throws ServiceException;

    void deleteSession(String username) throws ServiceException;

    Boolean isSessionActive(String token);

    String getUserRoleByToken(String token) throws ServiceException;

    String startOrGetSession(String username) throws ServiceException;
}