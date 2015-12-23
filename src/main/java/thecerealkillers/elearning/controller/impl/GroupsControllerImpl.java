package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.GroupsController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Group;
import thecerealkillers.elearning.service.GroupsService;
import thecerealkillers.elearning.service.SessionService;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 22.12.2015.
 */

@RestController
@CrossOrigin
public class GroupsControllerImpl implements GroupsController {

    @Autowired
    private GroupsService groupsService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ResponseEntity<List<Group>> getGroups(@RequestHeader(value = "token") String token) {
        try {
            sessionService.getSessionByToken(token);
            List<Group> groupList = groupsService.getAll();
            return new ResponseEntity<>(groupList, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
