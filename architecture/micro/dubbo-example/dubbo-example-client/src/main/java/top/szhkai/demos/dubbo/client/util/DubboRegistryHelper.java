package top.szhkai.demos.dubbo.client.util;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import top.szhkai.demos.dubbo.services.DubboExampleService;

public class DubboRegistryHelper {
    public static DubboExampleService find() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("client-java-dubbo");
        applicationConfig.setQosPort(33333);

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("consul://127.0.0.1:8500");

        ReferenceConfig<DubboExampleService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(DubboExampleService.class);
        // referenceConfig.setGroup("dubbo-x");
        // referenceConfig.setVersion("0.0.1");

        return referenceConfig.get();
    }
}
