package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.Module;

/**
 * Created by cuvidk on 12/22/2015.
 */
@RestController
public interface ModuleController {
    @RequestMapping(value = "/courses/{title}/modules", method = RequestMethod.POST)
    ResponseEntity<String> createModule(@RequestBody Module module, @RequestHeader String token,
                                        @PathVariable("title") String title);
}
