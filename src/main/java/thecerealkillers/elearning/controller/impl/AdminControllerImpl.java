package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.controller.AdminController;
import thecerealkillers.elearning.model.AccountSuspensionInfo;
import thecerealkillers.elearning.model.ChangeAccountTypeInfo;
import thecerealkillers.elearning.model.UserSignUpInfo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


/**
 * Created by Dani.
 */
@RestController
@CrossOrigin
public class AdminControllerImpl implements AdminController {


    @Override
    public ResponseEntity createAccount(@RequestBody UserSignUpInfo newUser, String userRole, @RequestHeader(value = "token") String token) {
        return null;
    }

    @Override
    public ResponseEntity suspendAccount(@RequestBody AccountSuspensionInfo suspendInfo, @RequestHeader(value = "token") String token) {
        return null;
    }

    @Override
    public ResponseEntity reactivateAccount(@RequestBody AccountSuspensionInfo reactivateInfo, @RequestHeader(value = "token") String token) {
        return null;
    }

    @Override
    public ResponseEntity changeAccountType(@RequestBody ChangeAccountTypeInfo accountTypeInfo, @RequestHeader(value = "token") String token) {
        return null;
    }

    @Override
    public ResponseEntity<List<String>> getAudit(@RequestHeader(value = "token") String token) {
        return null;
    }

    @Override
    public ResponseEntity<List<String>> getAuditForUser(@PathVariable("username") String username, @RequestHeader(value = "token") String token) {
        return null;
    }
}
