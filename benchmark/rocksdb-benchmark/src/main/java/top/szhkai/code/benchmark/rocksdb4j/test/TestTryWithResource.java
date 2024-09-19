package top.szhkai.code.benchmark.rocksdb4j.test;

class Co implements AutoCloseable {
    String name;

    public Co(String name) {
        this.name = name;
    }

    @Override
    public void close() throws Exception {
        System.out.println("Close " + name);
    }
}

public class TestTryWithResource {
    public static void main(String[] args) {
        try (Co ca = new Co("Ca"); Co cb = new Co("cb")) {
        } catch (Exception e) {
        }
    }
}
/* Output
 Close cb
 Close Ca
 */