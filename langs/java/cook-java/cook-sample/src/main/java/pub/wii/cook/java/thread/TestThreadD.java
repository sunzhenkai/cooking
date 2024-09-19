package pub.wii.cook.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 一个任务执行异常，其他任务状态
 */
public class TestThreadD {
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
                if (sleep == 5) {
                    System.out.println("throw exception");
                    throw new RuntimeException();
                }
                return "";
            });
        }

        callables.add(() -> {
            while (true) {
                int i = 1 + 2;
            }
        });

        try {
            List<Future<String>> fts = executor.invokeAll(callables, 15, TimeUnit.SECONDS);

            for (Future<String> f : fts) {
                System.out.printf("is done: %s, is cancelled: %s%n", f.isDone(), f.isCancelled());
                try {
                    f.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
 done 1
 done 2
 done 3
 done 4
 done 5
 throw exception
 done 6
 done 7
 done 8
 done 9
 done 10
 is done: true, is cancelled: false
 is done: true, is cancelled: false
 is done: true, is cancelled: false
 is done: true, is cancelled: false
 is done: true, is cancelled: false
 is done: true, is cancelled: false
 is done: true, is cancelled: false
 is done: true, is cancelled: false
 is done: true, is cancelled: false
 is done: true, is cancelled: false
 is done: true, is cancelled: true
 done
 {1=done, 2=done, 3=done, 4=done, 5=done, 6=done, 7=done, 8=done, 9=done, 10=done}
 java.util.concurrent.ExecutionException: java.lang.RuntimeException
 at java.util.concurrent.FutureTask.report(FutureTask.java:122)
 at java.util.concurrent.FutureTask.get(FutureTask.java:192)
 at pub.wii.cook.java.thread.TestThreadD.main(TestThreadD.java:43)
 Caused by: java.lang.RuntimeException
 at pub.wii.cook.java.thread.TestThreadD.lambda$main$0(TestThreadD.java:25)
 at java.util.concurrent.FutureTask.run(FutureTask.java:266)
 at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
 at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 at java.lang.Thread.run(Thread.java:748)
 java.util.concurrent.CancellationException
 at java.util.concurrent.FutureTask.report(FutureTask.java:121)
 at java.util.concurrent.FutureTask.get(FutureTask.java:192)
 at pub.wii.cook.java.thread.TestThreadD.main(TestThreadD.java:43)
 {1=done, 2=done, 3=done, 4=done, 5=done, 6=done, 7=done, 8=done, 9=done, 10=done}
 */
