package pub.wii.cook.java.test;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TestE {
    public static void main(String[] args) {
        List<Object> l = Lists.newArrayList("1", null, "2");
        System.out.println(l.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));


        Map<String, String> m = new HashMap<>();
        m.remove("a");
    }
}
