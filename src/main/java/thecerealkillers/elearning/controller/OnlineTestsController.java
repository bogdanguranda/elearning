package thecerealkillers.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecerealkillers.elearning.model.OnlineTest;

/**
 * Created with love by Lucian and @Pi on 29.12.2015.
 */

@RestController
public interface OnlineTestsController {

    @RequestMapping(value = "/professor/tests/create", method = RequestMethod.POST)
    ResponseEntity createTest(@RequestBody OnlineTest onlineTest, @RequestHeader(value = "token") String token);

}
