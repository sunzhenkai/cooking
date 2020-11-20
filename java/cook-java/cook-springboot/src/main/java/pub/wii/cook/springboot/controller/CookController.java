package pub.wii.cook.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pub.wii.cook.springboot.model.EchoReq;

@RestController
@RequestMapping("cook")
public class CookController {

  @RequestMapping(value = "echo",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Object echo(HttpServletRequest request, @RequestBody EchoReq data) {
    return data;
  }
}
