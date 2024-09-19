package pub.wii.cook.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import pub.wii.cook.springboot.service.iface.TypeService;

import java.util.Map;

@SpringBootApplication
@EnableSpringConfigured
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "pub.wii.cook", lazyInit = true)
public class CookSpringBootApplication {
    public static void main(String[] args) {
        System.setProperty("metric.monitor.enable", "true");
        ApplicationContext context = SpringApplication.run(CookSpringBootApplication.class, args);

        RedisProperties redisProperties = (RedisProperties) context.getBean("testBean");
        System.out.println(redisProperties.getPassword());

        Map<String, TypeService> c = context.getBeansOfType(TypeService.class);
        System.out.println(c);
    }
}
