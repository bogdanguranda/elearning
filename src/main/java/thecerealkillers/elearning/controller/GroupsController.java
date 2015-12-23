package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.Group;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 22.12.2015.
 */

@RestController
public interface GroupsController {

    /**
     * Get all groups from DB
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    ResponseEntity<List<Group>> getGroups(@RequestHeader(value = "token") String token);

    /**
     * Create a group
     *
     * @param group
     * @param token
     * @return
     */
    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    ResponseEntity createGroup(@RequestBody Group group, @RequestHeader(value = "token") String token);

    /**
     * Delete the specified group
     *
     * @param name
     * @param token
     * @return
     */
    @RequestMapping(value = "/groups", method = RequestMethod.DELETE)
    ResponseEntity deleteCourse(@RequestParam(value = "name", required = true) String name, @RequestHeader(value = "token") String token);

}
