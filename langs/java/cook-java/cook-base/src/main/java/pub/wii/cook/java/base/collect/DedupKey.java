package pub.wii.cook.java.base.collect;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DedupKey {
    public static void main(String[] args) {
        Map<Integer, Integer> m = new HashMap<>();
        List<Integer> l = Lists.newArrayList(1, 2, 2, 3, 3, 3, 4, 5, 6, 7);
        System.out.println(l.stream().collect(Collectors.toMap(i -> i, i -> i, (a, b) -> a)));
        System.out.println(l.stream().collect(Collectors.toMap(Function.identity(), Function.identity(), (a, b) -> a)));
        System.out.println(l.stream().collect(Collectors.toMap(i -> i, i -> i)));
    }
}
