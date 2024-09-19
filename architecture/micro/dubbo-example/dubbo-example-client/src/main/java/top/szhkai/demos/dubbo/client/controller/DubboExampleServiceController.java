package top.szhkai.demos.dubbo.client.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.szhkai.demos.dubbo.client.util.DubboRegistryHelper;
import top.szhkai.demos.dubbo.services.DubboExampleService;

@RestController
public class DubboExampleServiceController {
    // @DubboReference
    // private DubboExampleService service;

    // @RequestMapping("echo")
    // public String echo(@RequestParam String word) {
    //     return service.echo(word);
    // }
    //
    // @RequestMapping("echoOld")
    // public String echoOld(@RequestParam String word) {
    //     DubboExampleService srv = DubboRegistryHelper.find();
    //     return srv.echo(word);
    // }
}
