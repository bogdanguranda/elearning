package thecerealkillers.elearning.service;

import org.springframework.stereotype.Service;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.SessionDM;

/**
 * Created by cuvidk on 11/20/2015.
 */
@Service
public interface SessionService {
    /**
     * Adds @session in the database.
     * @param session
     * @throws ServiceException if DAOException was caught /
     * other weird problem.
     */
    void addSession(SessionDM session) throws ServiceException;

    /**
     * Retrieves the Session assigned to user,
     * if it exists one.
     * @param username
     * @return - The session of the user if exists.
     * @throws ServiceException if DAOException was caught /
     * other weird problem.
     */
    SessionDM getSessionByUser(String username) throws ServiceException;

    /**
     * Checks if @token is an valid token in Session
     * table in DB.
     * @param token
     * @return - the associated session to token
     * @throws ServiceException if DAOException was caught /
     * other weird problem.
     */
    SessionDM getSessionByToken(String token) throws ServiceException;
}
