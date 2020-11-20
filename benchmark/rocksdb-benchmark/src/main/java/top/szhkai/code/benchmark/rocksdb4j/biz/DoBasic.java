package top.szhkai.code.benchmark.rocksdb4j.biz;

import org.rocksdb.*;
import top.szhkai.code.benchmark.rocksdb4j.utils.Metric;
import top.szhkai.code.benchmark.rocksdb4j.utils.Utils;

import java.util.*;

public class DoBasic {
    public static final int size = 10000;
    public static final int keyLen = 32;
    public static final int valueLen = 50 * 1024;

    public static void doTest(RocksDB db) throws RocksDBException {
        long startTs;
        Map<byte[], byte[]> data = new HashMap<>();
        Metric metric = new Metric(50);
        for (int i = 0; i < size; ++i) {
            byte[] key = Utils.buildByteArray(keyLen);
            byte[] value = Utils.buildByteArray(valueLen);
            startTs = System.currentTimeMillis();
            db.put(key, value);
            metric.add(System.currentTimeMillis() - startTs);
            data.put(key, value);
        }

        System.out.println("Put done.");
        System.out.println(metric);
        metric.clear();
        for (Map.Entry<byte[], byte[]> entry : data.entrySet()) {
            startTs = System.currentTimeMillis();
            byte[] value = db.get(entry.getKey());
            metric.add(System.currentTimeMillis() - startTs);
            if (!Arrays.equals(value, entry.getValue())) {
                System.out.println(String.format("key [%s]'s value is not right", new String(entry.getKey())));
            }
        }

        System.out.println(metric);
        System.out.println("All is done.");
    }

    public static void main(String[] args) {
        RocksDB.loadLibrary();
        try (final Options options = new Options().setCreateIfMissing(true);
             final RocksDB db = RocksDB.open(options, "/Users/sunzhenkai/Tmp/rocks");
             final RocksIterator it = db.newIterator()) {
            while (it.isValid()) {
                System.out.println(new String(it.key()));
            }
            // doTest(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
