package pub.wii.cook.springboot.auto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class AutoStartRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        // Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
        //     System.out.println("RUN AUTO START RUNNER");
        // }, 0, 1, TimeUnit.SECONDS);
    }
}
