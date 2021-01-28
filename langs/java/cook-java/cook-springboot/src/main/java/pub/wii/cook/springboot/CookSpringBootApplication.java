package pub.wii.cook.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CookSpringBootApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(CookSpringBootApplication.class, args);
    RedisProperties redisProperties = (RedisProperties)context.getBean("testBean");
    System.out.println(redisProperties.getPassword());
  }
}
