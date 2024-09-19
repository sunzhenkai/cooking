package pub.wii.cook.java.lock;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestLock {
    private static final ExecutorService es = Executors.newFixedThreadPool(16);

    void sleep() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void fe() {
        System.out.println("fe: " + LocalDateTime.now());
        sleep();
    }

    synchronized void fd() {
        System.out.println("fd: " + LocalDateTime.now());
    }

    synchronized void fc() {
        System.out.println("fc: " + LocalDateTime.now());
    }

    synchronized void fb() {
        System.out.println("fb: " + LocalDateTime.now());
        fc();
        es.submit(this::fd);
        sleep();
    }

    synchronized void fa() {
        System.out.println("fa: " + LocalDateTime.now());
        sleep();
    }

    public static void main(String[] args) {
        TestLock tl = new TestLock();
        es.submit(tl::fa);
        es.submit(tl::fb);
        es.submit(tl::fe);
    }
}
