package thecerealkillers.elearning.service.impl;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.utilities.TokenGenerator;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.service.SessionService;
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
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;

    public void addSession(SessionDM session) throws ServiceException {
        try {
            sessionDAO.addSession(session);
        } catch (DAOException dao_exception) {
            throw new ServiceException(ServiceException.FAILED_ADD_SESSION + dao_exception.getMessage());
        }
    }

    public SessionDM getSessionByUser(String username) throws ServiceException {
        try {
            return sessionDAO.getSessionByUser(username);
        } catch (DAOException dao_exception) {
            throw new ServiceException(ServiceException.FAILED_GET_BY_USE + dao_exception.getMessage());
        }
    }

    public SessionDM getSessionByToken(String token) throws ServiceException {
        try {
            return sessionDAO.getSessionByToken(token);
        } catch (DAOException dao_exception) {
            throw new ServiceException(ServiceException.FAILED_GET_BY_TOKEN + dao_exception.getMessage());
        }
    }

    public Boolean isSessionActive(String token) {
        try {
            SessionDM session = sessionDAO.getSessionByToken(token);

            return isNotExpired(session.getCreationStamp(), session.getUsername());
        } catch (DAOException dao_exception) {
            return false;
        } catch (ServiceException serviceException) {
            return false;
        }
    }

    private Boolean isNotExpired(Date creationTime, String username) throws ServiceException {
        return true;
    }

    public void deleteSession(String username) throws ServiceException {
        try {
            sessionDAO.deleteSession(username);
        } catch (DAOException dao_exception) {
            throw new ServiceException(ServiceException.FAILED_DELETE_SESSION + dao_exception.getMessage());
        }
    }

    public String getUserRoleByToken(String token) throws ServiceException {
        try {
            SessionDM session = sessionDAO.getSessionByToken(token);

            return userRoleDAO.getRole(session.getUsername());
        } catch (DAOException dao_exception) {
            throw new ServiceException(ServiceException.FAILED_GET_ROBE + dao_exception.getMessage());
        }
    }

    public String startOrGetSession(String username) throws ServiceException {
        try {
            if (sessionDAO.isSessionAvailable(username)) {
                return getSessionByUser(username).getToken();
            } else {
                String token = TokenGenerator.generate();

                addSession(new SessionDM(username, token, null));

                return token;
            }
        } catch (DAOException dao_exception) {
            throw new ServiceException(ServiceException.FAILED_GET_ADD_SESSION + dao_exception.getMessage());
        }
    }
}
