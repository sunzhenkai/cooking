package pub.wii.cook.springboot.cache;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pub.wii.cook.springboot.config.CookSpringBootConfiguration;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pub.wii.cook.springboot.cache.CustomCacheManager.CACHE_NAME;

@ContextConfiguration(classes = CookSpringBootConfiguration.class)
@SpringJUnitConfig(classes = CookSpringBootConfiguration.class)
class CacheableServiceTest {

    interface ICache {
        List<Integer> cacheWithEmpty(List<Integer> echo);

        List<Integer> cacheWithoutEmpty(List<Integer> echo);
    }

    @Configuration
    @EnableCaching
    static class Config {
        static class ICacheImpl implements ICache {

            @Cacheable(
                    cacheManager = CACHE_NAME,
                    cacheNames = CACHE_NAME,
                    keyGenerator = "customKeyGenerator"
            )
            @Override
            public List<Integer> cacheWithEmpty(List<Integer> echo) {
                return echo;
            }

            @Cacheable(
                    cacheManager = CACHE_NAME,
                    cacheNames = CACHE_NAME,
                    keyGenerator = "customKeyGenerator",
                    unless = "#result == null or #result.size() == 0"
            )
            @Override
            public List<Integer> cacheWithoutEmpty(List<Integer> echo) {
                return echo;
            }
        }

        @Bean
        ICache iCache() {
            return new ICacheImpl();
        }
    }

    @Resource(name = CACHE_NAME)
    CacheManager cacheManager;

    @Resource
    ICache iCache;

    @SuppressWarnings("ConstantConditions")
    @SneakyThrows
    @Test
    void cache() {
        List<Integer> nonEmpty = Lists.newArrayList(1, 2, 3);
        List<Integer> empty = new ArrayList<>();
        List<Integer> nil = null;
        Cache cache = cacheManager.getCache(CACHE_NAME);
        KeyGenerator kg = new CustomKeyGenerator();
        assertNotNull(cache);

        iCache.cacheWithEmpty(nonEmpty);
        iCache.cacheWithEmpty(empty);
        iCache.cacheWithEmpty(nil);
        iCache.cacheWithoutEmpty(nonEmpty);
        iCache.cacheWithoutEmpty(empty);
        iCache.cacheWithoutEmpty(nil);

        assertEquals(cache.get(genKeyWithEmpty(kg, nonEmpty)).get(), nonEmpty);
        assertEquals(cache.get(genKeyWithEmpty(kg, empty)).get(), empty);
        assertEquals(cache.get(genKeyWithEmpty(kg, nil)).get(), nil);
        assertEquals(cache.get(genKeyWithoutEmpty(kg, nonEmpty)).get(), nonEmpty);
        assertEquals(cache.get(genKeyWithoutEmpty(kg, empty)), nil);
        assertEquals(cache.get(genKeyWithoutEmpty(kg, nil)), nil);
    }

    @SneakyThrows
    Object genKeyWithEmpty(KeyGenerator kg, List<Integer> arg) {
        // 第一个参数不要用 iCache, iCache 是通过反射机制设置的对象, 有可能是一个 Proxy
        // 获取 class name 的时候可能会得到奇怪的值, 导致 key 匹配不上
        return kg.generate(new Config.ICacheImpl(),
                ICache.class.getMethod("cacheWithEmpty", List.class), arg);
    }

    @SneakyThrows
    Object genKeyWithoutEmpty(KeyGenerator kg, List<Integer> arg) {
        // 第一个参数不要用 iCache, iCache 是通过反射机制设置的对象, 有可能是一个 Proxy
        // 获取 class name 的时候可能会得到奇怪的值, 导致 key 匹配不上
        return kg.generate(new Config.ICacheImpl(),
                ICache.class.getMethod("cacheWithoutEmpty", List.class), arg);
    }
}