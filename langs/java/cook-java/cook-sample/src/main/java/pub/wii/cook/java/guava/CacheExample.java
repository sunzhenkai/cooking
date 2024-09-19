package pub.wii.cook.java.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.*;
import lombok.experimental.Accessors;

@SuppressWarnings("NullableProblems")
public class CacheExample {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Accessors(chain = true)
    public static class Key {
        String name;
        int age;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class KeyNoToString {
        String name;
        int age;
    }

    @Setter
    public static class KeyNoData {
        String name;
        int age;
    }

    private static final LoadingCache<Key, String> cache = CacheBuilder.newBuilder()
            .build(new CacheLoader<Key, String>() {
                @Override
                public String load(Key key) throws Exception {
                    return key.toString();
                }
            });

    @SneakyThrows
    public static void main(String[] args) {

        Key key = new Key().setAge(18).setName("wii");
        System.out.println(key);

        KeyNoToString kns = new KeyNoToString().setAge(18).setName("wii");
        System.out.println(kns);

        KeyNoData knd = new KeyNoData();
        knd.setAge(18);
        knd.setName("wii");
        System.out.println(knd);

        String v = cache.get(key);
        System.out.println(v);
    }
}
