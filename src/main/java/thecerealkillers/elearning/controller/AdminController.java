package thecerealkillers.elearning.controller;


import thecerealkillers.elearning.model.AccountSuspensionInfo;
import thecerealkillers.elearning.model.ChangeAccountTypeInfo;
import thecerealkillers.elearning.model.AdminSignUpInfo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


/**
 * Created by Dani.
 */
public interface AdminController {

    /**
     * Creates a new account
     *
     * @param newUser = Information required to create the new account.
     */
    @RequestMapping(value = "/admin/createAccount", method = RequestMethod.POST)
    ResponseEntity createAccount(
            @RequestBody AdminSignUpInfo newUser,
            @RequestHeader(value = "token") String token);


    /**
     * Suspends a user's account.
     *
     * @param suspendInfo = Information required to suspend an account.
     */
    @RequestMapping(value = "/admin/suspendAccount", method = RequestMethod.POST)
    ResponseEntity suspendAccount(
            @RequestBody AccountSuspensionInfo suspendInfo,
            @RequestHeader(value = "token") String token);


    /**
     * Reactivates a user's account.
     *
     * @param reactivateInfo = Information required to reactivate an account.
     */
    @RequestMapping(value = "/admin/reactivateAccount", method = RequestMethod.POST)
    ResponseEntity reactivateAccount(
            @RequestBody AccountSuspensionInfo reactivateInfo,
            @RequestHeader(value = "token") String token);


    /**
     * Changes an account's role.
     *
     * @param accountTypeInfo = Information required to change an account's role.
     */
    @RequestMapping(value = "/admin/changeAccountType", method = RequestMethod.POST)
    ResponseEntity changeAccountType(
            @RequestBody ChangeAccountTypeInfo accountTypeInfo,
            @RequestHeader(value = "token") String token);
}
