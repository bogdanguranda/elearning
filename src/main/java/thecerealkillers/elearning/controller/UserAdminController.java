package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import thecerealkillers.elearning.model.User;

import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
public interface UserAdminController {
    ResponseEntity<String> authenticate(@RequestParam("username") String username,
                                        @RequestParam("password") String password);
    ResponseEntity<List<User>> getAll();
}
