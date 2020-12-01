package pub.wii.cook.springboot.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("upload")
public class UploaderController {

    @CrossOrigin
    @RequestMapping(value = "image",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String uploadPicture() {
        throw new RuntimeException("NO");
        // return "ok";
    }
}
