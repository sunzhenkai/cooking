package pub.wii.cook.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 如果有任务超时
 */
public class TestThreadB {
    private static final ExecutorService executor = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> res = new ConcurrentHashMap<>();
        List<Callable<String>> callables = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            final int sleep = i;
            callables.add(() -> {
                Thread.sleep(sleep * 1000);
                System.out.println("done " + sleep);
                res.put(sleep, "done");
                return "";
            });
        }

        try {
            List<Future<String>> fts = executor.invokeAll(callables, 5, TimeUnit.SECONDS);

            for (Future<String> f : fts) {
                System.out.printf("is done: %s, is cancelled: %s%n", f.isDone(), f.isCancelled());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


        System.out.println("done");
        while (true) {
            System.out.println(res);
            Thread.sleep(1000);
        }
    }
}
/**
 * output
 * is done: true, is cancelled: false
 * is done: true, is cancelled: false
 * is done: true, is cancelled: false
 * is done: true, is cancelled: false
 * is done: true, is cancelled: false
 * is done: true, is cancelled: true
 * is done: true, is cancelled: true
 * is done: true, is cancelled: true
 * is done: true, is cancelled: true
 * is done: true, is cancelled: true
 */
