package pub.wii.cook.java.stream;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestMapping {
    public static void main(String[] args) {
        List<Pair<String, Map<String, String>>> data = new ArrayList<>();
        data.add(Pair.of("A", new HashMap<String, String>(){{put("B", "C");}}));
        data.add(Pair.of("D", new HashMap<String, String>(){{put("E", "F");}}));
        Map<String, Map<String, String>> r = data.stream().collect(Collectors.groupingBy(Pair::getLeft, Collectors.mapping(Pair::getRight,
                Collector.of(HashMap::new, Map::putAll, (x, y) -> {
                    x.putAll(y);
                    return x;
                }))));
        System.out.println(r);
    }
}
