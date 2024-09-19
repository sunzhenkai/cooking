package pub.wii.cook.springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@EnableSpringConfigured
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"pub.wii.cook"}, lazyInit = true)
public class CookSpringBootConfiguration {
}
