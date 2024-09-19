package pub.wii.cook.springboot.cache;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pub.wii.cook.springboot.config.CookSpringBootConfiguration;

import javax.annotation.Resource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {CookSpringBootConfiguration.class})
@SpringJUnitConfig(classes = {CookSpringBootConfiguration.class})
class CustomKeyGeneratorTest {

    static class Sample {
        public String k() {
            return UUID.randomUUID().toString();
        }
    }

    @Resource
    CustomKeyGenerator customKeyGenerator;

    @SneakyThrows
    @Test
    void generate() {
        Sample s = new Sample();
        System.out.println(customKeyGenerator.generate(s, Sample.class.getMethod("k"),
                Lists.newArrayList("k", "e", "y")));
    }
}