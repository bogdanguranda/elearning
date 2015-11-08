package thecerealkillers.elearning.dao;

import thecerealkillers.elearning.model.User;

import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
public interface UserAdminDAO {
    /**
     * Retrieves a list of all the
     * existing users in the database.
     */
    List<User> getAll();
}
