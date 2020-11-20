package top.szhkai.code.benchmark.rocksdb4j.test;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;

public class TestBasic {
    public static void main(String[] args) {
        RocksDB.loadLibrary();
        try (final Options options = new Options().setCreateIfMissing(true)) {
            try (final RocksDB db = RocksDB.open(options, "/Users/sunzhenkai/Tmp/rocks")) {
                db.put("HELLO".getBytes(), "World".getBytes());
                System.out.println("Hello " + new String(db.get("HELLO".getBytes())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
