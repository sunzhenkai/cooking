package pub.wii.cook.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerBootstrap {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ConsumerBootstrap.class, args);
        } catch (Exception e) {
            log.error("Run spring application failed, error=" + ExceptionUtils.getFullStackTrace(e));
        }
    }
}
