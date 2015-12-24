package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.controller.GroupsController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.GroupsService;
import thecerealkillers.elearning.model.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ResponseEntity<?> getGroups(@RequestHeader(value = "token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("GroupsControllerImpl.getGroups", crtUserRole)) {
                    List<Group> groupList = groupsService.getAll();

                    return new ResponseEntity<>(groupList, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
