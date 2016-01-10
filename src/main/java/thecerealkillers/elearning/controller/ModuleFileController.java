package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by cuvidk on 12/29/2015.
 */
public interface ModuleFileController {
    @RequestMapping(value = "/courses/{courseTitle}/modules/{moduleTitle}/files", method = RequestMethod.POST)
    ResponseEntity<?> uploadFile(
            @RequestHeader("token") String token,
            @PathVariable("courseTitle") String courseTitle,
            @PathVariable("moduleTitle") String moduleTitle,
            @RequestParam("file") MultipartFile file);

    @RequestMapping(value = "/courses/{courseTitle}/modules/{moduleTitle}/files", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteFile(@RequestHeader("token") String token,
                                 @PathVariable("courseTitle") String courseTitle,
                                 @PathVariable("moduleTitle") String moduleTitle,
                                 @RequestParam("fileName") String fileNames);

    //!!!!  PLEASE NOTE THAT THIS REQUEST NEEDS TO END IN "/", if not using "/", !!!!
    //!!!!  a pathVariable like x.html will be truncated to x !!!!!
    @RequestMapping(value = "/courses/{courseTitle}/modules/{moduleTitle}/files/{fileName}/", method = RequestMethod.GET)
    ResponseEntity<?> getFile(@RequestHeader("token") String token,
                              @PathVariable("courseTitle") String courseTitle,
                              @PathVariable("moduleTitle") String moduleTitle,
                              @PathVariable("fileName") String fileName);

    @RequestMapping(value = "/courses/{courseTitle}/modules/{moduleTitle}/files", method = RequestMethod.GET)
    ResponseEntity<?> getAll(@RequestHeader("token") String token,
                             @PathVariable("courseTitle") String courseTitle,
                             @PathVariable("moduleTitle") String moduleTitle);

    @RequestMapping(value = "/courses/{courseTitle}/modules/{moduleTitle}/files", method = RequestMethod.PUT)
    ResponseEntity<?> renameFile(@RequestHeader("token") String token,
                                 @PathVariable("courseTitle") String courseTitle,
                                 @PathVariable("moduleTitle") String moduleTitle,
                                 @RequestParam("currentName") String currentName,
                                 @RequestParam("newName") String newName);
}
