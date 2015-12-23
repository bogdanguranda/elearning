package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.exceptions.InvalidSignUpInfoException;
import thecerealkillers.elearning.exceptions.InvalidLoginInfoException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.controller.UserController;
import thecerealkillers.elearning.service.UserRoleService;
import thecerealkillers.elearning.validator.UserValidator;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.UserService;
import thecerealkillers.elearning.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


/**
 * Created by cuvidk on 11/8/2015.
 * Modified by Dani
 */
@CrossOrigin
@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PermissionService permissionService;


    @Override
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody UserLoginInfo loginInfo) {
        try {
            UserValidator.validateLoginInfo(loginInfo);
            String token = userService.authenticate(loginInfo);
            String role = userRoleService.getRole(loginInfo.getUsername());
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
    public ResponseEntity<?> signUp(@RequestBody UserSignUpInfo signUpInfo) {
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
    public ResponseEntity<?> get(@PathVariable("username") String username, @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("UserControllerImpl.get", crtUserRole)) {
                    return new ResponseEntity<>(userService.get(username), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("UserControllerImpl.getAll", crtUserRole)) {
                    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users/confirmation/create/{username}", method = RequestMethod.GET)
    public ResponseEntity validateUserAccount(@PathVariable("username") String username, @RequestParam(value = "id", required = true) String actionID) {
        try {
            userService.validateUserAccount(username, actionID);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users/confirmation/reset/{username}", method = RequestMethod.GET)
    public ResponseEntity resetPasswordRequest(@PathVariable("username") String username) {
        try {
            userService.resetPasswordRequest(username);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users/password/reset/{username}", method = RequestMethod.GET)
    public ResponseEntity resetPassword(@PathVariable("username") String username, @RequestParam(value = "id", required = true) String actionID) {
        try {
            userService.resetPasswordRequestHandler(username, actionID);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users/password/change", method = RequestMethod.POST)
    public ResponseEntity changePassword(@RequestBody PasswordChange passwordChange, @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("UserControllerImpl.changePassword", crtUserRole)) {
                    UserValidator.validateLoginInfo(new UserLoginInfo(passwordChange.getUsername(), passwordChange.getNewPassword()));

                    userService.changePassword(passwordChange);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (InvalidLoginInfoException invalidInfo) {
            return new ResponseEntity<>(invalidInfo.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
