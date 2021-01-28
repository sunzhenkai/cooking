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

    private String decode(String secret) {
        return secret;
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        HashMap<String, Object> props = new HashMap<>();
        for (PropertySource<?> ps : environment.getPropertySources()) {
            if (ps instanceof OriginTrackedMapPropertySource) {
                OriginTrackedMapPropertySource source = (OriginTrackedMapPropertySource) ps;
                for (String name : source.getPropertyNames()) {
                    Object value = source.getProperty(name);
                    if (value instanceof String) {
                        String str = (String) value;
                        if (str.startsWith(PREFIX_SECRET)) {
                            // decode, or something else
                            String dec = decode(str.substring(PREFIX_SECRET.length()));
                            props.put(name, dec);
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