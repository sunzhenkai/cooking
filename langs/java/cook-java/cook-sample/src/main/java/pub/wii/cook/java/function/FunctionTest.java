package pub.wii.cook.java.function;

import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionTest {

    static class FC implements Function<String, String> {
        private final Function<String, String> f;
        private final Supplier<Long> spl;
        private final Function<String[], String> mf;

        public FC(Function<String, String> f,
                  Supplier<Long> spl,
                  Function<String[], String> mf) {
            this.f = f;
            this.spl = spl;
            this.mf = mf;
        }

        @Override
        public String apply(String s) {
            return spl.get() + " " + f.apply(s);
        }

        public String rmf(String[] ss) {
            return mf.apply(ss);
        }
    }

    public static void main(String[] args) {
        FC fc = new FC((item) -> "Hello " + item,
                System::currentTimeMillis,
                (ags) -> String.join(",", ags));
        System.out.println(fc.apply(fc.rmf(new String[]{"Tom", "Kitty"})));
    }
}
