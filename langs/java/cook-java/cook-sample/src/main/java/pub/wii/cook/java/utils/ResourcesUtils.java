package pub.wii.cook.java.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourcesUtils {

  public static InputStream getResources(String path) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
  }

  public static String getResourcesAsString(String path) throws IOException {
    StringBuilder builder = new StringBuilder();
    String line;
    InputStream in = ResourcesUtils.getResources(path);
    InputStreamReader sr = new InputStreamReader(in);
    BufferedReader br = new BufferedReader(sr);
    while ((line = br.readLine()) != null) {
      builder.append(line).append(System.lineSeparator());
    }
    return builder.toString();
  }
}
