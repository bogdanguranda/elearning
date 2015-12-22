package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.AccountSuspensionInfo;
import thecerealkillers.elearning.model.ChangeAccountTypeInfo;
import thecerealkillers.elearning.utilities.PermissionsExpert;
import thecerealkillers.elearning.controller.AdminController;
import thecerealkillers.elearning.utilities.SessionExpert;
import thecerealkillers.elearning.service.UserRoleService;
import thecerealkillers.elearning.model.UserSignUpInfo;
import thecerealkillers.elearning.service.AdminService;
import thecerealkillers.elearning.utilities.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;


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

    @Override
//    public ResponseEntity createAccount(@RequestBody UserSignUpInfo newUser, String userRole, @RequestHeader(value = "token") String token) {       //This is how final version will be.
    public ResponseEntity createAccount(@RequestBody UserSignUpInfo newUser, String userRole) {         //Dev only.
        String token = "";         //Dev only.

        try {
            if (SessionExpert.isSessionActive(token)) {
                String crtUserRole = SessionExpert.getUserRoleByToken(token);

                if (crtUserRole.compareTo(Constants.ADMIN) == 0 && PermissionsExpert.isOperationAvailable("createAccount", crtUserRole)) {
                    adminService.createAccount(newUser, userRole);

                    return new ResponseEntity<>(HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
//   public ResponseEntity suspendAccount(@RequestBody AccountSuspensionInfo suspendInfo, @RequestHeader(value = "token") String token) {       //This is how final version will be.
    public ResponseEntity suspendAccount(@RequestBody AccountSuspensionInfo suspendInfo) {         //Dev only.
        String token = "";         //Dev only.

        try {
            String userRole = userRoleService.getRole(suspendInfo.getAccountUsername());

            if (userRole.compareTo(Constants.ADMIN) != 0) {
                if (SessionExpert.isSessionActive(token)) {
                    String crtUserRole = SessionExpert.getUserRoleByToken(token);

                    if (crtUserRole.compareTo(Constants.ADMIN) == 0 && PermissionsExpert.isOperationAvailable("suspendAccount", crtUserRole)) {
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
//    public ResponseEntity reactivateAccount(@RequestBody AccountSuspensionInfo reactivateInfo, @RequestHeader(value = "token") String token) {       //This is how final version will be.
    public ResponseEntity reactivateAccount(@RequestBody AccountSuspensionInfo reactivateInfo) {         //Dev only.
        String token = "";         //Dev only.

        try {
            String userRole = userRoleService.getRole(reactivateInfo.getAccountUsername());

            if (userRole.compareTo(Constants.ADMIN) != 0) {
                if (SessionExpert.isSessionActive(token)) {
                    String crtUserRole = SessionExpert.getUserRoleByToken(token);

                    if (crtUserRole.compareTo(Constants.ADMIN) == 0 && PermissionsExpert.isOperationAvailable("reactivateAccount", crtUserRole)) {
                        adminService.reactivateAccount(reactivateInfo);

                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                    }
                }
            }

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
//    public ResponseEntity changeAccountType(@RequestBody ChangeAccountTypeInfo accountTypeInfo, @RequestHeader(value = "token") String token) {       //This is how final version will be.
    public ResponseEntity changeAccountType(@RequestBody ChangeAccountTypeInfo accountInfo) {         //Dev only.
        String token = "";         //Dev only.

        try {
            String userRole = userRoleService.getRole(accountInfo.getAccountUsername());

            if (userRole.compareTo(Constants.ADMIN) != 0) {
                if (SessionExpert.isSessionActive(token)) {
                    String crtUserRole = SessionExpert.getUserRoleByToken(token);

                    if (crtUserRole.compareTo(Constants.ADMIN) == 0 && PermissionsExpert.isOperationAvailable("changeAccountType", crtUserRole)) {
                        adminService.changeAccountType(accountInfo);

                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                    }
                }
            }

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
//    public ResponseEntity<List<String>> getAudit(@RequestHeader(value = "token") String token) {       //This is how final version will be.
    public ResponseEntity<List<String>> getAudit() {         //Dev only.
        String token = "";         //Dev only.

        try {
            if (SessionExpert.isSessionActive(token)) {
                String crtUserRole = SessionExpert.getUserRoleByToken(token);

                if (crtUserRole.compareTo(Constants.ADMIN) == 0 && PermissionsExpert.isOperationAvailable("getAudit", crtUserRole)) {
                    adminService.getAudit();

                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
//    public ResponseEntity<List<String>> getAuditForUser(@PathVariable("username") String username, @RequestHeader(value = "token") String token) {       //This is how final version will be.
    public ResponseEntity<List<String>> getAuditForUser(@PathVariable("username") String username) {         //Dev only.
        String token = "";         //Dev only.

        try {
            if (SessionExpert.isSessionActive(token)) {
                String crtUserRole = SessionExpert.getUserRoleByToken(token);

                if (crtUserRole.compareTo(Constants.ADMIN) == 0 && PermissionsExpert.isOperationAvailable("getAuditForUser", crtUserRole)) {
                    adminService.getAuditForUser(username);

                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}