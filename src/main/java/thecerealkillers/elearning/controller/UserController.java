package thecerealkillers.elearning.controller;


import thecerealkillers.elearning.model.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


/**
 * Created by cuvidk on 11/8/2015.
 * Modified by Dani.
 */
public interface UserController {

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    ResponseEntity<?> authenticate(
            @RequestBody UserLoginInfo loginInfo);


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    ResponseEntity<?> signUp(
            @RequestBody UserSignUpInfo signUpInfo);


    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    ResponseEntity<?> get(
            @PathVariable("username") String username,
            @RequestHeader(value = "token") String token);


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    ResponseEntity<?> getAll(
            @RequestHeader(value = "token") String token);


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
