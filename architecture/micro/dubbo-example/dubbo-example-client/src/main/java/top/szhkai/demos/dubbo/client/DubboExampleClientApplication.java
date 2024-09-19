package top.szhkai.demos.dubbo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:dubbo-example-consumer.xml"})
public class DubboExampleClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(DubboExampleClientApplication.class, args);
	}
}
