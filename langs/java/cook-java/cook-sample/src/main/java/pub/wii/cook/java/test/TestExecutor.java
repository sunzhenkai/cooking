package pub.wii.cook.java.test;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestExecutor {

    String s;

    static void f() {
        System.out.println("A");
//        throw new RuntimeException();
    }

    @SneakyThrows
    public static void main(String[] args) {
        Field f = TestExecutor.class.getDeclaredField("s");
        System.out.println(f);
        System.out.println(f.getGenericType());
        System.out.println(f.getDeclaringClass());
        System.out.println(f.getType());

        System.out.println(TestExecutor.class.isAssignableFrom(Object.class));
        System.out.println(Object.class.isAssignableFrom(TestExecutor.class));

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(TestExecutor::f, 1, 1, TimeUnit.SECONDS);
    }
}
