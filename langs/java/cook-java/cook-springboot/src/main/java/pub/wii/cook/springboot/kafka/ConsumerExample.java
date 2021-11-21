package pub.wii.cook.springboot.kafka;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Lazy(value = false)
public class ConsumerExample {
    @SneakyThrows
    @KafkaListener(id = "example-group", topics = "example")
    public void example(@Payload String in,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        System.out.println("received key: " + key + ", payload: " + in);
        Thread.sleep(3000);
        System.out.println("example-group done");
    }

    @KafkaListener(id = "example-group-b", topics = "example")
    public void exampleA(String in) {
        System.out.println("received b: " + in);
    }

    @KafkaListener(id = "example-group-c", topics = "example")
    public void exampleAB(String in) {
        System.out.println("received c: " + in);
    }

    @KafkaListener(id = "example", topics = "example-a")
    public void exampleAAB(String in) {
        System.out.println("received c: " + in);
    }
}
