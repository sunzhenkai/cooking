package pub.wii.cook.springboot.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pub.wii.cook.java.model.Response;
import pub.wii.cook.springboot.service.PingEchoService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("aspect")
public class AspectTestController {
    @RequestMapping(value = "echo",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response<String> echo(HttpServletRequest request, @RequestParam("echo") String ec) {
        return Response.ok(new PingEchoService().pe(ec));
    }
}
