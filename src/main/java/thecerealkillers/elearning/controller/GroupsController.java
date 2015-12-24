package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import thecerealkillers.elearning.model.Group;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 22.12.2015.
 */

@RestController
public interface GroupsController {

    /**
     * Get all groups from DB
     */
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    ResponseEntity<?> getGroups(@RequestHeader(value = "token") String token);
}
