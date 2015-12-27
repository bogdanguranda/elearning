package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.AccountSuspensionInfo;
import thecerealkillers.elearning.model.ChangeAccountTypeInfo;
import thecerealkillers.elearning.controller.AdminController;
import thecerealkillers.elearning.model.AdminSignUpInfo;
import thecerealkillers.elearning.utilities.Constants;
import thecerealkillers.elearning.model.AuditItem;
import thecerealkillers.elearning.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


/**
 * Created by Dani.
 */
@RestController
@CrossOrigin
public class AdminControllerImpl implements AdminController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuditService auditService;


    @Override
    @RequestMapping(value = "/admin/createAccount", method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody AdminSignUpInfo newUser,
                                        @RequestHeader(value = "token") String token) {
        String actionName = "AdminControllerImpl.createAccount";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    adminService.createAccount(newUser);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, newUser.toString(), Constants.ADMIN_NEW_ACCOUNT, true));
                    return new ResponseEntity<>(HttpStatus.CREATED);
                } else {

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, newUser.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, newUser.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/admin/suspendAccount", method = RequestMethod.POST)
    public ResponseEntity suspendAccount(@RequestBody AccountSuspensionInfo suspendInfo,
                                         @RequestHeader(value = "token") String token) {
        String actionName = "AdminControllerImpl.suspendAccount";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    adminService.suspendAccount(suspendInfo);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, suspendInfo.toString(), Constants.ADMIN_SUSPEND, true));
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, suspendInfo.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, suspendInfo.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, suspendInfo.toString(), notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/admin/reactivateAccount", method = RequestMethod.POST)
    public ResponseEntity reactivateAccount(@RequestBody AccountSuspensionInfo reactivateInfo,
                                            @RequestHeader(value = "token") String token) {
        String actionName = "AdminControllerImpl.reactivateAccount";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    adminService.reactivateAccount(reactivateInfo);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, reactivateInfo.toString(), Constants.ADMIN_REACTIVATE, true));
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, reactivateInfo.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, reactivateInfo.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, reactivateInfo.toString(), notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/admin/changeAccountType", method = RequestMethod.POST)
    public ResponseEntity changeAccountType(@RequestBody ChangeAccountTypeInfo accountTypeInfo,
                                            @RequestHeader(value = "token") String token) {
        String actionName = "AdminControllerImpl.changeAccountType";

        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    adminService.changeAccountType(accountTypeInfo);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, accountTypeInfo.toString(), Constants.ADMIN_CHANGE_TYPE, true));
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, accountTypeInfo.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, accountTypeInfo.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, accountTypeInfo.toString(), notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}