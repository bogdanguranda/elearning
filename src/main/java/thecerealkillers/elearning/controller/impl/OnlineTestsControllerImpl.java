package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.OnlineTestsController;
import thecerealkillers.elearning.exceptions.InvalidOnlineTestException;
import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.AuditItem;
import thecerealkillers.elearning.model.OnlineTest;
import thecerealkillers.elearning.service.AuditService;
import thecerealkillers.elearning.service.OnlineTestsService;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.utilities.Constants;
import thecerealkillers.elearning.validator.OnlineTestValidator;

/**
 * Created with love by Lucian and @Pi on 29.12.2015.
 */

@RestController
@CrossOrigin
public class OnlineTestsControllerImpl implements OnlineTestsController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private OnlineTestsService onlineTestsService;

    @Override
    @RequestMapping(value = "/professor/tests/create", method = RequestMethod.POST)
    public ResponseEntity createTest(@RequestBody OnlineTest onlineTest, @RequestHeader(value = "token") String token) {

        String actionName = "OnlineTestsControllerImpl.createTest";
        try {
            if (!sessionService.isSessionActive(token)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {

                    OnlineTestValidator.validate(onlineTest);

                    onlineTestsService.createTest(onlineTest);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, onlineTest.toString(), Constants.ONLINE_TEST_NEW_TEST, true));
                    return new ResponseEntity<>(HttpStatus.CREATED);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, onlineTest.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (NotFoundException notFoundException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, onlineTest.toString(), notFoundException.getMessage(), false));
                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, onlineTest.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            } catch (InvalidOnlineTestException ex) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, onlineTest.toString(), ex.getMessage(), false));
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
