package pub.wii.cook.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/api/passport/v1")
public class JingController {

    @lombok.Data
    static class Error {
        Integer code;
        String message;
    }

    @lombok.Data
    static class Data {
        String ticket;
        String name;
        Integer age;
        MultiValueMap<String, String> formData;
    }

    @lombok.Data
    static class Res {
        Boolean success;
        Error error;
        Data data;
    }

    private static Res res;
    private static Res resTrue;

    static {
        res = new Res();
        Data data = new Data();
        data.setTicket(UUID.randomUUID().toString());
        Error error = new Error();
        error.setCode(10003018);
        error.setMessage("用户需要滑块验证");
        res.setSuccess(false);
        res.setData(data);
        res.setError(error);

        resTrue = new Res();
        data = new Data();
        data.setName("刘德华");
        data.setAge(18);
        resTrue.setSuccess(true);
        resTrue.setData(data);
    }

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST, RequestMethod.GET})
    @RequestMapping(value = "thirdAuthOpenIdLogin", method = {
            RequestMethod.POST, RequestMethod.GET
    },
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Res> thirdAuthOpenIdLogin(
            @RequestParam MultiValueMap<String, String> formData
//            @RequestBody MultiValueMap<String, String> formData
    ) {
        log.info("thirdAuthOpenIdLogin. [formData={}]", formData);
        res.getData().setFormData(formData);
        return ResponseEntity.ok(res);
    }

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST, RequestMethod.GET})
    @RequestMapping(value = "thirdAuthOpenIdLoginWithMobile", method = {
            RequestMethod.POST, RequestMethod.GET
    }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Res> thirdAuthOpenIdLoginWithMobile() {
        return ResponseEntity.ok(resTrue);
    }

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST, RequestMethod.GET})
    @RequestMapping(value = "thirdAuthMobileLogin", method = {RequestMethod.POST, RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Res> thirdAuthMobileLogin() {
        return ResponseEntity.ok(resTrue);
    }
}
