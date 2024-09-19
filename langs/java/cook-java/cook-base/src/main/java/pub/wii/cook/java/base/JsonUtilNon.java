package pub.wii.cook.java.base;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

public class JsonUtilNon {

    private final SimpleModule simpleModule =
            new SimpleModule()
                    .addSerializer(Double.class, new DoubleAdapter())
                    .addSerializer(double.class, new DoubleAdapter())
                    .addSerializer(Float.class, new FloatAdapter())
                    .addSerializer(float.class, new FloatAdapter())
                    .addSerializer(byte[].class, new ByteArrayAdapter());

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(simpleModule)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public byte[] toBytes(Object object) {
        try {
            return mapper.writeValueAsBytes(object);
        } catch (Exception e) {
            throw new RuntimeException("Serialize object to json failed, " + e.getMessage(), e);
        }
    }

    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Serialize object to json failed, " + e.getMessage(), e);
        }
    }

    public String toJson(JsonNode object, boolean removeNullNode) {
        if (removeNullNode) {
            removeNullNode(object);
        }
        return toJson(object);
    }

    public <T> T fromJson(String jsonStr, Class<T> objClass) {
        try {
            return mapper.readValue(jsonStr, objClass);
        } catch (Exception e) {
            throw new RuntimeException("Deserialize object from json failed, " + e.getMessage(), e);
        }
    }

    public JsonNode fromJson(String jsonStr) {
        return fromJson(jsonStr, JsonNode.class);
    }

    public ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    public ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }


    public boolean asBoolean(JsonNode node, String key) {
        return asBoolean(node, key, false);
    }

    public boolean asBoolean(JsonNode node, String key, boolean dft) {
        if (node.has(key)) {
            return node.get(key).asBoolean();
        } else {
            return dft;
        }
    }

    public String asText(JsonNode node, String key) {
        return asText(node, key, null);
    }

    public String asText(JsonNode node, String key, String dft) {
        if (node.has(key)) {
            return node.get(key).asText();
        } else {
            return dft;
        }
    }

    public int asInt(JsonNode node, String key) {
        return asInt(node, key, 0);
    }

    public int asInt(JsonNode node, String key, int dft) {
        if (node.has(key)) {
            return node.get(key).asInt();
        } else {
            return dft;
        }
    }

    public long asLong(JsonNode node, String key) {
        return asLong(node, key, 0);
    }

    public long asLong(JsonNode node, String key, long dft) {
        if (node.has(key)) {
            return node.get(key).asLong();
        } else {
            return dft;
        }
    }

    public double asDouble(JsonNode node, String key) {
        return asDouble(node, key, 0);
    }

    public double asDouble(JsonNode node, String key, double dft) {
        if (node.has(key)) {
            return node.get(key).asDouble();
        } else {
            return dft;
        }
    }

    public float asFloat(JsonNode node, String key) {
        return asFloat(node, key, 0);
    }

    public float asFloat(JsonNode node, String key, float dft) {
        if (node.has(key)) {
            return (float) node.get(key).asDouble();
        } else {
            return dft;
        }
    }

    public ArrayNode asArray(JsonNode node, String key) {
        JsonNode res = node.get(key);
        return (ArrayNode) res;
    }

    public void put(ObjectNode objectNode, String key, Object obj) {
        if (obj == null) {
            // ignore null value
        } else if (obj instanceof String) {
            objectNode.put(key, (String) obj);
        } else if (obj instanceof Integer) {
            objectNode.put(key, (Integer) obj);
        } else if (obj instanceof Long) {
            objectNode.put(key, (Long) obj);
        } else if (obj instanceof Short) {
            objectNode.put(key, (Short) obj);
        } else if (obj instanceof Float) {
            objectNode.put(key, (Float) obj);
        } else if (obj instanceof Double) {
            objectNode.put(key, (Double) obj);
        } else if (obj instanceof BigDecimal) {
            objectNode.put(key, (BigDecimal) obj);
        } else if (obj instanceof Boolean) {
            objectNode.put(key, (Boolean) obj);
        } else if (obj instanceof byte[]) {
            objectNode.put(key, (byte[]) obj);
        } else if (obj instanceof JsonNode) {
            objectNode.set(key, (JsonNode) obj);
        } else {
            throw new IllegalArgumentException("Unsupported type " + obj.getClass());
        }
    }

    public void add(ArrayNode arrayNode, Object obj) {
        if (obj == null) {
            // ignore null value
        } else if (obj instanceof String) {
            arrayNode.add((String) obj);
        } else if (obj instanceof Integer) {
            arrayNode.add((Integer) obj);
        } else if (obj instanceof Long) {
            arrayNode.add((Long) obj);
        } else if (obj instanceof Short) {
            arrayNode.add((Short) obj);
        } else if (obj instanceof Float) {
            arrayNode.add((Float) obj);
        } else if (obj instanceof Double) {
            arrayNode.add((Double) obj);
        } else if (obj instanceof BigDecimal) {
            arrayNode.add((BigDecimal) obj);
        } else if (obj instanceof Boolean) {
            arrayNode.add((Boolean) obj);
        } else if (obj instanceof byte[]) {
            arrayNode.add((byte[]) obj);
        } else if (obj instanceof JsonNode) {
            arrayNode.add((JsonNode) obj);
        } else {
            throw new IllegalArgumentException("Unsupported type " + obj.getClass());
        }
    }

    public void removeNullNode(JsonNode node) {
        Iterator<JsonNode> it = node.iterator();
        while (it.hasNext()) {
            JsonNode child = it.next();
            if (child.isNull()) {
                it.remove();
            } else {
                removeNullNode(child);
            }
        }
    }

    private static class DoubleAdapter extends JsonSerializer<Double> {

        @Override
        public void serialize(Double value,
                              JsonGenerator gen,
                              SerializerProvider serializerProvider) throws IOException {
            if (value == null || Double.isNaN(value) || Double.isInfinite(value)) {
                gen.writeNumber(0.0);
            } else {
                gen.writeNumber(value);
            }
        }
    }

    private static class FloatAdapter extends JsonSerializer<Float> {

        @Override
        public void serialize(Float value,
                              JsonGenerator gen,
                              SerializerProvider serializerProvider) throws IOException {
            if (value == null || Float.isNaN(value) || Float.isInfinite(value)) {
                gen.writeNumber(0.0);
            } else {
                gen.writeNumber(value);
            }
        }
    }

    private static class ByteArrayAdapter extends JsonSerializer<byte[]> {

        @Override
        public void serialize(byte[] bytes,
                              JsonGenerator gen,
                              SerializerProvider serializerProvider) throws IOException {
            gen.writeStartArray();
            for (byte b : bytes) {
                gen.writeNumber(b & 0xFF);
            }
            gen.writeEndArray();
        }
    }
}
