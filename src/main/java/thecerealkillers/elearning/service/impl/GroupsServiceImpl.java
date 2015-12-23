package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.GroupsDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Group;
import thecerealkillers.elearning.service.GroupsService;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 22.12.2015.
 */
@Service
public class GroupsServiceImpl implements GroupsService {

    @Autowired
    private GroupsDAO groupsDAO;

    @Override
    public List<Group> getAll() throws ServiceException {
        try {
            return groupsDAO.getAll();
        } catch (DAOException daoException) {
            throw new ServiceException(daoException.getMessage());
        }
    }

    @Override
    public void addGroup(Group group) throws ServiceException {
        try {
            groupsDAO.add(group);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void removeGroup(String name) throws ServiceException {
        try {
            groupsDAO.remove(name);
        } catch (DAOException daoEX) {
            throw new ServiceException(daoEX.getMessage());
        }
    }

    @Override
    public void removeEnrollements(String title) throws ServiceException {
        try {
            groupsDAO.removeEnrollements(title);
        } catch (DAOException daoEX) {
            throw new ServiceException(daoEX.getMessage());
        }
    }


}
