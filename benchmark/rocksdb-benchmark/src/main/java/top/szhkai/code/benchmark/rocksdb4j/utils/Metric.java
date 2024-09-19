package top.szhkai.code.benchmark.rocksdb4j.utils;

import java.util.ArrayList;
import java.util.List;

public class Metric {
    List<Integer> values;
    int max;

    public Metric(int max) {
        this.max = max;
        this.clear();
    }

    public void add(long v) {
        add((int) v);
    }

    public void add(int v) {
        if (v >= max) {
            System.out.println("ERROR out of range " + v);
        } else {
            values.set(v, values.get(v) + 1);
        }
    }

    public void clear() {
        values = new ArrayList<>();
        for (int i = 0; i < max; ++i) {
            values.add(0);
        }
    }

    @Override
    public String toString() {
        return values.toString();
    }

    public static void main(String[] args) {
        Metric metric = new Metric(20);
        System.out.println(metric);
        metric.add(10);
        System.out.println(metric);
    }
}
