package top.szhkai.demos.dubbo.server.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.szhkai.demos.dubbo.*;

public class UserServiceImpl implements UerService.Iface {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserPutResponse put(UserPutRequest request) {
        return new UserPutResponse().setIsOk(true);
    }

    @Override
    public UserGetResponse get(UserGetRequest request) {
        logger.info("received request: [{}]", request);
        UserGetResponse response = new UserGetResponse();
        User user = new User().setId(request.uid).setName(request.uid);
        response.setUser(user);
        return response;
    }
}
