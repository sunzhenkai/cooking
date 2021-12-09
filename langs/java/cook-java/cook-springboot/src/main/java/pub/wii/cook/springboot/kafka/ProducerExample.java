package pub.wii.cook.springboot.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ProducerExample {
    @Resource
    KafkaTemplate<String, String> template;
}
