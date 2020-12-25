package pub.wii.cook.springboot.beans;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FunctionBean implements Function<String, String> {

    @Override
    public String apply(String s) {
        return "transfer(" + s + ")";
    }
}
