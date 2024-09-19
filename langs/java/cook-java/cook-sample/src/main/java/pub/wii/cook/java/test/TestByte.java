package pub.wii.cook.java.test;

import java.util.Arrays;

public class TestByte {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Long.valueOf(0).toString().getBytes()));
        System.out.println(Arrays.toString(Long.valueOf(-1).toString().getBytes()));
        System.out.println(Arrays.toString(Long.valueOf(-1000000000).toString().getBytes()));
        System.out.println(Arrays.toString(Long.valueOf(999999999).toString().getBytes()));
        System.out.println(Arrays.toString(Long.valueOf(1000000000).toString().getBytes()));
    }
}
