package thecerealkillers.elearning.controller.impl;


import thecerealkillers.elearning.exceptions.InvalidModuleException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.controller.ModuleController;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.validator.ModuleValidator;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.service.ModuleService;
import thecerealkillers.elearning.service.AuditService;
import thecerealkillers.elearning.utilities.Constants;
import thecerealkillers.elearning.model.AuditItem;
import thecerealkillers.elearning.model.Module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


/**
 * Created by cuvidk on 12/22/2015.
 * Modified by Dani.
 */
@RestController
public class ModuleControllerImpl implements ModuleController {

    @Autowired
    private AuditService auditService;

    @Autowired
    ModuleService moduleService;

    @Autowired
    SessionService sessionService;

    @Autowired
    private PermissionService permissionService;


    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.POST)
    public ResponseEntity<?> createModule(@RequestBody Module module, @RequestHeader String token,
                                          @PathVariable("courseTitle") String courseTitle) {
        String actionName = "ModuleControllerImpl.createModule";

        try {
            if (!sessionService.isSessionActive(token)) {
                auditService.addEvent(new AuditItem(Constants.USERNAME_OF_MOCK_USER_ACCOUNT, actionName, module.toString(), Constants.SESSION_EXPIRED, false));
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    //This check is mandatory...It's to much to explain
                    //here. I'll explain face to face or smth.
                    if (!courseTitle.equals(module.getCourse())) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }

                    ModuleValidator.validateModule(module);

                    moduleService.storeModule(module);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, module.toString(), Constants.MODULE_CREATE, true));
                    return new ResponseEntity<>(HttpStatus.CREATED);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, module.toString(), Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (InvalidModuleException moduleException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, module.toString(), moduleException.getMessage(), false));
                return new ResponseEntity<>(moduleException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

//            } catch (NotFoundException notFoundException) {
//	auditService.addEvent(new AuditItem(usernameForToken, actionName, module.toString(), notFoundException.getMessage(), false));
//                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, module.toString(), serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteModule(@RequestHeader String token, @PathVariable("courseTitle") String courseTitle,
                                          @RequestParam("moduleTitle") String moduleTitle) {
        String actionName = "ModuleControllerImpl.deleteModule";

        try {
            if (!sessionService.isSessionActive(token)) {
                auditService.addEvent(new AuditItem(Constants.USERNAME_OF_MOCK_USER_ACCOUNT, actionName, "Course title = " + courseTitle +
                        " | Module title = " + moduleTitle, Constants.SESSION_EXPIRED, false));
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    Module module = new Module();
                    module.setCourse(courseTitle);
                    module.setTitle(moduleTitle);

                    moduleService.deleteModule(module);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
                            " | Module title = " + moduleTitle, Constants.MODULE_DELETED, true));
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
                            " | Module title = " + moduleTitle, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
//            } catch (NotFoundException notFoundException) {
//                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
//                        " | Module title = " + moduleTitle, notFoundException.getMessage(), false));
//                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
                        " | Module title = " + moduleTitle, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@PathVariable("courseTitle") String courseTitle, @RequestHeader("token") String token) {
        String actionName = "ModuleControllerImpl.getAll";

        try {
            if (!sessionService.isSessionActive(token)) {
                auditService.addEvent(new AuditItem(Constants.USERNAME_OF_MOCK_USER_ACCOUNT, actionName, courseTitle, Constants.SESSION_EXPIRED, false));
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, courseTitle, Constants.MODULE_GET_ALL, true));
                    return new ResponseEntity<>(moduleService.getAll(courseTitle), HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, courseTitle, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
//            } catch (NotFoundException notFoundException) {
//	auditService.addEvent(new AuditItem(usernameForToken, actionName, courseTitle, notFoundException.getMessage(), false));
//                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, courseTitle, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules/{moduleTitle}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@RequestHeader("token") String token, @PathVariable("courseTitle") String courseTitle,
                                 @PathVariable("moduleTitle") String moduleTitle) {
        String actionName = "ModuleControllerImpl.get";

        try {
            if (!sessionService.isSessionActive(token)) {
                auditService.addEvent(new AuditItem(Constants.USERNAME_OF_MOCK_USER_ACCOUNT, actionName, "Course title = " + courseTitle +
                        " | Module title = " + moduleTitle, Constants.SESSION_EXPIRED, false));
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    Module module = moduleService.get(moduleTitle, courseTitle);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
                            " | Module title = " + moduleTitle, Constants.MODULE_GET, true));
                    return new ResponseEntity<>(module, HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
                            " | Module title = " + moduleTitle, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
//            } catch (NotFoundException notFoundException) {
//	auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
//                " | Module title = " + moduleTitle, notFoundException.getMessage(), false));
//                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
                        " | Module title = " + moduleTitle, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.PUT)
    public ResponseEntity<?> renameModule(@RequestHeader("token") String token, @PathVariable("courseTitle") String courseTitle,
                                          @RequestParam("currentTitle") String currentTitle, @RequestParam("newTitle") String newTitle) {
        String actionName = "ModuleControllerImpl.renameModule";

        try {
            if (!sessionService.isSessionActive(token)) {
                auditService.addEvent(new AuditItem(Constants.USERNAME_OF_MOCK_USER_ACCOUNT, actionName, "Course title = " + courseTitle +
                        " | Current module title = " + currentTitle + " | New module title = " + newTitle, Constants.SESSION_EXPIRED, false));
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String userRoleForToken = sessionService.getUserRoleByToken(token);
            String usernameForToken = sessionService.getUsernameByToken(token);

            try {
                if (permissionService.isOperationAvailable(actionName, userRoleForToken)) {
                    Module module = moduleService.get(currentTitle, courseTitle);
                    moduleService.update(module, newTitle);

                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
                            " | Current module title = " + currentTitle + " | New module title = " + newTitle, Constants.MODULE_RENAMED, true));
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
                            " | Current module title = " + currentTitle + " | New module title = " + newTitle, Constants.NO_PERMISSION, false));
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
//            } catch (NotFoundException notFoundException) {
//	auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
//                " | Current module title = " + currentTitle + " | New module title = " + newTitle, notFoundException.getMessage(), false));
//                return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

            } catch (ServiceException serviceException) {
                auditService.addEvent(new AuditItem(usernameForToken, actionName, "Course title = " + courseTitle +
                        " | Current module title = " + currentTitle + " | New module title = " + newTitle, serviceException.getMessage(), false));
                return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
