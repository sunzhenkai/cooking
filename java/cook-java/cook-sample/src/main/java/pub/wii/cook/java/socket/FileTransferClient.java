package pub.wii.cook.java.socket;

import pub.wii.cook.java.utils.GsonUtils;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTransferClient {
    Socket socket;
    DataOutputStream dos;
    DataInputStream dis;

    public FileTransferClient(Socket socket) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        this.socket = socket;
        this.dos = new DataOutputStream(bos);
        this.dis = new DataInputStream(socket.getInputStream());
    }

    public void send(String path, File file) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; files != null && i < files.length; ++i) {
                File f = files[i];
                send(Paths.get(path, file.getName()).toString(), f);
            }
        } else {
            System.out.println("Sending file: " + file.getAbsolutePath() + ", length: " + file.length());
            dos.writeUTF(GsonUtils.GSON.toJson(new FileTransferInfo().name(file.getName()).path(path)));
            dos.writeLong(file.length());
            Files.copy(file.toPath(), dos);
            dos.flush();
            System.out.println(dis.readUTF());
        }
    }

    public void close() throws IOException {
        FileTransferInfo info = new FileTransferInfo().type(FileTransferInfo.TYPE_CLOSE);
        dos.writeUTF(GsonUtils.GSON.toJson(info));
        dos.flush();
        System.out.println(dis.readUTF());
        dis.close();
        dos.close();
        socket.close();
    }

    public void delete(String path) throws IOException {
        FileTransferInfo info = new FileTransferInfo().path(path).type(FileTransferInfo.TYPE_DELETE);
        dos.writeUTF(GsonUtils.GSON.toJson(info));
        dos.flush();
        System.out.println(dis.readUTF());
    }

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 2222;
        String path = "receiver";
        Socket socket = new Socket(host, port);
        FileTransferClient client = new FileTransferClient(socket);
        client.delete(path);
        client.send(path, new File("/Users/wii/Downloads"));
        client.close();
    }
}
