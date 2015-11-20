package thecerealkillers.elearning.service.impl;

import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.SessionDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.SessionDM;
import thecerealkillers.elearning.service.SessionService;

/**
 * Created by cuvidk on 11/20/2015.
 */
@Service
public class SessionServiceImpl implements SessionService {
    private SessionDAO sessionDAO;

    @Override
    public void addSession(SessionDM session) throws ServiceException {
        try {
            sessionDAO.addSession(session);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public SessionDM getSessionByUser(String username) throws ServiceException {
        try {
            return sessionDAO.getSessionByUser(username);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }

    @Override
    public SessionDM getSessionByToken(String token) throws ServiceException {
        try {
            return sessionDAO.getSessionByToken(token);
        } catch (DAOException dao_exception) {
            throw new ServiceException(dao_exception.getMessage());
        }
    }
}
