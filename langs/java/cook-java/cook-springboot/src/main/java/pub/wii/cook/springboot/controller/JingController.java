package pub.wii.cook.springboot.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/api/passport/v1")
public class JingController {

    @lombok.Data
    static class Error {
        String code;
        String message;
    }

    @lombok.Data
    static class Data {
        String ticket;
    }

    @lombok.Data
    static class Res {
        String success;
        Error error;
        Data data;
    }

    private static Res res;

    static {
        res = new Res();
        Data data = new Data();
        data.setTicket(UUID.randomUUID().toString());
        Error error = new Error();
        error.setCode("10003016");
        error.setMessage("用户需要滑块验证");
        res.setSuccess("false");
        res.setData(data);
        res.setError(error);
    }

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST, RequestMethod.GET})
    @RequestMapping(value = "thirdAuthOpenIdLogin", method = {
            RequestMethod.POST, RequestMethod.GET
    }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Res> thirdAuthOpenIdLogin() {
        return ResponseEntity.ok(res);
    }

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST, RequestMethod.GET})
    @RequestMapping(value = "thirdAuthOpenIdLoginWithMobile", method = {
            RequestMethod.POST, RequestMethod.GET
    }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Res> thirdAuthOpenIdLoginWithMobile() {
        return ResponseEntity.ok(res);
    }
}
