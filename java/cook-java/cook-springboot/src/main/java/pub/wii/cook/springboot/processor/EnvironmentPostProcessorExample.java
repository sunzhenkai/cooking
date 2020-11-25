package pub.wii.cook.springboot.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;

public class EnvironmentPostProcessorExample implements EnvironmentPostProcessor {
    private static final String PREFIX_SECRET = "secret:";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        HashMap<String, Object> props = new HashMap<>();
        for (PropertySource<?> ps : environment.getPropertySources()) {
            System.out.println("sp name: " + ps.getName());
            if (ps instanceof OriginTrackedMapPropertySource) {
                OriginTrackedMapPropertySource source = (OriginTrackedMapPropertySource) ps;
                for (String name : source.getPropertyNames()) {
                    Object value = source.getProperty(name);
                    System.out.println(name + " ======= " + value);
                    if (value instanceof String) {
                        String str = (String) value;
                        if (str.startsWith(PREFIX_SECRET)) {
                            props.put(name, str.substring(PREFIX_SECRET.length()));
                        }
                    }
                }
            }
        }

        if (!props.isEmpty()) {
            environment.getPropertySources().addFirst(new MapPropertySource("secretDecryptedConfig", props));
        }
    }
}