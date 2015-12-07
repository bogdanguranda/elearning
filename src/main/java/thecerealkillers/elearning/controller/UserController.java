package thecerealkillers.elearning.controller;


import thecerealkillers.elearning.model.PasswordChange;
import thecerealkillers.elearning.model.UserSignUpInfo;
import thecerealkillers.elearning.model.UserLoginInfo;
import thecerealkillers.elearning.model.User;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


/**
 * Created by cuvidk on 11/8/2015.
 * Modified by Dani
 * - Methods added : validateUserAccount, resetPasswordRequest, resetPassword, changePassword
 */
public interface UserController {

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    ResponseEntity<String> authenticate(@RequestBody UserLoginInfo loginInfo);

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    ResponseEntity<String> signUp(@RequestBody UserSignUpInfo signUpInfo);

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    ResponseEntity<User> get(
            @PathVariable("username") String username,
            @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    ResponseEntity<List<User>> getAll(@RequestHeader(value = "token") String token);

    @RequestMapping(value = "/users/confirmation/create/{username}", method = RequestMethod.GET)
    ResponseEntity validateUserAccount(
            @PathVariable("username") String username,
            @RequestParam(value = "id", required = true) String actionID);

    @RequestMapping(value = "/users/confirmation/reset/{username}", method = RequestMethod.GET)
    ResponseEntity resetPasswordRequest(
            @PathVariable("username") String username);

    @RequestMapping(value = "/users/password/reset/{username}", method = RequestMethod.GET)
    ResponseEntity resetPassword(
            @PathVariable("username") String username,
            @RequestParam(value = "id", required = true) String actionID);

    @RequestMapping(value = "/users/password/change", method = RequestMethod.POST)
    ResponseEntity changePassword(
            @RequestBody PasswordChange passwordChange,
            @RequestHeader(value = "token") String token);
}
