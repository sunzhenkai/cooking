package pub.wii.cook.springboot.kafka;

import lombok.SneakyThrows;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Lazy(value = false)
public class AlwaysLatestConsumerExample implements ConsumerSeekAware {
    @SneakyThrows
    @KafkaListener(id = "example-group-always-latest-2",
//            properties = {"enable.auto.commit:false", "auto.offset.reset:latest"},
            topics = "example")
    public void example(@Payload String in,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        System.out.println("received d key: " + key + ", payload: " + in);
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        assignments.forEach((t, o) -> callback.seekToEnd(t.topic(), t.partition()));
    }
}
