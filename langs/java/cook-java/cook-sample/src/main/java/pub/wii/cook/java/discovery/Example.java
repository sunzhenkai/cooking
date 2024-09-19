package pub.wii.cook.java.discovery;

import lombok.SneakyThrows;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.details.ServiceCacheListener;

public class Example {
    public static void main(String[] args) throws Exception {
        RetryPolicy rp = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", rp);
        cf.start();

        Payload pl = new Payload();
        pl.setMsg("payload-msg");
        InstanceDetail<Payload> ins = new InstanceDetail<>();
        ins.setAddress("127.0.0.1");
        ins.setPort(8080);
        ins.setName("service-test");
        ins.setPayload(pl);
        Discovery<Payload> discovery = new Discovery<>(cf, Payload.class);

        discovery.register(ins);
        discovery.listen(ins, new ServiceCacheListener() {
            @SneakyThrows
            @Override
            public void cacheChanged() {
                System.out.println("cache changed: " + discovery.all(ins).size() + ", " + discovery.all(ins));
            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                System.out.println("state changed: " + connectionState);
            }
        });

        while (true) {
            System.out.println(discovery.random(ins));
            Thread.sleep(5000);
        }
    }
}
