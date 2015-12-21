package thecerealkillers.elearning.controller;


import thecerealkillers.elearning.model.AccountSuspensionInfo;
import thecerealkillers.elearning.model.ChangeAccountTypeInfo;
import thecerealkillers.elearning.model.UserSignUpInfo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;


/**
 * Created by Dani.
 */
public interface AdminController {

    @RequestMapping(value = "/admin/createAccount", method = RequestMethod.POST)
    ResponseEntity createAccount(@RequestBody UserSignUpInfo newUser, String userRole);

    @RequestMapping(value = "/admin/suspendAccount", method = RequestMethod.POST)
    ResponseEntity suspendAccount(@RequestBody AccountSuspensionInfo suspendInfo);

    @RequestMapping(value = "/admin/suspendAccount", method = RequestMethod.POST)
    ResponseEntity reactivateAccount(@RequestBody AccountSuspensionInfo reactivateInfo);

    @RequestMapping(value = "/admin/changeAccountType", method = RequestMethod.POST)
    ResponseEntity changeAccountType(@RequestBody ChangeAccountTypeInfo accountTypeInfo);

    @RequestMapping(value = "/admin/getAudit", method = RequestMethod.GET)
    ResponseEntity<List<String>> getAudit();

    @RequestMapping(value = "/admin/getAuditForUser/{username}", method = RequestMethod.GET)
    ResponseEntity<List<String>> getAuditForUser(@PathVariable("username") String username);
}
