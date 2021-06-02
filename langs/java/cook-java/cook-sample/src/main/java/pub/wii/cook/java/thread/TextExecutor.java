package pub.wii.cook.java.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TextExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            System.out.println("A " + System.currentTimeMillis());
        }, 1, TimeUnit.SECONDS); // 执行一次

        executorService.scheduleAtFixedRate(() -> {
            System.out.println("B " + System.currentTimeMillis());
        }, 0, 1, TimeUnit.SECONDS); // 每一秒执行一次

        executorService.scheduleAtFixedRate(() -> {
            System.out.println("C " + System.currentTimeMillis());
            throw new RuntimeException();
        }, 0, 1, TimeUnit.SECONDS); // 只执行一次，异常则退出
    }
}

/*
B 1618369996487
A 1618369997488
B 1618369997488
B 1618369998488
B 1618369999488
B 1618370000491
*/