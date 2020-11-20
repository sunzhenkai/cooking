package top.szhkai.demos.dubbo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:dubbo-example-provider.xml"})
public class DubboExampleServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboExampleServerApplication.class, args);
    }
}
