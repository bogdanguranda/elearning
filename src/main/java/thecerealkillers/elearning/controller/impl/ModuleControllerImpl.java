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
import thecerealkillers.elearning.validator.Validator;

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
}
