package pub.wii.cook.java.sample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutoCloseSample implements AutoCloseable {
    public final String value;

    public AutoCloseSample(String value) {
        this.value = value;
    }

    @Override
    public void close() throws Exception {
        System.out.println("close " + value);
    }

    public static void main(String[] args) {
        try (AutoCloseSample ignored = new AutoCloseSample("jdbc")) {
            System.out.println("run");
            throw new Exception("HELLO");
        } catch (Exception e) {
            log.error("unexpected exception", e);
        }
    }
}
