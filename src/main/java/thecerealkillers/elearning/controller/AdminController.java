package thecerealkillers.elearning.controller;


import thecerealkillers.elearning.model.AccountSuspensionInfo;
import thecerealkillers.elearning.model.ChangeAccountTypeInfo;
import thecerealkillers.elearning.model.AdminSignUpInfo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


/**
 * Created by Dani.
 */
public interface AdminController {

    /**
     * Creates a new account
     *
     * @param newUser  = Information required to create the new account.
     */
    @RequestMapping(value = "/admin/createAccount", method = RequestMethod.POST)
//    ResponseEntity createAccount(@RequestBody AdminSignUpInfo newUser, @RequestHeader(value="token") String token);       //This is how final version will be.
    ResponseEntity createAccount(@RequestBody AdminSignUpInfo newUser);         //Dev only.

    /**
     * Suspends a user's account.
     *
     * @param suspendInfo = Information required to suspend an account.
     */
    @RequestMapping(value = "/admin/suspendAccount", method = RequestMethod.POST)
//    ResponseEntity suspendAccount(@RequestBody AccountSuspensionInfo suspendInfo, @RequestHeader(value="token") String token);       //This is how final version will be.
    ResponseEntity suspendAccount(@RequestBody AccountSuspensionInfo suspendInfo);         //Dev only.

    /**
     * Reactivates a user's account.
     *
     * @param reactivateInfo = Information required to reactivate an account.
     */
    @RequestMapping(value = "/admin/reactivateAccount", method = RequestMethod.POST)
//    ResponseEntity reactivateAccount(@RequestBody AccountSuspensionInfo reactivateInfo, @RequestHeader(value="token") String token);       //This is how final version will be.
    ResponseEntity reactivateAccount(@RequestBody AccountSuspensionInfo reactivateInfo);         //Dev only.

    /**
     * Changes an account's role.
     *
     * @param accountInfo = Information required to change an account's role.
     */
    @RequestMapping(value = "/admin/changeAccountType", method = RequestMethod.POST)
//    ResponseEntity changeAccountType(@RequestBody ChangeAccountTypeInfo accountTypeInfo, @RequestHeader(value="token") String token);       //This is how final version will be.
    ResponseEntity changeAccountType(@RequestBody ChangeAccountTypeInfo accountInfo);         //Dev only.

    /**
     * Retireves the audit.
     */
    @RequestMapping(value = "/admin/getAudit", method = RequestMethod.GET)
//    ResponseEntity<List<String>> getAudit(@RequestHeader(value="token") String token);       //This is how final version will be.
    ResponseEntity<List<String>> getAudit();         //Dev only.

    /**
     * Retrieves the audit for a specific user
     *
     * @param username = User for which to retrieve audit.
     */
    @RequestMapping(value = "/admin/getAuditForUser/{username}", method = RequestMethod.GET)
//    ResponseEntity<List<String>> getAuditForUser(@PathVariable("username") String username, @RequestHeader(value="token") String token);       //This is how final version will be.
    ResponseEntity<List<String>> getAuditForUser(@PathVariable("username") String username);         //Dev only.
}
