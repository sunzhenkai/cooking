package pub.wii.cook.springboot.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Component;
import pub.wii.cook.springboot.service.ESService;

@EnableSpringConfigured
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "pub.wii.cook")
@Component
public class Startup {

    @Autowired
    ESService esService;

    public static void main(String[] args) {
    }
}
