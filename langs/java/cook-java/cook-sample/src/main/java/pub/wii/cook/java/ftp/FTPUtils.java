package pub.wii.cook.java.ftp;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;

public class FTPUtils {
    public static void delete(FTPClient client, String remote) throws IOException {
        if (!client.deleteFile(remote)) {
            FTPFile[] files = client.listFiles(remote);
            if (files != null && files.length > 0) {
                for (FTPFile file : files) {
                    System.out.println(file.getName() + " - " + file.getLink());
                    String rp = remote + "/" + file.getName();
                    if (file.isDirectory()) {
                        delete(client, rp);
                    } else {
                        client.deleteFile(rp);
                    }
                }
            }
            client.removeDirectory(remote);
        }
    }

    @Deprecated
    public static boolean uploadFile(FTPClient client, String remote, String local) throws IOException {
        File file = new File(local);
        InputStream is = new FileInputStream(file);
        client.setFileType(FTP.BINARY_FILE_TYPE);
        return client.storeFile(remote, is);
    }

    @Deprecated
    public static boolean uploadFolder(FTPClient client, String remote, String local) throws IOException {
        File localFile = new File(local);
        System.out.println(localFile.getName());
        File[] files = localFile.listFiles();
        delete(client, remote);
        client.mkd(remote);

        if (files != null && files.length > 0) {
            for (File item : files) {
                String lp = local + "/" + item.getName();
                String rp = remote + "/" + item.getName();
                if (item.isFile()) {
                    uploadFile(client, rp, lp);
                } else {
                    uploadFolder(client, rp, lp);
                }
            }
        }

        return true;
    }

    public static void upload(FTPClient client, String remote, String local) throws IOException {
        File file = new File(local);
        if (file.isDirectory()) {
            String dest = remote + "/" + file.getName();
            delete(client, dest);
            client.mkd(dest);
            File[] files = file.listFiles();

            for (int i = 0; files != null && i < files.length; ++i) {
                String lp = local + "/" + files[i].getName();
                String rp = files[i].isDirectory() ? dest : dest + "/" + files[i].getName();
                upload(client, rp, lp);
            }
        } else {
            InputStream is = new FileInputStream(file);
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.storeFile(remote, is);
        }
    }

    public static boolean isFile(FTPClient client, String remote) throws IOException {
        FTPFile[] files = client.listFiles(remote);
        System.out.println("*** files length: " + (files == null ? null : files.length));
        System.out.println("*** list names: " + new Gson().toJson(client.listNames(remote)));
        if (files == null || files.length != 1) {
            return false;
        }

        for (FTPFile file : files) {
            System.out.println(file.getName());
        }
        return true;
    }

    public static void download(FTPClient client, String remote, String local) throws IOException {
        File file = new File(local);
        FileUtils.deleteQuietly(file);
        FileUtils.touch(file);
        OutputStream os = new FileOutputStream(file);
        client.retrieveFile(remote, os);
    }
}
