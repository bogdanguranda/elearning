package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.Question;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 29.12.2015.
 */

@RestController
public interface OnlineTestsController {

    @RequestMapping(value = "/professor/tests/create", method = RequestMethod.POST)
    ResponseEntity createTest(@RequestHeader(value = "token") String token,
                              @RequestParam(value = "title", required = true) String title,
                              @RequestParam(value = "course", required = true) String course,
                              @RequestParam(value = "attempts", required = true) String attempts,
                              @RequestBody List<Question> questionList);

    @RequestMapping(value = "/professor/tests", method = RequestMethod.DELETE)
    ResponseEntity deleteTest(@RequestHeader(value = "token") String token,
                              @RequestParam(value = "title") String title,
                              @RequestParam(value = "course") String course);
}
