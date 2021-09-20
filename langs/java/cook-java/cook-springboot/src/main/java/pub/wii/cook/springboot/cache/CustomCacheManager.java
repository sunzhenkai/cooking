package pub.wii.cook.springboot.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CustomCacheManager {
    private static final int CACHE_CAP = 100;
    public static final String CACHE_NAME = "sample";

    @Bean(name = CACHE_NAME)
    CacheManager cacheManager() {
        CaffeineCacheManager cm = new CaffeineCacheManager();
        cm.setCaffeine(Caffeine.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES)
                .recordStats()
                .initialCapacity(CACHE_CAP)
                .maximumSize(CACHE_CAP));
        cm.setCacheNames(Lists.newArrayList(CACHE_NAME));
        return cm;
    }
}
