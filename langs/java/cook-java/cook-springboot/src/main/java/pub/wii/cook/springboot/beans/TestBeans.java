package pub.wii.cook.springboot.beans;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class TestBeans {
    @Bean
    @Primary
    RedisProperties testBean(RedisProperties redisProperties) {
        System.out.println(redisProperties.getPassword());
        redisProperties.setPassword("encrypt+" + redisProperties.getPassword());
        return redisProperties;
    };
}
