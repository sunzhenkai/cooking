package pub.wii.cook.java.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class FileTest {
    public static void main(String[] args) throws IOException {
        // delete directory
        // FileUtils.deleteQuietly(new File("/Users/wii/Tmp/d"));
        // FileUtils.deleteQuietly(new File("/Users/wii/Tmp/f"));

        Collection<File> files = FileUtils.listFiles(new File("/Users/wii/Tmp"), new IOFileFilter() {
            @Override
            public boolean accept(File file) {
                return true;
            }

            @Override
            public boolean accept(File dir, String name) {
                System.out.println("Dir: " + dir.getName() + ", Name: " + name);
                return false;
            }
        }, new IOFileFilter() {
            @Override
            public boolean accept(File file) {
                return false;
            }

            @Override
            public boolean accept(File dir, String name) {
                return false;
            }
        });

        for (File file : files) {
            System.out.println(file.getName());
        }
    }
}
