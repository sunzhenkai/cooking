package pub.wii.cook.java.net;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;

public class NetUtils {
    private static final String DockerIP = "172.17";

    public static String getLocalHost() throws Exception {
        for (NetworkInterface iface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            System.out.println("Walk network interface " + iface.getDisplayName() +
                    ", isVirtual: " + iface.isVirtual() +
                    ", displayName: " + iface.getDisplayName() +
                    ", " + (iface.getParent() == null ? "" : iface.getParent().getName()) +
                    ", " + iface.getIndex());

            if (iface.getDisplayName().startsWith("Docker")) {
                continue;
            }

            for (InetAddress addr : Collections.list(iface.getInetAddresses())) {
                System.out.println("Checking ip address {} for {}." + addr + iface.getDisplayName());
                String hostAddress = addr.getHostAddress();
                // The docker virtual environment uses a virtual ip which should be skipped.
                if (addr.isSiteLocalAddress()
                        && !addr.isLoopbackAddress()
                        && !(addr instanceof Inet6Address)
                        && !hostAddress.startsWith(DockerIP)) {
                    System.out.println("Ok, the ip {} will be used." + addr + ", hostname: " + addr.getHostName());
                    return hostAddress;
                }
            }
        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        InetAddress addr = InetAddress.getByName("127.0.0.1");
        System.out.println(addr.getHostName());
        // InetAddress ip;
        // String hostname;
        // try {
        //     ip = InetAddress.getLocalHost();
        //     hostname = ip.getHostName();
        //     System.out.println("Your current IP address : " + ip);
        //     System.out.println("Your current Hostname : " + hostname);
        //
        // } catch (UnknownHostException e) {
        //     e.printStackTrace();
        // }
        //
        // getLocalHost();
    }
}
