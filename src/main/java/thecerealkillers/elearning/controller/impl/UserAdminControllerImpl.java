package thecerealkillers.elearning.controller.impl;

import javafx.beans.InvalidationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.UserAdminController;
import thecerealkillers.elearning.exceptions.InvalidLoginInfoException;
import thecerealkillers.elearning.exceptions.InvalidSignUpInfoException;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.model.UserLoginInfo;
import thecerealkillers.elearning.model.UserSignUpInfo;
import thecerealkillers.elearning.service.UserAdminService;
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
    public ResponseEntity<String> authenticate(@RequestBody UserLoginInfo loginInfo) throws NoSuchAlgorithmException {
        try {
            UserValidator.validateLoginInfo(loginInfo);

            String token = userAdminService.authenticate(loginInfo);
            if (token == null)
                //TODO: HttpStatus should be tweaked, Idk what's the suitable one
                return new ResponseEntity<>("Invalid login info.", HttpStatus.UNPROCESSABLE_ENTITY);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (InvalidLoginInfoException login_exception) {
            return new ResponseEntity<>(login_exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody UserSignUpInfo signUpInfo) throws NoSuchProviderException, NoSuchAlgorithmException {
        try {
            UserValidator.validateSignUpInfo(signUpInfo);

            String validationFeedback = userAdminService.createUser(signUpInfo);
            if (!validationFeedback.equals(""))
                return new ResponseEntity<>(validationFeedback, HttpStatus.UNPROCESSABLE_ENTITY);
            return new ResponseEntity<>("Account successfuly created. Please check your email to activate it.", HttpStatus.OK);
        } catch (InvalidSignUpInfoException info_exception) {
            return new ResponseEntity<>(info_exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
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
