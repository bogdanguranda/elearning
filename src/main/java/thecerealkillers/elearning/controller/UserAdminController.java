package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.User;
import thecerealkillers.elearning.model.UserOM;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
@RestController
public interface UserAdminController {

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    ResponseEntity<String> authenticate(@RequestBody UserOM user) throws NoSuchAlgorithmException;

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    ResponseEntity<User> get(@PathVariable("username") String username);

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    ResponseEntity<List<User>> getAll();
}
