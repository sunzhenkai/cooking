package pub.wii.cook.java.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.*;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.curator.x.discovery.details.ServiceCacheListener;

import java.io.Closeable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static pub.wii.cook.java.discovery.Constance.BASE_PATH;

public class Discovery<T> implements Closeable {
    private final ServiceDiscovery<T> discovery;
    private final Map<String, ServiceProvider<T>> provider = new ConcurrentHashMap<>();
    private final Map<String, ServiceCache<T>> cache = new ConcurrentHashMap<>();

    public Discovery(CuratorFramework cf, Class<T> cls) throws Exception {
        JsonInstanceSerializer<T> serializer = new JsonInstanceSerializer<>(cls);
        discovery = ServiceDiscoveryBuilder.builder(cls)
                .client(cf)
                .basePath(BASE_PATH)
                .serializer(serializer)
                .build();
        discovery.start();
    }

    private ServiceProvider<T> buildProvider(InstanceDetail<T> id) throws Exception {
        ServiceProvider<T> pvd = discovery.serviceProviderBuilder()
                .serviceName(id.getName())
                .build();
        pvd.start();
        return pvd;
    }

    private ServiceProvider<T> getProvider(InstanceDetail<T> id) throws Exception {
        ServiceProvider<T> res = provider.get(id.getName());
        if (res == null) {
            synchronized (provider) {
                res = buildProvider(id);
                provider.put(id.getName(), res);
            }
        }
        return res;
    }

    public void register(InstanceDetail<T> id) throws Exception {
        ServiceInstance<T> instance = ServiceInstance.<T>builder()
                .port(id.getPort())
                .payload(id.getPayload())
                .address(id.getAddress())
                .name(id.getName())
                .id(id.getId())
                .build();
        discovery.registerService(instance);
    }

    public void unregister(InstanceDetail<T> id) throws Exception {
        ServiceInstance<T> instance = ServiceInstance.<T>builder()
                .port(id.getPort())
                .payload(id.getPayload())
                .address(id.getAddress())
                .name(id.getName())
                .id(id.getId())
                .build();
        discovery.unregisterService(instance);
    }

    public ServiceInstance<T> random(InstanceDetail<T> id) throws Exception {
        return getProvider(id).getInstance();
    }

    public Collection<ServiceInstance<T>> all(InstanceDetail<T> id) throws Exception {
        return getProvider(id).getAllInstances();
    }

    public ServiceCache<T> buildCache(InstanceDetail<T> id) throws Exception {
        ServiceCache<T> cc = discovery.serviceCacheBuilder().name(id.getName()).build();
        cc.start();
        return cc;
    }

    public ServiceCache<T> getCache(InstanceDetail<T> id) throws Exception {
        ServiceCache<T> res = cache.get(id.getName());
        if (res == null) {
            synchronized (cache) {
                res = buildCache(id);
                cache.put(id.getName(), res);
            }
        }
        return res;
    }

    public void listen(InstanceDetail<T> id, ServiceCacheListener listener) throws Exception {
        getCache(id).addListener(listener);
    }

    @Override
    public void close() {
        CloseableUtils.closeQuietly(discovery);
    }

    public Collection<String> queryForNames() throws Exception {
        return discovery.queryForNames();
    }
}
