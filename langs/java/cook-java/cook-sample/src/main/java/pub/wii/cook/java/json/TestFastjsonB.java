package pub.wii.cook.java.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;
import pub.wii.cook.java.base.JacksonUtils;
import pub.wii.cook.java.utils.GsonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestFastjsonB {
    public static void main(String[] args) throws JsonProcessingException {
        Node node = new Node();
        node.b = true;
        // System.out.println(new Gson().toJson(node));
        System.out.println(new ObjectMapper().writeValueAsString(node));
        System.out.println(JSON.toJSON(node));

        String s = "{\"b\": 1}";
        GsonUtils.GSON.fromJson(s, Node.class);
        new ObjectMapper().readValue(s, Node.class);
        JSON.parseObject(s, Node.class);

        String s2 = "{\"b\": 1.1, \"c\":{\"b\": 1.1}}";
        JsonNode rd = JacksonUtils.MAPPER.readValue(s2, JsonNode.class);
        System.out.println((float) rd.get("b").asDouble());
        System.out.println(rd instanceof ValueNode);
        System.out.println(rd.get("b") instanceof ValueNode);
        if (rd.get("c") instanceof ValueNode) {
            System.out.println(JacksonUtils.MAPPER.readValue(rd.get("c").asText(), JsonNode.class));
            System.out.println(JacksonUtils.MAPPER.readValue(rd.get("c").asText(), JsonNode.class));
        }

        ObjectNode jn2 = JacksonUtils.MAPPER.createObjectNode();
        jn2.put("c", s2);
        String s3 = jn2.toString();
        System.out.println(s3);

        if (jn2.get("c") instanceof ValueNode) {
            System.out.println(jn2.get("c").toString());
            System.out.println(JacksonUtils.MAPPER.readValue(jn2.get("c").asText(), JsonNode.class));
        }

        String content = "{\"id\":\"18617\","
                + "\"sName\":\"杭州萧山明达园艺场\","
                + "\"catId\":4,"
                + "\"latitude\":0,"
                + "\"longitude\":0,"
                + "}";
        JSONObject jo = JSON.parseObject(content, JSONObject.class);
        System.out.println(jo);

        // new Gson().fromJson(content, JsonObject.class);

        String jsonStr = "{\n"
                + "  \"id\": \"1\",\n"
                + "  \"name\": \"appstore-search\",\n"
                + "  \"level\": \"info\",\n"
                + "  \"log\": \"search result is empty\",\n"
                + "  \"host\": \"xiaomi-search01.bj\",\n"
                + "  \"shard\": \"1\",\n"
                + "  \"_dynamic\": [\n"
                + "    {\n"
                + "      \"mgroup\": \"misearch\",\n"
                + "      \"_type\": \"string\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"cost\": 30,\n"
                + "      \"_type\": \"long\",\n"
                + "    },\n"
                + "    {\n"
                + "      \"num\": 7.7,\n"
                + "      \"_type\": \"float\",\n"
                + "    },\n"
                + "    {\n"
                + "      \"count\": 3,\n"
                + "      \"_type\": \"int\",\n"
                + "    },\n"
                + "    {\n"
                + "      \"val\": \"10.0\",\n"
                + "      \"_type\": \"double\",\n"
                + "    }\n"
                + "    {\n"
                + "      \"text\": \"this is a tokenized string field\",\n"
                + "      \"_tokenize\": \"true\",\n"
                + "    }\n"
                + "  ]\n"
                + "}";

        jsonStr = "{\n"
                + "  \"id\": \"1\",\n"
                + "  \"schema\": [\n"
                + "    {\n"
                + "      \"val\": \"10.0\",\n"
                + "      \"type\": \"double\",\n"
                + "    }\n"
                + "    {\n"
                + "      \"val\": \"true\",\n"
                + "      \"type\": \"boolean\",\n"
                + "    }\n"
                + "  ]\n"
                + "}";
        System.out.println();
        System.out.println(jsonStr);
        jo = JSON.parseObject(jsonStr, JSONObject.class);
        System.out.println("==== fastjson ==== ");
        System.out.println(jo);

        System.out.println("==== gson ==== ");
        new Gson().fromJson(jsonStr, JsonNode.class);
    }

    @Data
    static class Node {
        String host;
        int shardId;
        boolean b;
    }
}
