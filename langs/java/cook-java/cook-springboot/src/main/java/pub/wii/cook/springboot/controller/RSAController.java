package pub.wii.cook.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pub.wii.cook.springboot.utils.RSAUtil;

@RestController
@RequestMapping("/rsa")
public class RSAController {
    @Value("rsa.private-key:none")
    private String privateKey;

    @Value("rsa.public-key:none")
    private String publicKey;

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST, RequestMethod.GET})
    @RequestMapping(value = "encode", method = {RequestMethod.POST, RequestMethod.GET
    }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> encode(@RequestParam String code) {
        return ResponseEntity.ok(RSAUtil.encode(code, publicKey));
    }

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST, RequestMethod.GET})
    @RequestMapping(value = "decode", method = {RequestMethod.POST, RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> decode(@RequestParam String code) {
        return ResponseEntity.ok(RSAUtil.decode(code, privateKey));
    }
}
