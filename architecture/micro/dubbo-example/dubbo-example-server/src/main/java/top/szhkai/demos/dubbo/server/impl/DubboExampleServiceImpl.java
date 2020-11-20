package top.szhkai.demos.dubbo.server.impl;

import top.szhkai.demos.dubbo.services.DubboExampleService;

public class DubboExampleServiceImpl implements DubboExampleService {

    @Override
    public String echo(String word) {
        System.out.println(String.format("receive request, word = [%s]", word));
        return String.format("echo: %s", word);
    }
}
