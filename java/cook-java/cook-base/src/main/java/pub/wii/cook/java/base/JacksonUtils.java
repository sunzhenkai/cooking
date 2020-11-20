package pub.wii.cook.java.base;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import pub.wii.cook.java.model.EnumTest;

import java.io.IOException;

public class JacksonUtils {
    private final static SimpleModule simpleModule =
            new SimpleModule()
                    .addSerializer(Double.class, new DoubleAdapter())
                    .addSerializer(EnumTest.class, new EnumTestSerialize())
                    .addSerializer(byte[].class, new ByteArrayAdapter());
    public final static ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(simpleModule)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private static class DoubleAdapter extends JsonSerializer<Double> {
        @Override
        public void serialize(Double value,
                              JsonGenerator jgen,
                              SerializerProvider serializerProvider) throws IOException {
            if (value == null || Double.isNaN(value) || Double.isInfinite(value)) {
                jgen.writeString("0.0");
            } else {
                jgen.writeString(String.valueOf(value));
            }
        }
    }

    private static class ByteArrayAdapter extends JsonSerializer<byte[]> {

        @Override
        public void serialize(byte[] bytes,
                              JsonGenerator jgen,
                              SerializerProvider serializerProvider) throws IOException {
            jgen.writeStartArray();
            for (byte b : bytes) {
                jgen.writeNumber(unsignedToBytes(b));
            }
            jgen.writeEndArray();
        }
    }

    private static int unsignedToBytes(byte b) {
        return b & 0xFF;
    }

    private static class EnumTestSerialize extends JsonSerializer<EnumTest> {

        @Override
        public void serialize(EnumTest node, JsonGenerator gen, SerializerProvider serializerProvider)
                throws IOException {
            gen.writeNumber(node.getValue());
        }
    }
}
