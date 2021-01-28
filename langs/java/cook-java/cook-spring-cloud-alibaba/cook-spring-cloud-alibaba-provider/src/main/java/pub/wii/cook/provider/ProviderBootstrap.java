package pub.wii.cook.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@EnableAutoConfiguration
public class ProviderBootstrap {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ProviderBootstrap.class, args);
        } catch (Exception e) {
            log.error("Run spring application failed, error=" + ExceptionUtils.getFullStackTrace(e));
        }
    }
}
