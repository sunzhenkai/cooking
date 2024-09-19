package pub.wii.cook.java.json;

import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONObject;

public class JsonConvertTest {
    @SneakyThrows
    public static Pair<String, String> getJsonString() {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("linden.version", "0.0.1");
        jsonParams.put("index.refresh.time", "60");
        jsonParams.put("search.rate.limit", 200);
        jsonParams.put("enable.parallel.search", false);
        JSONObject json = new JSONObject();
        json.put("domain", "domain");
        json.put("cluster", "cluster");
        json.put("schema", "schema");
        json.put("plugins", "plugins");
        json.put("params", jsonParams.toString());
        System.out.println(jsonParams);
        System.out.println(json);
        return Pair.of(jsonParams.toString(), json.toString());
    }
}
