package pub.wii.cook.java.thread;

public class ThreadTestA {
    public static void run() {
        System.out.println("run");
    }
    public static void main(String[] args) {
        Thread td = new Thread(ThreadTestA::run);
        td.setName("run");
        td.start();
    }
}
