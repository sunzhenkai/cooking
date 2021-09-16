package pub.wii.cook.java.etcd;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;
import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

public class KVExample {
    @SneakyThrows
    public static void main(String[] args) {
        Client client = Client.builder().endpoints("http://localhost:2379").build();
        KV kvClient = client.getKVClient();

        ByteSequence key = ByteSequence.from("foo".getBytes());
        ByteSequence value = ByteSequence.from("bar".getBytes());
        kvClient.put(key, value);

        CompletableFuture<GetResponse> getFuture = kvClient.get(key);
        GetResponse response = getFuture.get();
        KeyValue kv = response.getKvs().get(0);
        System.out.println(kv.getKey() + ": " + kv.getValue());

        client.getLeaseClient();
    }
}
