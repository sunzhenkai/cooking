package pub.wii.cook.java.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.io.Closeable;
import java.io.IOException;

import static pub.wii.cook.java.discovery.Constance.BASE_PATH;

@Deprecated
public class Server implements Closeable {

    private final ServiceDiscovery<InstanceDetail> discovery;
    private final ServiceInstance<InstanceDetail> instance;

    public Server(CuratorFramework client, String name) throws Exception {
        this.instance = ServiceInstance.<InstanceDetail>builder()
                .name(name)
                .payload(new InstanceDetail(""))
                .port(8080)
                .build();
        JsonInstanceSerializer<InstanceDetail> serializer = new JsonInstanceSerializer<>(InstanceDetail.class);
        this.discovery = ServiceDiscoveryBuilder.builder(InstanceDetail.class)
                .client(client)
                .basePath(BASE_PATH)
                .serializer(serializer)
                .thisInstance(instance)
                .build();
    }

    public ServiceInstance<InstanceDetail> getInstance() {
        return instance;
    }

    public void start() throws Exception {
        discovery.start();
    }

    @Override
    public void close() throws IOException {
        CloseableUtils.closeQuietly(discovery);
    }

    public static void main(String[] args) throws Exception {
        // ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        // CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        // cf.start();
        // Server server = new Server(cf, SERVICE_NAME);
        // server.start();
        //
        // Discovery discovery = new Discovery(cf, SERVICE_NAME);
        // while (true) {
        //     System.out.println(discovery.all());
        //     // System.out.println(discovery.random());
        //     System.out.println(discovery.queryForNames());
        //     Thread.sleep(1000);
        // }
    }
}
