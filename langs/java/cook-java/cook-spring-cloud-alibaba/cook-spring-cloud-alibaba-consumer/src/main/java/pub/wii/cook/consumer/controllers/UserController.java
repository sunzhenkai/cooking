package pub.wii.cook.consumer.controllers;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.thrift.TException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pub.wii.cook.user.Request;
import pub.wii.cook.user.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    @DubboReference
    private UserService.Iface userService;

    @RequestMapping(value = "get",
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String get(@RequestParam("id") String id) throws TException {
        return String.valueOf(userService.getUser(new Request().setId(id)));
    }
}
