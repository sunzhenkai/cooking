package pub.wii.cook.springboot.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("cors")
public class CORSController {

    @CrossOrigin(maxAge = 1
            , methods = {RequestMethod.POST}
    )
    @RequestMapping(value = "check"
            , method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<String> check() {
        System.out.println("call check");
        return ResponseEntity.ok("ok");
    }
}
