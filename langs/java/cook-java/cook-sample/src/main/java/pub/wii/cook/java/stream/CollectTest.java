package pub.wii.cook.java.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectTest {
    public static void main(String[] args) {
        class Info {
            final String key;
            final String value;

            Info(String key, String value) {
                this.key = key;
                this.value = value;
            }

            public String getKey() {
                return key;
            }

            public String getValue() {
                return value;
            }

            @Override
            public String toString() {
                return "Info{" +
                        "key='" + key + '\'' +
                        ", value='" + value + '\'' +
                        '}';
            }
        }

        List<Info> infos = new ArrayList<>();
        infos.add(new Info("A", "B"));
        infos.add(new Info("C", "D"));
        Map<String, String> mp = infos.stream().collect(Collectors.toMap(Info::getKey, Info::getValue));
        System.out.println(mp);

        Map<Info, Map<String, String>> data = new HashMap<>();
        data.put(new Info("A", "A"), new HashMap<>());
        data.put(new Info("A", "B"), new HashMap<>());
        data.put(new Info("A", "C"), new HashMap<>());
        data.put(new Info("B", "A"), new HashMap<>());
        data.put(new Info("B", "B"), new HashMap<>());
        data.put(new Info("B", "C"), new HashMap<>());
        Map<String, Map<Info, Map<String, String>>> rd = data.entrySet()
                .stream()
                .collect(Collectors.groupingBy(kv -> kv.getKey().getKey(),
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        System.out.println(rd);
    }
}
