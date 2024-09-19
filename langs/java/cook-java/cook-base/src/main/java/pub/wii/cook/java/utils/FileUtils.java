package pub.wii.cook.java.utils;

import java.io.File;

public class FileUtils {

  public static long getSize(File file) {
    long res = 0L;
    if (file.isFile()) {
      res = file.length();
    } else {
      File[] files = file.listFiles();
      if (files != null && files.length != 0) {
        for (File item : files) {
          res += getSize(item);
        }
      }
    }

    return res;
  }
}
