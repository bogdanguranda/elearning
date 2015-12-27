package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.controller.GroupsController;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.GroupsService;
import thecerealkillers.elearning.service.AuditService;
import thecerealkillers.elearning.utilities.Constants;
import thecerealkillers.elearning.model.AuditItem;
import thecerealkillers.elearning.model.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * Created with love by Lucian and @Pi on 22.12.2015.
 * Modified by Dani.
 */

@RestController
@CrossOrigin
public class GroupsControllerImpl implements GroupsController {

    @Autowired
    private AuditService auditService;

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ResponseEntity<?> getGroups(@RequestHeader(value = "token") String token) {
        String actionName = "GroupsControllerImpl.getGroups";

        try {
            if (!sessionService.isSessionActive(token)) {
                auditService.addEvent(new AuditItem(token, actionName, "", Constants.SESSION_EXPIRED, false));
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    List<Group> groupList = groupsService.getAll();

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "", Constants.GROUPS_GET_ALL, true));
                    return new ResponseEntity<>(groupList, HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "", Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "", serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
