package pub.wii.cook.java.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import pub.wii.cook.java.base.GsonUtils;
import pub.wii.cook.java.base.JacksonUtils;

public class TestJackson {
    public static final ObjectMapper MAPPER = JacksonUtils.MAPPER;
    public static final Gson GSON = GsonUtils.GSON;

    public static String serialize(Object obj) throws JsonProcessingException {
        return MAPPER.writeValueAsString(obj);
    }

    public static void main(String[] args) throws JsonProcessingException {
        Inner inner = new Inner();
        inner.d = 0.0;
        inner.dn = Double.NaN;
        inner.da = 0.1;
        inner.number = Double.NaN;
        inner.o = Double.NaN;
        inner.bts = "Hello".getBytes();
        System.out.println(GSON.toJson(inner));
        System.out.println(serialize(inner));
        System.out.println(JSON.toJSON(inner));

        System.out.println(serialize(GSON.fromJson(serialize(inner), Inner.class)));
    }

    static class Inner {
        Double d;
        Double dn;
        Double da;
        Number number;
        Object o;
        byte[] bts;
    }
}
