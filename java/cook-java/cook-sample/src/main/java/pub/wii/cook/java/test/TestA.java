package pub.wii.cook.java.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

public class TestA {
    public static void main(String[] args) {
        String hello = "Hello World";
        // System.out.println(hello.substring(0, hello.lastIndexOf("Wrld")));

        // Number number = Double.NaN;
        // System.out.println(number);
        // String s = "HELLO";
        // System.out.println(String.valueOf(s));
        // System.out.println(s.toString());
        //
        // System.out.println("20200811-abc".compareTo("20200711-de"));

        System.out.println(new Gson().toJson("AB:C".split(":")));

        String[] ss = new String[]{
                "current_1604024823113",
                "current_1604024823113.taxonomy",
                "current_1604024823114",
                "current_1604024823114.taxonomy",
                "expired_1604024229400.taxonomy",
                "expired_1604023933430",
                "next_1595991651595",
                "next_1595991651595",
                "next_2595991651595",
                "next_2595991651595.taxonomy",
                "expired_1604023933430.taxonomy",
                "expired_1604024535119",
                "expired_1604024229400",
                "expired_1604024535119.taxonomy"
        };
        Arrays.sort(ss, String::compareTo);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(ss));
        Map<String, List<String>> vg = new HashMap<>();
        for (String s : ss) {
            String group = s.split("_")[0];
            if (group.equals("current") || group.equals("next")) {
                List<String> gl = vg.computeIfAbsent(group, g -> new ArrayList<>());
                if (!gl.isEmpty() && !s.startsWith(gl.get(0))) {
                    gl.clear();
                }
                gl.add(s);
            }
        }

        System.out.println(gson.toJson(vg));
        List<String> res = new ArrayList<>();
        vg.values().forEach(res::addAll);
        System.out.println(gson.toJson(res));

        System.out.println(String.join("/", "A", "B", "C"));
    }
}
