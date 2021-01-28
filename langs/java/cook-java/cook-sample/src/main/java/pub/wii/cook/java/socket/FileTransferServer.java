package pub.wii.cook.java.socket;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

public class FileTransferServer {
    private ServerSocket ss;
    private final int port;
    private final String wd;

    /**
     * @param port network port for listening
     * @param wd   working directory, files will saved into this folder
     */
    public FileTransferServer(int port, String wd) {
        this.port = port;
        this.wd = wd;
    }

    public void start() throws Exception {
        FileUtils.forceMkdir(new File(wd));

        InetSocketAddress listenAddr = new InetSocketAddress(port);
        ServerSocketChannel listener = ServerSocketChannel.open();
        ss = listener.socket();
        ss.setReuseAddress(true);
        ss.bind(listenAddr);

        while (true) {
            Socket socket = ss.accept();
            System.out.println("Received request: " + socket);
            FileTransferServerThread thread = new FileTransferServerThread(socket, wd);
            thread.start();
        }
    }

    public void close() {
        if (ss != null && !ss.isClosed()) {
            try {
                ss.close();
            } catch (Exception ignored) {
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FileTransferServer server = new FileTransferServer(2222, "/Users/wii/Tmp");
        server.start();
    }
}
