package pub.wii.cook.springboot.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pub.wii.cook.springboot.model.EchoReq;
import pub.wii.cook.springboot.redis.RedisTest;

@RestController
@RequestMapping("cook")
@AllArgsConstructor
public class CookController {

  RedisTest redisTest;

  @RequestMapping(value = "echo",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Object echo(HttpServletRequest request, @RequestBody EchoReq data) {
    return data;
  }

  @RequestMapping(value = "redis-echo",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Object redisEcho(HttpServletRequest request, @RequestParam("value") Integer value) {
    redisTest.setI("redis-echo", value);
    return redisTest.getI("redis-echo");
  }
}
