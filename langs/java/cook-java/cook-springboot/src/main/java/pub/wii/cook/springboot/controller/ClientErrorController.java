package pub.wii.cook.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pub.wii.cook.springboot.exception.BadRequestException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("client")
public class ClientErrorController {
    @RequestMapping(value = "referrer",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object referrer(HttpServletRequest request, @RequestParam String value) {
        throw new BadRequestException(HttpStatus.BAD_REQUEST);
    }
}
