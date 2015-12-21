package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.controller.AdminController;
import thecerealkillers.elearning.model.AccountSuspensionInfo;
import thecerealkillers.elearning.model.ChangeAccountTypeInfo;
import thecerealkillers.elearning.model.UserSignUpInfo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;


/**
 * Created by Dani.
 */
@RestController
@CrossOrigin
public class AdminControllerImpl implements AdminController {

    @Override
    public ResponseEntity createAccount(@RequestBody UserSignUpInfo newUser, String userRole) {
        return null;
    }

    @Override
    public ResponseEntity suspendAccount(@RequestBody AccountSuspensionInfo suspendInfo) {
        return null;
    }

    @Override
    public ResponseEntity reactivateAccount(@RequestBody AccountSuspensionInfo reactivateInfo) {
        return null;
    }

    @Override
    public ResponseEntity changeAccountType(@RequestBody ChangeAccountTypeInfo accountTypeInfo) {
        return null;
    }

    @Override
    public ResponseEntity<List<String>> getAudit() {
        return null;
    }

    @Override
    public ResponseEntity<List<String>> getAuditForUser(@PathVariable("username") String username) {
        return null;
    }
}
