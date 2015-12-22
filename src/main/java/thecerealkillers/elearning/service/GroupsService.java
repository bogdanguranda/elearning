package thecerealkillers.elearning.service;

import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Group;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 22.12.2015.
 */
public interface GroupsService {

    List<Group> getAll() throws ServiceException;

    void addGroup(Group group) throws ServiceException;

    void removeGroup(String name) throws ServiceException;
}
