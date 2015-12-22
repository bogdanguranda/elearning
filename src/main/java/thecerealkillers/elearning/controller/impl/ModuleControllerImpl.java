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
import thecerealkillers.elearning.service.SessionService;
import thecerealkillers.elearning.validator.ModuleValidator;

import java.util.List;

/**
 * Created by cuvidk on 12/22/2015.
 */
@RestController
public class ModuleControllerImpl implements ModuleController {
    @Autowired
    ModuleService moduleService;
    @Autowired
    SessionService sessionService;

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.POST)
    public ResponseEntity<String> createModule(@RequestBody Module module, @RequestHeader String token,
                                               @PathVariable("courseTitle") String courseTitle) {
        try {
            sessionService.getSessionByToken(token);

            //This check is mandatory...It's to much to explain
            //here. I'll explain face to face or smth.
            if (!courseTitle.equals(module.getCourse())) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            ModuleValidator.validateModule(module);

            moduleService.storeModule(module);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (InvalidModuleException moduleException) {
            return new ResponseEntity<>(moduleException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteModule(@RequestHeader String token, @PathVariable("courseTitle") String courseTitle,
                                               @RequestParam("moduleTitle") String moduleTitle) {
        try {
            sessionService.getSessionByToken(token);

            Module module = new Module();
            module.setCourse(courseTitle);
            module.setTitle(moduleTitle);

            moduleService.deleteModule(module);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.GET)
    public ResponseEntity<List<Module>> getAll(@RequestHeader("token") String token) {
        try {
            sessionService.getSessionByToken(token);

            return new ResponseEntity<>(moduleService.getAll(), HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules/{moduleTitle}", method = RequestMethod.GET)
    public ResponseEntity<Module> get(@RequestHeader("token") String token, @PathVariable("courseTitle") String courseTitle,
                                      @PathVariable("moduleTitle") String moduleTitle) {
        try {
            sessionService.getSessionByToken(token);

            Module module = moduleService.get(moduleTitle, courseTitle);
            return new ResponseEntity<>(module, HttpStatus.OK);
        } catch (ServiceException serviceException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.PUT)
    public ResponseEntity<String> renameModule(@RequestHeader("token") String token, @PathVariable("courseTitle") String courseTitle,
                                               @RequestParam("currentTitle") String currentTitle, @RequestParam("newTitle") String newTitle)
    {
        try {
            sessionService.getSessionByToken(token);

            Module module = moduleService.get(currentTitle, courseTitle);
            moduleService.update(module, newTitle);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException serviceExcetion) {
            return new ResponseEntity<>(serviceExcetion.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
