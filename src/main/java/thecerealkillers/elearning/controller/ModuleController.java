package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thecerealkillers.elearning.model.Module;


/**
 * Created by cuvidk on 12/22/2015.
 * .
 */
public interface ModuleController {

    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.POST)
    ResponseEntity<?> createModule(
            @RequestBody Module module,
            @RequestHeader("token") String token,
            @PathVariable("courseTitle") String title);


    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteModule(
            @RequestHeader("token") String token,
            @PathVariable("courseTitle") String courseTitle,
            @RequestParam("moduleTitle") String moduleTitle);


    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.GET)
    ResponseEntity<?> getAll(
            @PathVariable("courseTitle") String courseTitle,
            @RequestHeader("token") String token);


    @RequestMapping(value = "/courses/{courseTitle}/modules/{moduleTitle}", method = RequestMethod.GET)
    ResponseEntity<?> get(
            @RequestHeader("token") String token,
            @PathVariable("courseTitle") String courseTitle,
            @PathVariable("moduleTitle") String moduleTitle);


    @RequestMapping(value = "/courses/{courseTitle}/modules", method = RequestMethod.PUT)
    ResponseEntity<?> renameModule(
            @RequestHeader("token") String token,
            @PathVariable("courseTitle") String courseTitle,
            @RequestParam("currentTitle") String currentTitle,
            @RequestParam("newTitle") String newTitle);
}
