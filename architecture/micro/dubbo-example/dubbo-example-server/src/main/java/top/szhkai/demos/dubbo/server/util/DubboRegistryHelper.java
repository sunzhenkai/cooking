package top.szhkai.demos.dubbo.server.util;

import org.apache.dubbo.config.*;
import top.szhkai.demos.dubbo.server.impl.DubboExampleServiceImpl;
import top.szhkai.demos.dubbo.services.DubboExampleService;

public class DubboRegistryHelper {
    public static void registry() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("java-dubbo-server");
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("consul://127.0.0.1:8500");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setHost("127.0.0.1");  // specific in my machine
        protocolConfig.setPort(20000);

        ServiceConfig<DubboExampleService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(DubboExampleService.class);
        serviceConfig.setRef(new DubboExampleServiceImpl());
        // serviceConfig.setGroup("dubbo-x");
        // serviceConfig.setVersion("0.0.1");
        serviceConfig.export();
    }
}
