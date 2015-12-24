package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.Module;

import java.util.List;

/**
 * Created by cuvidk on 12/22/2015.
 */
@RestController
public interface ModuleController {
    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.POST)
    ResponseEntity<String> createModule(@RequestBody Module module, @RequestHeader("token") String token,
                                        @PathVariable("courseTitle") String title);

    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.DELETE)
    ResponseEntity<String> deleteModule(@RequestHeader("token") String token, @PathVariable("courseTitle") String courseTitle,
                                        @RequestParam("moduleTitle") String moduleTitle);

    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.GET)
    ResponseEntity<List<Module>> getAll(@RequestHeader("token") String token, @PathVariable("courseTitle") String courseTitle);

    @RequestMapping(value = "/courses/{courseTitle}/modules/{moduleTitle}", method = RequestMethod.GET)
    ResponseEntity<Module> get(@RequestHeader("token") String token, @PathVariable("courseTitle") String courseTitle,
                               @PathVariable("moduleTitle") String moduleTitle);

    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.PUT)
    ResponseEntity<String> renameModule(@RequestHeader("token") String token, @PathVariable("courseTitle") String courseTitle,
                                        @RequestParam("currentTitle") String currentTitle,
                                        @RequestParam("newTitle") String newTitle);
}
