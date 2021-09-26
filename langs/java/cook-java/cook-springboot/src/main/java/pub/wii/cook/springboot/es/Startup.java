package pub.wii.cook.springboot.es;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@EnableSpringConfigured
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "pub.wii.cook")
@Component
public class Startup {

    @Resource
    ESService esService;

    public static void main(String[] args) {
    }
}
