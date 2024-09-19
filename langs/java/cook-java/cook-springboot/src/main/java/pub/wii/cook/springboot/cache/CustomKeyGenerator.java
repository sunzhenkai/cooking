package pub.wii.cook.springboot.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class CustomKeyGenerator implements KeyGenerator {
    @SuppressWarnings("NullableProblems")
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        return o.getClass().getSimpleName() + ":" + method.getName() + ":" + Arrays.toString(objects);
    }
}
