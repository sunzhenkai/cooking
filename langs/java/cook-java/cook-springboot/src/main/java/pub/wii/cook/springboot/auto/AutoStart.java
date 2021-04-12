package pub.wii.cook.springboot.auto;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class AutoStart {

    @PostConstruct
    public void init() {
        // Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
        //     System.out.println("RUN INIT");
        // }, 0, 1, TimeUnit.SECONDS);
    }
}
