package pub.wii.cook.springboot.controller;

import com.google.gson.Gson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pub.wii.cook.springboot.utils.RSAUtil;

@Slf4j
@RestController
@RequestMapping("/rsa")
public class RSAController {
    @Data
    static class RSARequest {
        private String code;
    }

    @Value("${rsa.private-key:none}")
    private String privateKey;

    @Value("${rsa.public-key:none}")
    private String publicKey;

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST, RequestMethod.GET})
    @GetMapping(value = "encode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> encode(@RequestParam String code) {
        return ResponseEntity.ok(RSAUtil.encrypt(code, publicKey));
    }

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST, RequestMethod.GET})
    @GetMapping(value = "decode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> decode(@RequestParam String code) {
        return ResponseEntity.ok(RSAUtil.decrypt(code, privateKey));
    }

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST, RequestMethod.GET})
    @PostMapping(value = "encode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> encodeA(@RequestBody RSARequest code) {
        log.info("code={}", new Gson().toJson(code));
        return ResponseEntity.ok(RSAUtil.encrypt(code.code, publicKey));
    }

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST, RequestMethod.GET})
    @PostMapping(value = "decode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> decodeA(@RequestBody RSARequest code) {
        log.info("code={}", new Gson().toJson(code));
        return ResponseEntity.ok(RSAUtil.decrypt(code.code, privateKey));
    }
}
