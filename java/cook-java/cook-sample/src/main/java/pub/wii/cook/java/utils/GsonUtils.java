package pub.wii.cook.java.utils;

import com.google.gson.*;

import java.lang.reflect.Type;

public class GsonUtils {
    static GsonBuilder gsonBuilder = new GsonBuilder();
    private static final Gson gson = new Gson();
    public static final Gson GSON = gsonBuilder
            .registerTypeAdapter(Double.class, new DoubleAdapter())
            .registerTypeAdapter(Float.class, new FloatAdapter())
            .registerTypeAdapter(Number.class, new NaNExcludeAdapter())
            .registerTypeAdapter(Boolean.class, new BooleanAdapter())
            .registerTypeAdapter(boolean.class, new BooleanAdapter())
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                    return fieldAttributes.getName().equals("__isset_bit_vector");
                }

                @Override
                public boolean shouldSkipClass(Class<?> aClass) {
                    return false;
                }
            })
            .create();

    private static class DoubleAdapter implements JsonSerializer<Double> {
        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null || Double.isNaN(src) || Double.isInfinite(src)) {
                return null;
            } else {
                return gson.toJsonTree(src);
            }
        }
    }

    private static class FloatAdapter implements JsonSerializer<Float> {
        @Override
        public JsonElement serialize(Float src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null || Float.isNaN(src) || Float.isInfinite(src)) {
                return gson.toJsonTree(0);
            } else {
                return gson.toJsonTree(src);
            }
        }
    }

    private static class NaNExcludeAdapter implements JsonSerializer<Object> {
        @Override
        public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null ||
                    (src instanceof Double && (Double.isNaN((double) src) || Double.isInfinite((double) src))) ||
                    (src instanceof Float && (Double.isNaN((float) src) || Double.isInfinite((float) src)))
            ) {
                return gson.toJsonTree(0);
            } else {
                return gson.toJsonTree(src);
            }
        }
    }

    private static class BooleanAdapter implements JsonDeserializer<Boolean> {

        @Override
        public Boolean deserialize(JsonElement json,
                                   Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {
            return json.getAsString().equals("true") || json.getAsInt() == 1;
        }
    }
}
