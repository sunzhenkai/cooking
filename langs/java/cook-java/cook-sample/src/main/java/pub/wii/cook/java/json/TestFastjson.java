package pub.wii.cook.java.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;
import pub.wii.cook.java.base.GsonUtils;
import pub.wii.cook.java.base.JacksonUtils;
import pub.wii.cook.java.model.Node;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestFastjson {
    public static void main(String[] args) throws JsonProcessingException {
        Gson gson = new Gson();
        String s = "{\n" +
                "  \"ABC|music\":{\n" +
                "    \"min\":20,\n" +
                "    \"max\":100,\n" +
                "    \"queueSize\":3000\n" +
                "  },\n" +
                "  \"ABC|musicV61\":{\n" +
                "    \"min\":10,\n" +
                "    \"max\":100,\n" +
                "    \"queueSize\":3000\n" +
                "  },\n" +
                "  \"ABC|radio\":{\n" +
                "    \"min\":10,\n" +
                "    \"max\":100,\n" +
                "    \"queueSize\":2000\n" +
                "  },\n" +
                "  \"ABC|mlGroup\":{\n" +
                "    \"min\":10,\n" +
                "    \"max\":100,\n" +
                "    \"queueSize\":2000\n" +
                "  },\n" +
                "  \"ABC|mlUsers\":{\n" +
                "    \"min\":10,\n" +
                "    \"max\":100,\n" +
                "    \"queueSize\":2000\n" +
                "  },\n" +
                "  \"ABC|yellowpageServiceV1\":{\n" +
                "    \"min\":10,\n" +
                "    \"max\":100,\n" +
                "    \"queueSize\":1200\n" +
                "  },\n" +
                "  \"ABC|as-search-v2-preview\":{\n" +
                "    \"min\":10,\n" +
                "    \"max\":100,\n" +
                "    \"queueSize\":1200\n" +
                "  },\n" +
                "  \"ABC|as-search-v2-staging\":{\n" +
                "    \"min\":10,\n" +
                "    \"max\":100,\n" +
                "    \"queueSize\":1200\n" +
                "  },\n" +
                "  \"theme-search|theme-color-search-online\":{\n" +
                "    \"min\":10,\n" +
                "    \"max\":100,\n" +
                "    \"queueSize\":600\n" +
                "  },\n" +
                "  \"miui-ads|as-search-ads\":{\n" +
                "    \"min\":20,\n" +
                "    \"max\":100,\n" +
                "    \"queueSize\":600\n" +
                "  },\n" +
                "  \"SHARED\":{\n" +
                "    \"min\":30,\n" +
                "    \"max\":500,\n" +
                "    \"queueSize\":3000\n" +
                "  }\n" +
                "}";

        JsonObject jsonConfig =
                gson.fromJson(s, JsonObject.class);
        for (Map.Entry<String, JsonElement> entry : jsonConfig.entrySet()) {
            JsonObject json = entry.getValue().getAsJsonObject();
            int min = json.get("min").getAsInt();
            int max = json.get("max").getAsInt();
            int queueSize = json.has("queueSize") ? json.get("queueSize").getAsInt() : 3000;
            System.out.printf("%d %d %d%n", min, max, queueSize);
        }

        String s2 = "{\"serviceEndpoint\":{\"host\":\"tj1\",\"port\":100},\"additionalEndpoints\":{},\"status\":\"ALIVE\",\"shard\":0}";
        JsonObject jo = gson.fromJson(s2, JsonObject.class);
        System.out.println(jo.get("shard").getAsInt());
        JsonObject serviceEndpoint = jo.getAsJsonObject("serviceEndpoint");
        System.out.println(serviceEndpoint.get("port").getAsInt());
        System.out.println(serviceEndpoint.get("host").getAsString());

        String s4 = "[{\"id\": \"a\"},{\"id\": \"b\"}]";
        JsonArray ja = gson.fromJson(s4, JsonArray.class);
        for (JsonElement je : ja) {
            System.out.println(je.getAsJsonObject().get("id").getAsString());
        }

        String s5 = "{\"d\":[{\"id\": \"a\"},{\"id\": [\"b\", \"c\"]}],\"f\":1}";
        jo = gson.fromJson(s5, JsonObject.class);
        for (Map.Entry<String, JsonElement> entry : jo.entrySet()) {
            if (entry.getValue() instanceof JsonArray) {
                System.out.println("YES");
                System.out.println(entry.getValue().getAsJsonArray());
            }
        }

        JsonNode jnoA = JacksonUtils.MAPPER.readValue(s5, JsonNode.class);
        System.out.println("001 " + (jnoA.get("f") instanceof ValueNode));
        // System.out.println(jo.get("NO").getAsString());
        System.out.println();

        // Types
        // ObjectNode
        // JsonNode   <--->   JsonElements
        // ArrayNode
        // ValueNode
        // ValueNode  <-?->   JsonPrimitive

        JsonNode jno = JacksonUtils.MAPPER.readValue(s5, JsonNode.class);
        System.out.println("000 " + jno);

        String s6 = "[{\"host\":\"127.0.0.1\", \"shardId\": 0}, {\"host\": \"127.0.0.2\", \"shardId\": 0}]";
        Node[] nodes = gson.fromJson(s6, Node[].class);
        List<Node> nodeList = Arrays.asList(nodes);
        System.out.println(nodeList);

        JsonNode on = JacksonUtils.MAPPER.readValue(s6, JsonNode.class);
        System.out.println(on);
        if (on instanceof ArrayNode) {
            System.out.println(on.get(0));
        }

        for (JsonNode node : on) {
            System.out.println("002 " + (node instanceof ObjectNode));
            System.out.println("002 " + node);
        }

        // ObjectNode valueNode = JacksonUtils.MAPPER.readValue(s6, ObjectNode.class);
        // System.out.println(valueNode);

        String s7 = "{\"em\":\"LONG\",\"emInt\":1}";
        Node node7 = JacksonUtils.MAPPER.readValue(s7, Node.class);
        System.out.println("003 " + JacksonUtils.MAPPER.writeValueAsString(node7));
        System.out.println("004 " + GsonUtils.GSON.fromJson(s7, Node.class));

        ObjectNode on8 = (ObjectNode) JacksonUtils.MAPPER.readValue(s2, JsonNode.class);
        for (Iterator<Map.Entry<String, JsonNode>> it = on8.fields(); it.hasNext(); ) {
            System.out.println("008 " + it.next());
        }
    }
}
