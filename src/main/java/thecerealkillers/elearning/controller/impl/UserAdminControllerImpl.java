package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.awt.AWTAccessor;
import thecerealkillers.elearning.controller.UserAdminController;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.model.UserOM;
import thecerealkillers.elearning.service.UserAdminService;
import thecerealkillers.elearning.validator.UserValidator;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
@RestController
@CrossOrigin
public class UserAdminControllerImpl implements UserAdminController {

    @Autowired
    private UserAdminService userAdminService;

    @Override
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<String> authenticate(@RequestParam("username") String username,
                                               @RequestParam("password") String password) {
        if (UserValidator.validateUsername(username) != "" ||
                UserValidator.validatePassword(password) != "")
            //TODO: HttpStatus should be tweaked, Idk what's the suitable one
            return new ResponseEntity<>("Invalid login info.", HttpStatus.BAD_REQUEST);

        UserOM user = new UserOM(username, password);
        return new ResponseEntity<>("INFO OK!!!!!", HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userAdminService.getAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
