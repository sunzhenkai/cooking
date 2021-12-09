package pub.wii.cook.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
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
// matchIfMissing=true: 如果没有定义变量也算满足条件
@ConditionalOnProperty(name = "metric.monitor.enable", matchIfMissing = true)
public class CookController {

    RedisTest redisTest;

    @Value("${ext.name:not set}")
    private String external;

    @Value("${rsa.public-key:not set}")
    private String publicKey;

    @Value("${rsa.private-key:not set}")
    private String privateKey;

    @Value("${metric.monitor.enable:false}")
    private String metric;

    @Value("prefix-${spring.config.additional-location:not set}")
    private String location;

    @Value("prefix-#{ T(java.net.InetAddress).getLocalHost().getHostName() }")
    private String hostName;

    @Value("#{T(pub.wii.cook.springboot.config.Constants).C_VAR}")
    private String foo;

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
        res.put("hostname", hostName);
        res.put("foo", foo);
        res.put("metric", metric);
        res.put("public-key", publicKey);
        res.put("private-key", privateKey);
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "referrer",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object referrer(HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("referrer", "https://www.so.com");
        responseHeaders.set("referer", "https://www.so.com");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("referrer");
    }
}
