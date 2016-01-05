package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.Answer;
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

    @RequestMapping(value = "/professor/tests/{course}/{test}", method = RequestMethod.GET)
    ResponseEntity<?> getStudentPoints(@RequestHeader(value = "token") String token,
                                       @PathVariable(value = "course") String course,
                                       @PathVariable(value = "test") String test,
                                       @RequestParam(value = "username") String username);

    @RequestMapping(value = "/{course}/tests/{test}", method = RequestMethod.GET)
    ResponseEntity<?> getOnlineTest(@RequestHeader(value = "token") String token,
                                    @PathVariable(value = "course") String course,
                                    @PathVariable(value = "test") String test);
}
