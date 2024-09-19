package pub.wii.cook.java.test;

import java.util.ArrayList;
import java.util.List;

public class TestD {
    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        ls.add("A");
        ls.add("D");
        ls.add("B");
        ls.add("C");

        List<String> lsb = new ArrayList<>();
        lsb.add("E");
        lsb.add("R");
        lsb.add("F");
        lsb.add("G");

        lsb.addAll(ls);
        System.out.println(lsb);
    }
}
