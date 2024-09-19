package pub.wii.cook.java.test;

import java.util.ArrayList;
import java.util.List;

public class TestC {
    public static void main(String[] args) {
        List<Class<? extends String>> cls = new ArrayList<>();
        cls.add("".getClass());
        Class<? extends String>[] cla = new Class[2]; // (Class<? extends String>[]) cls.toArray();
        cla[0] = cls.get(0);
        System.out.println(cla);
    }
}
