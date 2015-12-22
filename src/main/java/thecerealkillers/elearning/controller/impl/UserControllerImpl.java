package thecerealkillers.elearning.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.UserController;
import thecerealkillers.elearning.exceptions.InvalidLoginInfoException;
import thecerealkillers.elearning.exceptions.InvalidSignUpInfoException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.*;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.UserService;
import thecerealkillers.elearning.validator.UserValidator;

import java.util.List;


/**
 * Created by cuvidk on 11/8/2015.
 * Modified by Dani
 * - Methods added : validateUserAccount, resetPasswordRequest, resetPassword, changePassword
 */
@RestController
@CrossOrigin
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @Override
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody UserLoginInfo loginInfo) {
        try {

            UserValidator.validateLoginInfo(loginInfo);
            String token = userService.authenticate(loginInfo);
            String role = userService.getRole(loginInfo.getUsername());
            AuthenticationInfo authenticationInfo = new AuthenticationInfo(token, role);

            return new ResponseEntity<>(authenticationInfo, HttpStatus.OK);
        } catch (InvalidLoginInfoException login_exception) {
            return new ResponseEntity<>("Invalid login info.", HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>("Invalid login info.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestBody UserSignUpInfo signUpInfo) {
        try {
            UserValidator.validateSignUpInfo(signUpInfo);

            userService.signUp(signUpInfo);

            return new ResponseEntity<>("Account successfully created. Please check your email to activate it.", HttpStatus.OK);
        } catch (InvalidSignUpInfoException info_exception) {
            return new ResponseEntity<>(info_exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable("username") String username, @RequestHeader(value = "token") String token) {
        try {
             sessionService.getSessionByToken(token); //if the token is not found an exception will occur

            return new ResponseEntity<>(userService.get(username), HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll(@RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token); //if the token is not found an exception will occur

            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity validateUserAccount(@PathVariable("username") String username,
                                              @RequestParam(value = "id", required = true) String actionID) {
        try {
            userService.validateUserAccount(username, actionID);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity resetPasswordRequest(@PathVariable("username") String username) {
        try {
            userService.resetPasswordRequest(username);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity resetPassword(@PathVariable("username") String username,
                                        @RequestParam(value = "id", required = true) String actionID) {
        try {
            userService.resetPassword(username, actionID);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity changePassword(@RequestBody PasswordChange passwordChange,
                                         @RequestHeader(value = "token") String token) {
        try {
             sessionService.getSessionByToken(token);

            UserValidator.validateLoginInfo(new UserLoginInfo(passwordChange.getUsername(), passwordChange.getNewPassword()));

            userService.changePassword(passwordChange);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (InvalidLoginInfoException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
