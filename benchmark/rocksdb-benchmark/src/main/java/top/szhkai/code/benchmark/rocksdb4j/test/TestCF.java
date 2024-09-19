package top.szhkai.code.benchmark.rocksdb4j.test;

import org.rocksdb.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCF {
    public static void main(String[] args) throws RocksDBException {
        RocksDB.loadLibrary();
        try (final ColumnFamilyOptions cfOpts = new ColumnFamilyOptions().optimizeUniversalStyleCompaction()) {
            final List<ColumnFamilyDescriptor> cfDescriptors = Arrays.asList(
                    new ColumnFamilyDescriptor(RocksDB.DEFAULT_COLUMN_FAMILY, cfOpts),
                    new ColumnFamilyDescriptor("example-cf".getBytes(), cfOpts)
            );

            final List<ColumnFamilyHandle> columnFamilyHandleList = new ArrayList<>();

            try (final DBOptions options = new DBOptions()
                 .setCreateIfMissing(true)
                 .setCreateMissingColumnFamilies(true);
                 final RocksDB db = RocksDB.open(
                         options,
                         "",
                         cfDescriptors,
                         columnFamilyHandleList)
            ) {
                // db.
            } finally {
                for (final ColumnFamilyHandle columnFamilyHandle: columnFamilyHandleList) {
                    columnFamilyHandle.close();
                }
            }
        }
    }
}
