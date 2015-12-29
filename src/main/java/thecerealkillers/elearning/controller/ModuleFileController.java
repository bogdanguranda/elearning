package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by cuvidk on 12/29/2015.
 */
@RestController
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
}
