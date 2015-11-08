package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.UserAdminDAO;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.service.UserAdminService;

import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private UserAdminDAO userAdminDAO;

    @Override
    public List<User> getAll() {
        return userAdminDAO.getAll();
    }
}
