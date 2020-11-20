package top.szhkai.code.benchmark.rocksdb4j.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
    public static String buildString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static byte[] buildByteArray(int length) {
        return buildString(length).getBytes();
    }
}
