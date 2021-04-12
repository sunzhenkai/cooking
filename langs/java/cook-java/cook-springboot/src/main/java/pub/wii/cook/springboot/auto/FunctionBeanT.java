package pub.wii.cook.springboot.auto;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class FunctionBeanT {

    private final Function<String, String> f;

    public FunctionBeanT(Function<String, String> f) {
        this.f = f;
    }

    @PostConstruct
    public void init() throws InterruptedException {
        // Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
        //     System.out.println(f.apply("RUN INIT"));
        // }, 1, 1, TimeUnit.SECONDS);
    }
}
