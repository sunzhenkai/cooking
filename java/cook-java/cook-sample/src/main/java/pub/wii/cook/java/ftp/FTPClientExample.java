package pub.wii.cook.java.ftp;

import com.google.gson.Gson;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.PrintWriter;

public class FTPClientExample {
    public static void main(String[] args) throws IOException {
        FTPClient ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftp.connect("localhost", 2221);
        ftp.login("test", "test");
        System.out.println(new Gson().toJson(ftp.listNames()));
        // ftp.deleteFile("02970_apinkinthegreen_3840x2400.jpg");

        ftp.mkd("h/a/b");

        // FTPUtils.delete(ftp, "a");
        // FTPUtils.delete(ftp, "b");
        // System.out.println(new Gson().toJson(ftp.listFiles()));
        // System.out.println(new Gson().toJson(ftp.listDirectories()));

        // FTPUtils.uploadFile(ftp, "wallpaper-212422.jpg", "/Users/wii/Pictures/4k+/wallpaper-212422.jpg");
        // FTPUtils.upload(ftp, "wallpaper-212422.jpg", "/Users/wii/Pictures/4k+/wallpaper-212422.jpg");
        // FTPUtils.upload(ftp, "/", "/Users/wii/Tmp/e/ca");

        // System.out.println(new Gson().toJson("ABC".split(":")));
        // System.out.println("*** is folder: " + FTPUtils.isFile(ftp, "ca"));
    }
}
