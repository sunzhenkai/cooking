package pub.wii.cook.springboot.es;

import lombok.Getter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class ESService {
    @Value("${es.host}")
    private String host;

    @Value("${es.port}")
    private int port;

    RestHighLevelClient rest;

    public ESService() {
        this.rest = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port)));
    }
}
