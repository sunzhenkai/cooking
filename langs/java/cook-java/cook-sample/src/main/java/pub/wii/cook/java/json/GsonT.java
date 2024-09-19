package pub.wii.cook.java.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GsonT {
    public static void main(String[] args) {
        String j = "{\"id\": \"0\"}";
        Gson gson = new Gson();
        JsonObject jo = gson.fromJson(j, JsonObject.class);
        jo.addProperty("id", "1");


        JsonArray ja = new JsonArray();
        ja.add("1");
        ja.add("2");
        ja.add("3");

        jo.add("list", ja);
        System.out.println(jo);
    }
}
