package pub.wii.cook.springboot.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pub.wii.cook.springboot.cache.CacheableService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("cache")
public class CacheController {

    @Resource
    CacheableService cacheableService;

    @RequestMapping(value = "get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Object>> get(@RequestParam("key") String key) {
        return ResponseEntity.ok(cacheableService.cache(key));
    }
}
