package thecerealkillers.elearning.dao;

import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Group;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 22.12.2015.
 */
@Repository
public interface GroupsDAO {

    List<Group> getAll() throws DAOException;

    void add(Group group) throws DAOException;

    void remove(String name) throws DAOException;

    void removeEnrollements(String title) throws DAOException;
}
