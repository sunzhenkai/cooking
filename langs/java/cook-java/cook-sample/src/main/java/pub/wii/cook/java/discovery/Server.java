package pub.wii.cook.java.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.io.Closeable;
import java.io.IOException;

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
                .basePath("services")
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
}
