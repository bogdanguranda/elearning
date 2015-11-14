package thecerealkillers.elearning.controller.impl;

import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.UserAdminController;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.model.UserOM;
import thecerealkillers.elearning.service.UserAdminService;
import thecerealkillers.elearning.utilities.PasswordExpert;
import thecerealkillers.elearning.utilities.PasswordInfo;
import thecerealkillers.elearning.validator.UserValidator;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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
    public ResponseEntity<String> authenticate(@RequestBody UserOM user) throws NoSuchAlgorithmException {
        if (UserValidator.validateUsername(user.getUsername()) != "" ||
                UserValidator.validatePassword(user.getPassword()) != "") {
            //TODO: HttpStatus should be tweaked, Idk what's the suitable one
            return new ResponseEntity<>("Invalid login info.", HttpStatus.NOT_FOUND);
        }

        String token = userAdminService.authenticate(user);
        if (token == null)
            //TODO: HttpStatus should be tweaked, Idk what's the suitable one
            return new ResponseEntity<>("Invalid login info.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable("username") String username) {
        User user = userAdminService.get(username);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userAdminService.getAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
