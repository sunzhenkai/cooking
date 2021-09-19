package pub.wii.cook.springboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pub.wii.cook.springboot.config.CookSpringBootConfiguration;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {CookSpringBootConfiguration.class})
@SpringJUnitConfig(classes = CookSpringBootConfiguration.class)
class ESServiceTest {

    @Resource
    ESService esService;

    @Test
    public void testSample() {
        System.out.println("ckpt;" + esService.getRest());
    }
}