package pub.wii.cook.java.socket;

import org.apache.commons.io.FileUtils;
import pub.wii.cook.java.utils.GsonUtils;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTransferServerThread extends Thread {
    Socket socket;
    String wd;

    public FileTransferServerThread(Socket socket, String wd) {
        this.socket = socket;
        this.wd = wd;
    }

    @Override
    public void run() {
        try {
            boolean loopFlag = true;
            FileTransferInfo info;
            DataOutputStream dot = new DataOutputStream(socket.getOutputStream());
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            DataInputStream dis = new DataInputStream(bis);

            while (loopFlag) {
                String msg = dis.readUTF();
                info = GsonUtils.GSON.fromJson(msg, FileTransferInfo.class);

                if (FileTransferInfo.TYPE_CLOSE.equals(info.type())) {
                    loopFlag = false;
                } else if (FileTransferInfo.TYPE_DELETE.equals(info.type())) {
                    Path fnl = Paths.get(wd, info.path());
                    FileUtils.deleteQuietly(fnl.toFile());
                } else {
                    long length = dis.readLong();

                    Path fnl = Paths.get(wd, info.path(), info.name());
                    System.out.println("Receiving file: " + fnl.toAbsolutePath() + ", length: " + length);
                    FileUtils.forceMkdir(Paths.get(wd, info.path()).toFile());
                    FileUtils.touch(fnl.toFile());

                    FileOutputStream fos = new FileOutputStream(fnl.toFile());
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    for (long j = 0; j < length; j++) {
                        bos.write(bis.read());
                    }
                    bos.close();
                    System.out.println("Saved file: " + fnl.toAbsolutePath());
                }

                dot.writeUTF(FileTransferInfo.MSG_DONE);
                dot.flush();
            }

            System.out.println("Closing socket...");
            dis.close();
            dot.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
