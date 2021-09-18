package pub.wii.cook.springboot.processor;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;

/**
 * 读取环境变量 config.additional, 可以是字符串，也可以是 list
 * 然后加载配置文件内数据, 并写入环境变量
 *
 * 也可以在 jvm 参数里通过指定 -Dspring.config.additional-location=/path/to/config.file 来指定
 * 不过稍微麻烦点
 */
public class AdditionalConfigProcessor implements EnvironmentPostProcessor {
    private static final String ADDITIONAL_CONFIG_KEY = "config.additional";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        HashMap<String, Object> props = new HashMap<>();
        for (PropertySource<?> ps : environment.getPropertySources()) {
            if (ps instanceof OriginTrackedMapPropertySource) {
                OriginTrackedMapPropertySource source = (OriginTrackedMapPropertySource) ps;
                for (String name : source.getPropertyNames()) {
                    if (name.startsWith(ADDITIONAL_CONFIG_KEY)) {
                        Object value = source.getProperty(name);
                        if (value != null && Files.exists(Paths.get(value.toString()))) {
                            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
                            yamlFactory.setResources(new FileSystemResource(value.toString()));
                            Properties p = yamlFactory.getObject();
                            if (p != null) {
                                for (Object key : p.keySet()) {
                                    props.put(key.toString(), p.get(key));
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!props.isEmpty()) {
            environment.getPropertySources().addFirst(new MapPropertySource("e.config.additional", props));
        }
    }
}
