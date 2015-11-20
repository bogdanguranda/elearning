package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.UserController;
import thecerealkillers.elearning.exceptions.InvalidLoginInfoException;
import thecerealkillers.elearning.exceptions.InvalidSignUpInfoException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.model.UserLoginInfo;
import thecerealkillers.elearning.model.UserSignUpInfo;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.UserService;
import thecerealkillers.elearning.validator.UserValidator;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
@RestController
@CrossOrigin
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userAdminService;
    @Autowired
    private SessionService sessionService;

    @Override
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<String> authenticate(@RequestBody UserLoginInfo loginInfo) throws NoSuchAlgorithmException {
        try {
            UserValidator.validateLoginInfo(loginInfo);

            String token = userAdminService.authenticate(loginInfo);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (InvalidLoginInfoException login_exception) {
            return new ResponseEntity<>("Invalid login info.", HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>("Invalid login info.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestBody UserSignUpInfo signUpInfo) throws NoSuchProviderException, NoSuchAlgorithmException {
        try {
            UserValidator.validateSignUpInfo(signUpInfo);

            userAdminService.signUp(signUpInfo);
            return new ResponseEntity<>("Account successfuly created. Please check your email to activate it.", HttpStatus.OK);
        } catch (InvalidSignUpInfoException info_exception) {
            return new ResponseEntity<>(info_exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(service_exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable("username") String username, @RequestHeader(value="token") String token) {
        try {
            sessionService.getSessionByToken(token); //if the token is not found an exception will occur
            User user = userAdminService.get(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll(@RequestHeader(value="token") String token) {
        try {
            sessionService.getSessionByToken(token); //if the token is not found an exception will occur
            List<User> users = userAdminService.getAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (ServiceException service_exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
