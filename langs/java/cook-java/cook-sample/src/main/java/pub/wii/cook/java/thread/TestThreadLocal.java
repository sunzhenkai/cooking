package pub.wii.cook.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Info {
    public String s;
    public int i;
}

class Outer {
    private static final ThreadLocal<Info> tl = ThreadLocal.withInitial(Info::new);

    public static void setTL() {
        Thread t = Thread.currentThread();
        Info info = new Info();
        info.s = t.getName();
        info.i = 1;
        tl.set(info);
    }

    public static void f() {
        // setTL();
        Info info = tl.get();
        System.out.println("A> Info{s=" + info.s + ", i=" + info.i + "}");
    }
}

public class TestThreadLocal {
    private static final ThreadLocal<Info> tl = new ThreadLocal<>();
    private static final ExecutorService executor = Executors.newFixedThreadPool(8);

    public static void setTL(int tno) {
        Thread t = Thread.currentThread();
        Info info = new Info();
        info.s = t.getName();
        info.i = 1;
        while (tno-- > 0) info.i *= 10;
        tl.set(info);
        // for (int i = 0; i < 100000000; ++i);
    }

    public static void testTL() {
        Info info = tl.get();
        System.out.println("B> Info{s=" + info.s + ", i=" + info.i + "}");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; ++i) {
            final int j = i;
            executor.submit(() -> {
                setTL(j);
                Outer.f();
                testTL();
            });
        }

        for (int i = 0; i < 8; ++i) {
            executor.submit(Outer::f);
        }
    }
}
