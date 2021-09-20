package pub.wii.cook.springboot.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static pub.wii.cook.springboot.cache.CustomCacheManager.CACHE_NAME;

@Service
public class CacheableService {
    private static final Random random = new Random();

    @Cacheable(
            cacheManager = CACHE_NAME,
            cacheNames = CACHE_NAME,
            keyGenerator = "customKeyGenerator",
            unless = "#result == null or #result.size() == 0"
    )
    public List<Object> cache(String key) {
        List<Object> res = new ArrayList<>();
        int size = random.nextInt(10) + 1;
        for (int i = 0; i < size; ++i) {
            res.add(UUID.randomUUID().toString());
        }
        return res;
    }
}
