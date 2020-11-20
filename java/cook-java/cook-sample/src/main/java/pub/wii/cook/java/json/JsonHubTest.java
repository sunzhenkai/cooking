package pub.wii.cook.java.json;

import org.apache.commons.lang3.tuple.Pair;

public class JsonHubTest {
    public static void main(String[] args) {
        Pair<String, String> p1 = FastjsonConvertTest.getJsonString();
        Pair<String, String> p2 = JsonConvertTest.getJsonString();
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.equals(p2));
    }
}
