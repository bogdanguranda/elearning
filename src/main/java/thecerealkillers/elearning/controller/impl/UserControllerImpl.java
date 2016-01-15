package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.exceptions.InvalidSignUpInfoException;
import thecerealkillers.elearning.exceptions.InvalidLoginInfoException;
import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.controller.UserController;
import thecerealkillers.elearning.validator.UserValidator;
import thecerealkillers.elearning.utilities.Constants;
import thecerealkillers.elearning.service.*;
import thecerealkillers.elearning.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * Created by cuvidk on 11/8/2015.
 * Modified by Dani
 */
@CrossOrigin
@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private AuditService auditService;

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
        String actionName = "UserControllerImpl.authenticate";

        try {
            try {
                UserValidator.validateLoginInfo(loginInfo);
                String token = userService.authenticate(loginInfo);
                String role = userRoleService.getRole(loginInfo.getUsername());
                AuthenticationInfo authenticationInfo = new AuthenticationInfo(token, role);

                auditService.addEvent(new AuditItem(loginInfo.getUsername(), actionName, "", Constants.USER_AUTHENTICATE, true));
                return new ResponseEntity<>(authenticationInfo, HttpStatus.OK);

            } catch (InvalidLoginInfoException info_exception) {
                auditService.addEvent(new AuditItem(loginInfo.getUsername(), actionName, "", Constants.USER_INVALID_LOGIN_INFO, false));
                return new ResponseEntity<>(Constants.USER_INVALID_LOGIN_INFO, HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(loginInfo.getUsername(), actionName, "", Constants.USER_INVALID_LOGIN_INFO, false));
                return new ResponseEntity<>(Constants.USER_INVALID_LOGIN_INFO, HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(loginInfo.getUsername(), actionName, "", Constants.USER_INVALID_LOGIN_INFO, false));
                return new ResponseEntity<>(Constants.USER_INVALID_LOGIN_INFO, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logout(@RequestHeader(value = "token") String token) {
        String actionName = "UserControllerImpl.logout";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    sessionService.deleteSession(usernameForToken);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, usernameForToken, Constants.USER_LOGOUT, true));
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, usernameForToken, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, usernameForToken, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody UserSignUpInfo signUpInfo) {
        String actionName = "UserControllerImpl.signUp";

        try {
            try {
                UserValidator.validateSignUpInfo(signUpInfo);
                userService.signUp(signUpInfo);

                auditService.addEvent(new AuditItem(signUpInfo.getUsername(), actionName, "", Constants.USER_ACCOUNT_CREATED, true));
                return new ResponseEntity<>(Constants.USER_ACCOUNT_CREATED, HttpStatus.OK);

            } catch (InvalidSignUpInfoException info_exception) {
                auditService.addEvent(new AuditItem(signUpInfo.getUsername(), actionName, "", info_exception.getMessage(), false));
                return new ResponseEntity<>(info_exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(signUpInfo.getUsername(), actionName, "", serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("username") String username, @RequestHeader(value = "token") String token) {
        String actionName = "UserControllerImpl.get";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    User user = userService.get(username);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, username, Constants.USER_GET, true));
                    return new ResponseEntity<>(user, HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, username, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, username, notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, username, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @Override
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestHeader(value = "token") String token) {
        String actionName = "UserControllerImpl.getAll";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    List<User> userList = userService.getAllUsers();

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "", Constants.USER_GET_ALL, true));
                    return new ResponseEntity<>(userList, HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "", Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "", notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "", serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users/confirmation/create/{username}", method = RequestMethod.GET)
    public ResponseEntity validateUserAccount(@PathVariable("username") String username, @RequestParam(value = "id", required = true) String actionID) {
        String actionName = "UserControllerImpl.validateUserAccount";

        try {
            try {
                userService.validateUserAccount(username, actionID);

                auditService.addEvent(new AuditItem(username, actionName, "", Constants.USER_VALIDATED_ACCOUNT, true));
                return new ResponseEntity<>(HttpStatus.OK);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(username, actionName, "", notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(username, actionName, "", serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users/confirmation/reset/{username}", method = RequestMethod.GET)
    public ResponseEntity resetPasswordRequest(@PathVariable("username") String username) {
        String actionName = "UserControllerImpl.resetPasswordRequest";

        try {
            try {
                userService.resetPasswordRequest(username);

                auditService.addEvent(new AuditItem(username, actionName, "", Constants.USER_PASSWORD_RESET_REQUEST, true));
                return new ResponseEntity<>(HttpStatus.OK);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(username, actionName, "", notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(username, actionName, "", serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users/password/reset/{username}", method = RequestMethod.GET)
    public ResponseEntity resetPassword(@PathVariable("username") String username, @RequestParam(value = "id", required = true) String actionID) {
        String actionName = "UserControllerImpl.resetPassword";

        try {
            try {
                userService.resetPasswordRequestHandler(username, actionID);

                auditService.addEvent(new AuditItem(username, actionName, "", Constants.USER_PASSWORD_RESET, true));
                return new ResponseEntity<>(HttpStatus.OK);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(username, actionName, "", notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(username, actionName, "", serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/users/password/change", method = RequestMethod.POST)
    public ResponseEntity changePassword(@RequestBody PasswordChange passwordChange, @RequestHeader(value = "token") String token) {
        String actionName = "UserControllerImpl.changePassword";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    UserValidator.validateLoginInfo(new UserLoginInfo(passwordChange.getUsername(), passwordChange.getNewPassword()));
                    userService.changePassword(passwordChange);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "", Constants.USER_PASSWORD_CHANGED, true));
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "", Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "", notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "", serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (InvalidLoginInfoException invalidInfo) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "", invalidInfo.getMessage(), false));
                return new ResponseEntity<>(invalidInfo.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
