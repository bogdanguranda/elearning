package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.AccountSuspensionInfo;
import thecerealkillers.elearning.model.ChangeAccountTypeInfo;
import thecerealkillers.elearning.controller.AdminController;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.service.UserRoleService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.model.AdminSignUpInfo;
import thecerealkillers.elearning.service.AdminService;
import thecerealkillers.elearning.utilities.Constants;

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
    private AdminService adminService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PermissionService permissionService;


    @Override
    @RequestMapping(value = "/admin/createAccount", method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody AdminSignUpInfo newUser,
                                        @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("AdminControllerImpl.createAccount", crtUserRole)) {
                    adminService.createAccount(newUser);

                    return new ResponseEntity<>(HttpStatus.CREATED);
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
    @RequestMapping(value = "/admin/suspendAccount", method = RequestMethod.POST)
    public ResponseEntity suspendAccount(@RequestBody AccountSuspensionInfo suspendInfo,
                                         @RequestHeader(value = "token") String token) {
        try {
            String userRole = userRoleService.getRole(suspendInfo.getAccountUsername());

            if (userRole.compareTo(Constants.ADMIN) != 0) {
                if (sessionService.isSessionActive(token)) {
                    String crtUserRole = sessionService.getUserRoleByToken(token);

                    if (permissionService.isOperationAvailable("AdminControllerImpl.suspendAccount", crtUserRole)) {
                        adminService.suspendAccount(suspendInfo);

                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                    }
                }
            }

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/admin/reactivateAccount", method = RequestMethod.POST)
    public ResponseEntity reactivateAccount(@RequestBody AccountSuspensionInfo reactivateInfo,
                                            @RequestHeader(value = "token") String token) {
        try {
            String userRole = userRoleService.getRole(reactivateInfo.getAccountUsername());

            if (userRole.compareTo(Constants.ADMIN) != 0) {
                if (sessionService.isSessionActive(token)) {
                    String crtUserRole = sessionService.getUserRoleByToken(token);

                    if (permissionService.isOperationAvailable("AdminControllerImpl.reactivateAccount", crtUserRole)) {
                        adminService.reactivateAccount(reactivateInfo);

                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                    }
                }
            }

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/admin/changeAccountType", method = RequestMethod.POST)
    public ResponseEntity changeAccountType(@RequestBody ChangeAccountTypeInfo accountTypeInfo,
                                            @RequestHeader(value = "token") String token) {
        try {
            String userRole = userRoleService.getRole(accountTypeInfo.getAccountUsername());

            if (userRole.compareTo(Constants.ADMIN) != 0) {
                if (sessionService.isSessionActive(token)) {
                    String crtUserRole = sessionService.getUserRoleByToken(token);

                    if (permissionService.isOperationAvailable("AdminControllerImpl.changeAccountType", crtUserRole)) {
                        adminService.changeAccountType(accountTypeInfo);

                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                    }
                }
            }

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/admin/getAudit", method = RequestMethod.GET)
    public ResponseEntity<?> getAudit(@RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("AdminControllerImpl.getAudit", crtUserRole)) {
                    adminService.getAudit();

                    return new ResponseEntity<>(HttpStatus.OK);
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
    @RequestMapping(value = "/admin/getAuditForUser/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuditForUser(@PathVariable("username") String username, @RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("AdminControllerImpl.getAuditForUser", crtUserRole)) {
                    adminService.getAuditForUser(username);

                    return new ResponseEntity<>(HttpStatus.OK);
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
}