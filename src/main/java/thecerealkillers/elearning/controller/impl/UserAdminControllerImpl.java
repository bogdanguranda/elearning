package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import thecerealkillers.elearning.controller.UserAdminController;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.service.UserAdminService;

import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
@RestController
public class UserAdminControllerImpl implements UserAdminController {

    @Autowired
    private UserAdminService userAdminService;

    @Override
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userAdminService.getAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
