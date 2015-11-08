package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import thecerealkillers.elearning.model.User;

import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
public interface UserAdminController {
    ResponseEntity<List<User>> getAll();
}
