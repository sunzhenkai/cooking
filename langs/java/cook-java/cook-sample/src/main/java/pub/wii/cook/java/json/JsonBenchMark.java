package pub.wii.cook.java.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import pub.wii.cook.java.base.GsonUtils;
import pub.wii.cook.java.base.JsonUtil;
import pub.wii.cook.java.base.JsonUtilNon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JsonBenchMark {
    public static final String s = "{\"id\":862,\"ownerId\":66,\"domainId\":53,\"cluster\":\"ai-teaching-search\",\"createdTime\":1590720396000,\"modifiedTime\":1598463352000,\"pluginsParam\":\"{\\\"search_analyzer\\\":\\\"jieba_globalsearch_search\\\",\\\"index_analyzer\\\":\\\"jieba_globalsearch_index\\\",\\\"search_similarity\\\":\\\"bm25similarity\\\"}\",\"params\":\"{\\\"linden.core.mode\\\":\\\"HOTSWAP\\\",\\\"enable.parallel.search\\\":\\\"true\\\",\\\"linden.version\\\":\\\"20200826-06904b2\\\",\\\"index.refresh.time\\\":60,\\\"search.rate.limit\\\":200,\\\"linden.java.args\\\":\\\"-server -d64 -Xmx2g -Xms2g -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSClassUnloadingEnabled -XX:CMSInitiatingOccupancyFraction=60 -XX:+UseCMSInitiatingOccupancyOnly -XX:MaxTenuringThreshold=15 -XX:+CMSScavengeBeforeRemark -XX:CMSFullGCsBeforeCompaction=1 -XX:+CMSParallelInitialMarkEnabled -XX:+PrintTenuringDistribution -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=50m -XX:+PrintHeapAtGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCApplicationStoppedTime\\\",\\\"enable.cache\\\":true,\\\"cache.duration\\\":120,\\\"cache.size\\\":10000}\",\"schemaParam\":\"{\\\"id\\\":\\\"id\\\",\\\"fields\\\":[{\\\"name\\\":\\\"main_name\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":1,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"sub_name\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":1,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"alias_name\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":1,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0,\\\"multi\\\":0},{\\\"name\\\":\\\"actors\\\",\\\"type\\\":0,\\\"stored\\\":1,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":1,\\\"snippet\\\":0,\\\"docValues\\\":0,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0,\\\"multi\\\":1},{\\\"name\\\":\\\"publisher\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":1,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"metaId\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"cp\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"on\\\",\\\"type\\\":1,\\\"stored\\\":0,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"payment\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"publish_time\\\",\\\"type\\\":2,\\\"stored\\\":0,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"publish_year\\\",\\\"type\\\":1,\\\"stored\\\":0,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"rank\\\",\\\"type\\\":3,\\\"stored\\\":0,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"subject\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":1,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0,\\\"multi\\\":0},{\\\"name\\\":\\\"grade\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":1,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0,\\\"multi\\\":0},{\\\"name\\\":\\\"exam\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":1,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"version\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":1,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"volume\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":1,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"tags\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":1,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"keywords\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":1,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"subject_term\\\",\\\"type\\\":0,\\\"stored\\\":1,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":1,\\\"snippet\\\":0,\\\"docValues\\\":0,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0,\\\"multi\\\":1},{\\\"name\\\":\\\"grade_term\\\",\\\"type\\\":0,\\\"stored\\\":1,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":1,\\\"snippet\\\":0,\\\"docValues\\\":0,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0,\\\"multi\\\":1},{\\\"name\\\":\\\"exam_term\\\",\\\"type\\\":0,\\\"stored\\\":1,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":1,\\\"snippet\\\":0,\\\"docValues\\\":0,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0,\\\"multi\\\":1},{\\\"name\\\":\\\"version_term\\\",\\\"type\\\":0,\\\"stored\\\":1,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":1,\\\"snippet\\\":0,\\\"docValues\\\":0,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0,\\\"multi\\\":1},{\\\"name\\\":\\\"volume_term\\\",\\\"type\\\":0,\\\"stored\\\":1,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":1,\\\"snippet\\\":0,\\\"docValues\\\":0,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0,\\\"multi\\\":1},{\\\"name\\\":\\\"tags_term\\\",\\\"type\\\":0,\\\"stored\\\":1,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":1,\\\"snippet\\\":0,\\\"docValues\\\":0,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0,\\\"multi\\\":1},{\\\"name\\\":\\\"_srcdata\\\",\\\"type\\\":0,\\\"stored\\\":0,\\\"tokenized\\\":0,\\\"indexed\\\":0,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"play_count\\\",\\\"type\\\":1,\\\"stored\\\":0,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"priority\\\",\\\"type\\\":1,\\\"stored\\\":0,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0},{\\\"name\\\":\\\"is_single\\\",\\\"type\\\":1,\\\"stored\\\":0,\\\"tokenized\\\":0,\\\"indexed\\\":1,\\\"omitNorms\\\":0,\\\"snippet\\\":0,\\\"docValues\\\":1,\\\"hierarchical\\\":0,\\\"omitFreqs\\\":0}]}\"}";

    public static void doBenchMark(int threadNumber, int loopTimes) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        List<Callable<Void>> cs;
        long startTs;

        // warm up
        for (int i = 0; i < loopTimes; ++i) {
            Object obj;
            obj = JsonUtil.fromJson(s);
            JsonUtil.toJson(obj);

            obj = GsonUtils.GSON.fromJson(s, JsonObject.class);
            GsonUtils.GSON.toJson(obj);

            obj = JSON.parseObject(s, JSONObject.class);
            JSON.toJSONString(obj);
        }

        startTs = System.currentTimeMillis();
        for (int i = 0; i < loopTimes; ++i) {
            Object obj;
            obj = JsonUtil.fromJson(s);
            JsonUtil.toJson(obj);
        }
        System.out.println("Jackson\t\t: " + (System.currentTimeMillis() - startTs));

        startTs = System.currentTimeMillis();
        for (int i = 0; i < loopTimes; ++i) {
            Object obj;
            obj = GsonUtils.GSON.fromJson(s, JsonObject.class);
            GsonUtils.GSON.toJson(obj);
        }
        System.out.println("GSON\t\t: " + (System.currentTimeMillis() - startTs));

        startTs = System.currentTimeMillis();
        for (int i = 0; i < loopTimes; ++i) {
            Object obj;
            obj = JSON.parseObject(s, JSONObject.class);
            JSON.toJSONString(obj);
        }
        System.out.println("Fastjson\t: " + (System.currentTimeMillis() - startTs));

        System.out.println();

        // warm up
        cs = new ArrayList<>();
        for (int j = 0; j < threadNumber; ++j) {
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
        for (int j = 0; j < threadNumber; ++j) {
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
        for (int j = 0; j < threadNumber; ++j) {
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
        System.out.println("Jackson NonStatic\t: " + (System.currentTimeMillis() - startTs));

        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < threadNumber; ++j) {
            cs.add(() -> {
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = GsonUtils.GSON.fromJson(s, JsonObject.class);
                    GsonUtils.GSON.toJson(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        System.out.println("GSON Static\t\t\t: " + (System.currentTimeMillis() - startTs));

        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < threadNumber; ++j) {
            cs.add(() -> {
                Gson gson = new Gson();
                for (int i = 0; i < loopTimes; ++i) {
                    Object obj = gson.fromJson(s, JsonObject.class);
                    gson.toJson(obj);
                }
                return null;
            });
        }
        executorService.invokeAll(cs, 100, TimeUnit.DAYS);
        System.out.println("GSON NonStatic\t\t: " + (System.currentTimeMillis() - startTs));


        startTs = System.currentTimeMillis();
        cs = new ArrayList<>();
        for (int j = 0; j < threadNumber; ++j) {
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
        executorService.awaitTermination(3, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception {
        int cores = Runtime.getRuntime().availableProcessors();
        int[] loopTimes = new int[]{1000, 10000, 100000};
        double[] threadRatio = new double[]{0.5, 1, 2, 4};
        for (double ratio : threadRatio) {
            for (int loopTime : loopTimes) {
                int threadNumber = (int) (ratio * cores);
                System.out.println(String.format("===== Cpu core number : %d, thread number: %d, loop times %d =====",
                        cores, threadNumber, loopTime));
                doBenchMark(threadNumber, loopTime);
                System.out.println();
            }
        }
    }
}
