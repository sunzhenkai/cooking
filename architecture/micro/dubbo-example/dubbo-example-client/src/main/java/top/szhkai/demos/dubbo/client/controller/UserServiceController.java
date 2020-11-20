package top.szhkai.demos.dubbo.client.controller;

import com.google.gson.Gson;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.szhkai.demos.dubbo.UerService;
import top.szhkai.demos.dubbo.UserGetRequest;
import top.szhkai.demos.dubbo.UserGetResponse;

@RestController
@RequestMapping("user")
public class UserServiceController {
    Logger logger = LoggerFactory.getLogger(UserServiceController.class);

    @DubboReference
    private UerService.Iface service;

    @RequestMapping("get")
    public UserGetResponse get(@RequestParam(defaultValue = "default") String uid) throws TException {
        UserGetRequest request = new UserGetRequest();
        request.setUid(uid);
        logger.info(new Gson().toJson(request));
        return service.get(request);
    }
}
