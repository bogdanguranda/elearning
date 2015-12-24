package thecerealkillers.elearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.controller.ModuleController;
import thecerealkillers.elearning.exceptions.InvalidModuleException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.Module;
import thecerealkillers.elearning.service.ModuleService;
import thecerealkillers.elearning.service.PermissionService;
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.validator.ModuleValidator;

import java.util.List;

/**
 * Created by cuvidk on 12/22/2015.
 * Modified by Dani.
 */
@RestController
public class ModuleControllerImpl implements ModuleController {

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
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ModuleControllerImpl.createModule", crtUserRole)) {

                    //This check is mandatory...It's to much to explain
                    //here. I'll explain face to face or smth.
                    if (!courseTitle.equals(module.getCourse())) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }

                    ModuleValidator.validateModule(module);

                    moduleService.storeModule(module);

                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (InvalidModuleException moduleException) {
            return new ResponseEntity<>(moduleException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteModule(@RequestHeader String token, @PathVariable("courseTitle") String courseTitle,
                                          @RequestParam("moduleTitle") String moduleTitle) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ModuleControllerImpl.deleteModule", crtUserRole)) {
                    Module module = new Module();
                    module.setCourse(courseTitle);
                    module.setTitle(moduleTitle);

                    moduleService.deleteModule(module);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestHeader("token") String token) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ModuleControllerImpl.getAll", crtUserRole)) {
                    return new ResponseEntity<>(moduleService.getAll(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules/{moduleTitle}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@RequestHeader("token") String token, @PathVariable("courseTitle") String courseTitle,
                                 @PathVariable("moduleTitle") String moduleTitle) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ModuleControllerImpl.get", crtUserRole)) {
                    Module module = moduleService.get(moduleTitle, courseTitle);
                    return new ResponseEntity<>(module, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.PUT)
    public ResponseEntity<?> renameModule(@RequestHeader("token") String token, @PathVariable("courseTitle") String courseTitle,
                                          @RequestParam("currentTitle") String currentTitle, @RequestParam("newTitle") String newTitle) {
        try {
            if (sessionService.isSessionActive(token)) {
                String crtUserRole = sessionService.getUserRoleByToken(token);

                if (permissionService.isOperationAvailable("ModuleControllerImpl.renameModule", crtUserRole)) {
                    Module module = moduleService.get(currentTitle, courseTitle);
                    moduleService.update(module, newTitle);

                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
