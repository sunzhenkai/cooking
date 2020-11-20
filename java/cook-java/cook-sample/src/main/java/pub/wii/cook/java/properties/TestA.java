package pub.wii.cook.java.properties;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
class TestBean {
    private String v = "Hello";
    private String w;

    public TestBean setW(String s) {
        w = "v" + s;
        return this;
    }
}

public class TestA {
    public static void main(String[] args) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream inputStream = loader.getResourceAsStream("sample.properties")) {
            props.load(inputStream);
        }
        System.out.println(props);
    }
}
