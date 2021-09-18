package pub.wii.cook.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pub.wii.cook.springboot.model.EchoReq;
import pub.wii.cook.springboot.model.EchoResponse;
import pub.wii.cook.springboot.redis.RedisTest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("cook")
public class CookController {

    RedisTest redisTest;

    @Value("${ext.name:not set}")
    private String external;

    @Value("${spring.config.additional-location:not set}")
    private String location;

    public CookController(RedisTest redisTest) {
        this.redisTest = redisTest;
    }

    @CrossOrigin(maxAge = 4800)
    @RequestMapping(value = "check",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("ok");
    }

    @RequestMapping(value = "echo",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EchoResponse> echo(HttpServletRequest request, @RequestBody EchoReq data) {
        System.out.println(request.getAttribute("key"));
        EchoResponse response = new EchoResponse();
        response.getExtra().put("foo", String.valueOf(request.getAttribute("foo")));
        response.getExtra().put("key", String.valueOf(request.getAttribute("key")));
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "redis-echo",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object redisEcho(HttpServletRequest request, @RequestParam("value") Integer value) {
        redisTest.setI("redis-echo", value);
        return redisTest.getI("redis-echo");
    }

    @RequestMapping(value = "props",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, String>> props() {
        Map<String, String> res = new HashMap<>();
        res.put("external", external);
        res.put("location", location);
        return ResponseEntity.ok(res);
    }
}
