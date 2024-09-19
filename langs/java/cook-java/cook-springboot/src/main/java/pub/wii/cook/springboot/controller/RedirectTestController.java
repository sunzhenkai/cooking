package pub.wii.cook.springboot.controller;

import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pub.wii.cook.springboot.model.RedirectFromRequest;
import pub.wii.cook.springboot.model.RedirectFromResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("redirect")
public class RedirectTestController {
    @SneakyThrows
    @RequestMapping(value = "from", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RedirectFromResponse> from(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     @RequestBody RedirectFromRequest data) {
        if (data.isRedirect()) {
            response.sendRedirect("/api/v2/");
            return null;
        } else {
            return ResponseEntity.ok(new RedirectFromResponse().setMessage("from"));
        }
    }

    @SneakyThrows
    @RequestMapping(value = "to", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RedirectFromResponse> to(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestBody RedirectFromRequest data) {
        if (data.isRedirect()) {
            response.sendRedirect("/api/v2/");
            return null;
        } else {
            return ResponseEntity.ok(new RedirectFromResponse().setMessage("from"));
        }
    }
}
