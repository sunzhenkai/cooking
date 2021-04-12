package pub.wii.cook.java.thread;

import com.google.common.collect.Lists;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread {
    private static final ExecutorService executor = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws InterruptedException {
        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "DONE";
        };

        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("DONE");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        long startTs;

        startTs = System.currentTimeMillis();
        executor.execute(runnable);
        System.out.println("execute: " + (System.currentTimeMillis() - startTs));

        startTs = System.currentTimeMillis();
        executor.submit(callable);
        executor.submit(runnable);
        System.out.println("submit: " + (System.currentTimeMillis() - startTs));

        startTs = System.currentTimeMillis();
        executor.invokeAll(Lists.newArrayList(callable));
        System.out.println("submit: " + (System.currentTimeMillis() - startTs));

        // executor.shutdown();
    }
}
