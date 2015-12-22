package thecerealkillers.elearning.utilities;


import thecerealkillers.elearning.exceptions.SessionExpertException;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.UserRoleDAO;
import thecerealkillers.elearning.model.SessionDM;
import thecerealkillers.elearning.dao.SessionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Created by cuvidk on 11/20/2015.
 * Modified by Dani.
 */
@Service
public class SessionExpert {
    @Autowired
    private static SessionDAO sessionDAO;
    @Autowired
    private static UserRoleDAO userRoleDAO;

    /**
     * Adds @session in the database.
     *
     * @throws SessionExpertException if DAOException was caught  other weird problem.
     */
    public static void addSession(SessionDM session) throws SessionExpertException {
        try {
            sessionDAO.addSession(session);
        } catch (DAOException dao_exception) {
            throw new SessionExpertException(SessionExpertException.FAILED_ADD_SESSION + dao_exception.getMessage());
        }
    }

    /**
     * Retrieves the Session assigned to user,
     * if it exists one.
     *
     * @return - The session of the user if exists.
     * @throws SessionExpertException if DAOException was caught other weird problem.
     */
    public static SessionDM getSessionByUser(String username) throws SessionExpertException {
        try {
            return sessionDAO.getSessionByUser(username);
        } catch (DAOException dao_exception) {
            throw new SessionExpertException(SessionExpertException.FAILED_GET_BY_USE + dao_exception.getMessage());
        }
    }

    /**
     * Checks if @token is an valid token in Session table in DB.
     *
     * @return - the associated session to token
     * @throws SessionExpertException if DAOException was caught other weird problem.
     */
    public static SessionDM getSessionByToken(String token) throws SessionExpertException {
        try {
            return sessionDAO.getSessionByToken(token);
        } catch (DAOException dao_exception) {
            throw new SessionExpertException(SessionExpertException.FAILED_GET_BY_TOKEN + dao_exception.getMessage());
        }
    }

    public static Boolean isSessionActive(String token) throws SessionExpertException {
        SessionDM session = getSessionByToken(token);

        return isNotExpired(session.getCreationStamp(), session.getUsername());
    }

    private static Boolean isNotExpired(Date creationTime, String username) throws SessionExpertException {
        int timeIsValid = 24; // hours
        Date crtTime = new Date();

        long difference = crtTime.getTime() - creationTime.getTime();

        if ((difference / 60 * 60 * 1000) % 60 >= timeIsValid) {
            try {
                sessionDAO.deleteSession(username);
            } catch (DAOException dao_exception) {
                throw new SessionExpertException(SessionExpertException.FAILED_DELETE_SESSION + dao_exception.getMessage());
            }

            return true;
        }

        return false;
    }

    public static String getUserRoleByToken(String token) throws SessionExpertException {
        try {
            SessionDM session = sessionDAO.getSessionByToken(token);

            return userRoleDAO.getRole(session.getUsername());
        } catch (DAOException dao_exception) {
            throw new SessionExpertException(SessionExpertException.FAILED_GET_ROBE + dao_exception.getMessage());
        }
    }

    public static String startOrGetSession(String username) throws SessionExpertException {
        if (isSessionActive(username)) {
            return getSessionByUser(username).getToken();
        } else {
            String token = TokenGenerator.generate();

            addSession(new SessionDM(username, token, null));

            return token;
        }
    }
}
