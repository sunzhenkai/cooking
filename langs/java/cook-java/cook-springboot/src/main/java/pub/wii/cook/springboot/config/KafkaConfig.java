package pub.wii.cook.springboot.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        // 多个 broker: localhost:9092,localhost:9093
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(AdminClientConfig.RETRIES_CONFIG, 3);
        configs.put(AdminClientConfig.CLIENT_ID_CONFIG, "cook.java");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic example() {
        return TopicBuilder.name("example")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
