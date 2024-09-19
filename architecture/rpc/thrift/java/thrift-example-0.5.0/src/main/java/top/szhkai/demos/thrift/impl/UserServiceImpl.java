package top.szhkai.demos.thrift.impl;

import org.apache.thrift.TException;
import top.szhkai.demos.thrift.user.User;
import top.szhkai.demos.thrift.user.UserRequest;
import top.szhkai.demos.thrift.user.UserResponse;
import top.szhkai.demos.thrift.user.UserService;

public class UserServiceImpl implements UserService.Iface {
    @Override
    public UserResponse get(UserRequest request) throws TException {
        User user = new User();
        user.setAge(11);
        user.setName(request.getUid());
        return new UserResponse().setUser(user);
    }
}
