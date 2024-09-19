package pub.wii.cook.java.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ParseConfig {
    public static void main(String[] args) {
        Config cfg = ConfigFactory.parseResources("tf.cfg");
        System.out.println(cfg);
        System.out.println(cfg.getConfig("root.child"));
    }
}
