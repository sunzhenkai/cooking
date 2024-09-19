package pub.wii.cook.java.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestNet {
    private static final String SERVER_ADDRESS = "10.120.34.247";
    private static final int TCP_SERVER_PORT = 8380;

    public static boolean hostAvailabilityCheck() {
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress(SERVER_ADDRESS, TCP_SERVER_PORT), 5);
            return true;
        } catch (IOException ex) {
            /* ignore */
        }
        return false;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int loop = 10;
        for (int i = 0; i < loop; ++i) {
            if (!hostAvailabilityCheck()) {
                System.out.println("check failed");
            }
        }
        System.out.println("cost(ms): " + (System.currentTimeMillis() - start));
    }
}
