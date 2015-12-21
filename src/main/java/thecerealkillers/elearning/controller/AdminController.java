package thecerealkillers.elearning.controller;


import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.AccountSuspensionInfo;
import thecerealkillers.elearning.model.ChangeAccountTypeInfo;
import thecerealkillers.elearning.model.UserSignUpInfo;

import org.springframework.http.ResponseEntity;

import java.util.List;


/**
 * Created by Dani.
 */
public interface AdminController {

    @RequestMapping(value = "/admin/createAccount", method = RequestMethod.POST)
    ResponseEntity createAccount(@RequestBody UserSignUpInfo newUser, String userRole, @RequestHeader(value="token") String token);

    @RequestMapping(value = "/admin/suspendAccount", method = RequestMethod.POST)
    ResponseEntity suspendAccount(@RequestBody AccountSuspensionInfo suspendInfo, @RequestHeader(value="token") String token);

    @RequestMapping(value = "/admin/reactivateAccount", method = RequestMethod.POST)
    ResponseEntity reactivateAccount(@RequestBody AccountSuspensionInfo reactivateInfo, @RequestHeader(value="token") String token);

    @RequestMapping(value = "/admin/changeAccountType", method = RequestMethod.POST)
    ResponseEntity changeAccountType(@RequestBody ChangeAccountTypeInfo accountTypeInfo, @RequestHeader(value="token") String token);

    @RequestMapping(value = "/admin/getAudit", method = RequestMethod.GET)
    ResponseEntity<List<String>> getAudit(@RequestHeader(value="token") String token);

    @RequestMapping(value = "/admin/getAuditForUser/{username}", method = RequestMethod.GET)
    ResponseEntity<List<String>> getAuditForUser(@PathVariable("username") String username, @RequestHeader(value="token") String token);
}
