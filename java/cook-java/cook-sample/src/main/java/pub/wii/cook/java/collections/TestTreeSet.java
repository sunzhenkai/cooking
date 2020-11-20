package pub.wii.cook.java.collections;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class TestTreeSet {
    public static void main(String[] args) {
        TreeSet<Pair<Integer, Integer>> ts = new TreeSet<>(
                (o1, o2) -> o2.getRight().equals(o1.getRight())
                        ? o1.getLeft() - o2.getLeft()
                        : o1.getRight() - o2.getRight()
        );
        ts.add(Pair.of(2, 2));
        ts.add(Pair.of(1, 2));
        ts.add(Pair.of(4, 0));
        ts.add(Pair.of(3, 2));
        ts.add(Pair.of(5, 1));
        System.out.println(ts);
        Pair<Integer, Integer> poll = ts.pollFirst();
        System.out.println(ts);
        assert poll != null;
        System.out.println(poll);
        ts.add(Pair.of(poll.getLeft(), poll.getRight() + 10));
        System.out.println(ts);

        System.out.println();
        poll = ts.pollFirst();
        System.out.println(ts);
        assert poll != null;
        System.out.println(poll);
        ts.add(Pair.of(poll.getLeft(), poll.getRight() + 2));
        System.out.println(ts);

        Map<String, Object> res = ImmutableMap.of("success", "YES", "failed", "NO");
        String infos = new Gson().toJson(res);
        System.out.println(infos);

        class A {
            String a;
            public A(String a) {
                this.a = a;
            }

            @Override
            public String toString() {
                return a;
            }
        }

        List<A> ls = new ArrayList<>();
        ls.add(new A("A"));
        // List<A> lsA = Lists.newArrayList(ls);
        List<A> lsA = (ArrayList<A>)((ArrayList<A>)ls).clone();
        ls.add(new A("B"));
        lsA.get(0).a = "C";
        System.out.println(lsA);
    }
}
