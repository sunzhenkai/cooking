package pub.wii.cook.springboot.redis;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
// @AllArgsConstructor
public class RedisTest {

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    public Integer getI(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setI(String key, Integer value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
