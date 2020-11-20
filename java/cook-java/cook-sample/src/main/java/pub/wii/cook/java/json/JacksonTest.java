package pub.wii.cook.java.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import pub.wii.cook.java.base.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JacksonTest {
    public static void main(String[] args) throws InterruptedException, JsonProcessingException {
        int loopTimes = 10000;
        int tdNo = 30;
        List<Callable<Void>> cs;
        ExecutorService executorService = Executors.newFixedThreadPool(tdNo);
        String s = "{\"type\":\"INDEX\",\"id\":\"idValue\",\"doc\":{\"id\":\"idValue\",\"fields\":[{\"schema\":{\"name\":\"id\",\"type\":0,\"stored\":true,\"tokenized\":false,\"indexed\":true,\"omitNorms\":false,\"snippet\":false,\"docValues\":true,\"multi\":false,\"omitFreqs\":false,\"dynamicSchema\":false,\"docValuesFormat\":\"LUCENE54\",\"__isset_bit_vector\":{\"words\":[37],\"empty\":false},\"setName\":true,\"setType\":true,\"setStored\":true,\"setTokenized\":false,\"setIndexed\":true,\"setOmitNorms\":false,\"setSnippet\":false,\"setDocValues\":true,\"setMulti\":false,\"setOmitFreqs\":false,\"setDynamicSchema\":false,\"setDocValuesFormat\":true},\"value\":\"idValue\",\"setSchema\":true,\"setValue\":true},{\"schema\":{\"name\":\"source\",\"type\":0,\"stored\":true,\"tokenized\":false,\"indexed\":true,\"omitNorms\":false,\"snippet\":false,\"docValues\":true,\"multi\":false,\"omitFreqs\":false,\"dynamicSchema\":false,\"docValuesFormat\":\"LUCENE54\",\"__isset_bit_vector\":{\"words\":[37],\"empty\":false},\"setName\":true,\"setType\":true,\"setStored\":true,\"setTokenized\":false,\"setIndexed\":true,\"setOmitNorms\":false,\"setSnippet\":false,\"setDocValues\":true,\"setMulti\":false,\"setOmitFreqs\":false,\"setDynamicSchema\":false,\"setDocValuesFormat\":true},\"value\":\"{\\\"type\\\":\\\"typeValue\\\"}\",\"setSchema\":true,\"setValue\":true}],\"setId\":true,\"fieldsSize\":2,\"fieldsIterator\":{\"cursor\":0,\"lastRet\":-1,\"expectedModCount\":2},\"setFields\":true,\"setCoordinate\":false},\"setId\":true,\"setType\":true,\"setRouteParam\":false,\"setDoc\":true,\"setIndexName\":false}";
        JsonUtil.fromJson(s);
        GsonUtils.GSON.fromJson(s, JsonObject.class);
        JSON.parseObject(s, JSONObject.class);

        long startTs = System.currentTimeMillis();
        for (int i = 0; i < loopTimes; ++i) {
            JsonUtil.fromJson(s);
        }
        System.out.println(System.currentTimeMillis() - startTs);

        startTs = System.currentTimeMillis();
        for (int i = 0; i < loopTimes; ++i) {
            GsonUtils.GSON.fromJson(s, JsonObject.class);
        }
        System.out.println(System.currentTimeMillis() - startTs);

        startTs = System.currentTimeMillis();
        for (int i = 0; i < loopTimes; ++i) {
            JSON.parseObject(s, JSONObject.class);
        }
        System.out.println(System.currentTimeMillis() - startTs);

        System.out.println();

        // warm up
        cs = new ArrayList<>();
        for (int j = 0; j < tdNo; ++j) {
            cs.add(() -> {
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = GsonUtils.GSON.fromJson(s, JsonObject.class);
                    GsonUtils.GSON.toJson(obj);
                    obj = JsonUtil.fromJson(s, JSONObject.class);
                    JsonUtil.toJson(obj);
                    obj = JSON.parseObject(s, JSONObject.class);
                    JSON.toJSONString(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);

        // multi thread
        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < tdNo; ++j) {
            cs.add(() -> {
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = JsonUtil.fromJson(s, JSONObject.class);
                    JsonUtil.toJson(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        System.out.println("Jackson Static\t\t: " + (System.currentTimeMillis() - startTs));

        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < tdNo; ++j) {
            cs.add(() -> {
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = JsonUtil.fromJson(s, JSONObject.class);
                    JsonUtil.toJson(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        System.out.println("Jackson Static\t\t: " + (System.currentTimeMillis() - startTs));

        ObjectMapper mapper = new ObjectMapper();
        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < tdNo; ++j) {
            cs.add(() -> {
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = mapper.readValue(s, JSONObject.class);
                    mapper.writeValueAsString(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        System.out.println("Jackson Local\t\t: " + (System.currentTimeMillis() - startTs));

        JsonUtilNon[] ns = new JsonUtilNon[tdNo];
        for (int i = 0; i < tdNo; ++i) {
            ns[i] = new JsonUtilNon();
            ns[i].fromJson(s);
        }

        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < tdNo; ++j) {
            final int no = j;
            cs.add(() -> {
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = ns[no].fromJson(s, JSONObject.class);
                    ns[no].toJson(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        // System.out.println(System.currentTimeMillis() - startTs);
        System.out.println("Jackson NonStatic\t: " + (System.currentTimeMillis() - startTs));

        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < tdNo; ++j) {
            cs.add(() -> {
                JsonUtilNon non = new JsonUtilNon();
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = non.fromJson(s, JSONObject.class);
                    non.toJson(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        // System.out.println(System.currentTimeMillis() - startTs);
        System.out.println("Jackson Non & New\t: " + (System.currentTimeMillis() - startTs));

        // startTs = System.currentTimeMillis();
        // cs = new ArrayList<>();
        // for (int j = 0; j < tdNo; ++j) {
        //     cs.add(() -> {
        //         for (int i = 0; i < loopTimes; ++i) {
        //             Object obj = JsonUtilB.fromJson(s, JSONObject.class);
        //             JsonUtilB.toJson(obj);
        //         }
        //         return null;
        //     });
        // }
        // executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        // // System.out.println(System.currentTimeMillis() - startTs);
        // System.out.println("Jackson UtilB\t\t: " + (System.currentTimeMillis() - startTs));

        // startTs = System.currentTimeMillis();
        // cs = new ArrayList<>();
        // for (int j = 0; j < tdNo; ++j) {
        //     cs.add(() -> {
        //         for (int i = 0; i < loopTimes; ++i) {
        //             Object obj = JsonUtilPool.fromJson(s, JSONObject.class);
        //             JsonUtilPool.toJson(obj);
        //         }
        //         return null;
        //     });
        // }
        // executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        // // System.out.println(System.currentTimeMillis() - startTs);
        // System.out.println("Jackson Pool\t\t: " + (System.currentTimeMillis() - startTs));

        // startTs = System.currentTimeMillis();
        // cs = new ArrayList<>();
        // for (int j = 0; j < tdNo; ++j) {
        //     cs.add(() -> {
        //         for (int i = 0; i < loopTimes; ++i) {
        //             Object obj = JsonUtilPool.fromJson(s, JSONObject.class);
        //             JsonUtilPool.toJson(obj);
        //         }
        //         return null;
        //     });
        // }
        // executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        // // System.out.println(System.currentTimeMillis() - startTs);
        // System.out.println("Jackson Pool\t\t: " + (System.currentTimeMillis() - startTs));

        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < tdNo; ++j) {
            cs.add(() -> {
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = GsonUtils.GSON.fromJson(s, JsonObject.class);
                    GsonUtils.GSON.toJson(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        // System.out.println(System.currentTimeMillis() - startTs);
        System.out.println("GSON Static\t\t\t: " + (System.currentTimeMillis() - startTs));

        Gson[] ng = new Gson[tdNo];
        for (int i = 0; i < tdNo; ++i) {
            ng[i] = new Gson();
            ng[i].fromJson(s, JsonObject.class);
        }
        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < tdNo; ++j) {
            final int no = j;
            cs.add(() -> {
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = ng[no].fromJson(s, JsonObject.class);
                    ng[no].toJson(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        // System.out.println(System.currentTimeMillis() - startTs);
        System.out.println("GSON NonStatic\t\t: " + (System.currentTimeMillis() - startTs));


        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < tdNo; ++j) {
            cs.add(() -> {
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = JSON.parseObject(s, JSONObject.class);
                    JSON.toJSONString(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        System.out.println("Fastjson Static\t\t: " + (System.currentTimeMillis() - startTs));

        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < tdNo; ++j) {
            cs.add(() -> {
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = JSON.parseObject(s, JSONObject.class);
                    JSON.toJSONString(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        System.out.println("Fastjson Static\t\t: " + (System.currentTimeMillis() - startTs));

        executorService.shutdownNow();
    }
}
