package pub.wii.cook.java.map;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMap {
    public static void main(String[] args) {
        Long[] ls = new Long[]{1L, 2L, 3L, 4L, 5L};
        Map<Long, ArrayList<Long>> lm = new HashMap<>();
        for (Long l : ls) {
            List<Long> ll = lm.computeIfAbsent(l, k -> new ArrayList<>());
            ll.add(l);
        }

        System.out.println(new Gson().toJson(lm));
        System.out.println(new Gson().toJson(lm.get(1L)));
    }
}
