package pub.wii.cook.java.base.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class SubmitTest {
    public static void main(String[] args) {
        ThreadFactory namedThreadFactory =
                new ThreadFactoryBuilder().setNameFormat("测试线程-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(
                5,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        pool.submit(() -> {
            while (true) {
                System.out.println("RUNNING");
                throw new Exception();
            }
        });
    }
}
