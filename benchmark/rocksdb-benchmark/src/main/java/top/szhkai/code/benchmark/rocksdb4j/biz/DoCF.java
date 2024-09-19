package top.szhkai.code.benchmark.rocksdb4j.biz;

import com.google.common.collect.Lists;
import org.rocksdb.*;
import top.szhkai.code.benchmark.rocksdb4j.utils.Metric;
import top.szhkai.code.benchmark.rocksdb4j.utils.Utils;

import java.util.*;

public class DoCF {
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

    public static void sample(RocksDB db, List<ColumnFamilyHandle> columnFamilyHandleList) throws RocksDBException {
        db.put(columnFamilyHandleList.get(0), "hello".getBytes(), "world".getBytes());
        String value = new String(db.get(columnFamilyHandleList.get(0), "hello".getBytes()));
        System.out.println("hello " + value);
    }

    public static void main(String[] args) throws RocksDBException {
        RocksDB.loadLibrary();
        try (final ColumnFamilyOptions cfOpts = new ColumnFamilyOptions().optimizeUniversalStyleCompaction()) {
            final List<ColumnFamilyDescriptor> cfDescriptors = Arrays.asList(
                    new ColumnFamilyDescriptor(RocksDB.DEFAULT_COLUMN_FAMILY, cfOpts),
                    new ColumnFamilyDescriptor("hello".getBytes(), cfOpts)
            );

            final List<ColumnFamilyHandle> columnFamilyHandleList = new ArrayList<>();

            try (final DBOptions options = new DBOptions()
                    .setCreateIfMissing(true)
                    .setCreateMissingColumnFamilies(true);
                 final RocksDB db = RocksDB.open(
                         options,
                         "/Users/sunzhenkai/Tmp/rocks",
                         cfDescriptors,
                         columnFamilyHandleList)
            ) {
                // doTest(db);
                sample(db, columnFamilyHandleList);
            } finally {
                for (final ColumnFamilyHandle columnFamilyHandle: columnFamilyHandleList) {
                    columnFamilyHandle.close();
                }
            }
        }
    }
}
