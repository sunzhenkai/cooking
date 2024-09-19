package pub.wii.cook.java.lombok;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Setter
@Getter
@ToString
@Accessors(chain = true)
class TestBean {
    private String v = "Hello";
    private String w;
    private boolean b;
    private Map<String, String> m;

    public TestBean setW(String s) {
        w = "HHH" + s;
        return this;
    }
}

public class TestLombok {
    public static void main(String[] args) throws IOException {
        TestBean bean = new TestBean();
        bean.setV("YES");
        bean = bean.setW("HELLO");
        System.out.println(bean);

        TestBean beanOther = new TestBean();
        beanOther.setV("YES");
        beanOther = bean.setW("HELLO");
        System.out.println(bean.equals(beanOther));
    }
}
