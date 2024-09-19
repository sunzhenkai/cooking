package pub.wii.cook.java.json;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.tuple.Pair;

public class FastjsonConvertTest {
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
        json.put("params", jsonParams.toJSONString());
        System.out.println(jsonParams);
        System.out.println(json);
        return Pair.of(jsonParams.toJSONString(), json.toJSONString());
    }

    public static void main(String[] args) {
    }
}
