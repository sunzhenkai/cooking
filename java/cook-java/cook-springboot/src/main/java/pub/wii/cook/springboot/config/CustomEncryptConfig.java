package pub.wii.cook.springboot.config;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

@Configuration
public class CustomEncryptConfig implements PropertySourceLoader, PriorityOrdered {

    @Override
    public String[] getFileExtensions() {
        return new String[]{"yml"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        System.out.println("HELLO: " + resource);
        return null;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
