package thecerealkillers.elearning.service.impl;

import thecerealkillers.elearning.dao.UserAdminDAO;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.service.UserAdminService;

import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
public class UserAdminServiceImpl implements UserAdminService {
    private UserAdminDAO userAdminDAO;

    @Override
    public List<User> getAll() {
        return userAdminDAO.getAll();
    }
}
