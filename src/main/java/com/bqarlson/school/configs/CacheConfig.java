package com.bqarlson.school.configs;


import com.bqarlson.school.utilities.Constants;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class CacheConfig {
    @Value("${record.cache.stats:false}")
    boolean recordCacheStats;
    @Value("${mysql.cache.mode:0}")
    private int mysqlCacheMode;
    @Value("${user.cache.max.size:10000}")
    private int userCacheMaxSize;
    @Value("${user.cache.expire.interval.minutes:60}")
    private int userCacheExpireMinutes;

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        if (mysqlCacheMode == 0) {
            cacheManager.registerCustomCache(Constants.USER_CACHE, createCacheConfig(userCacheMaxSize, userCacheMaxSize));
        }
        cacheManager.setAllowNullValues(false);
        return cacheManager;
    }

    private Cache<Object, Object> createCacheConfig(int maxSize, int expireTime) {
        Cache<Object, Object> cache;
        if (recordCacheStats) {
            cache = Caffeine.newBuilder()
                    .initialCapacity(maxSize / 10)
                    .maximumSize(maxSize)
                    .expireAfterWrite(expireTime, TimeUnit.MINUTES)
                    .recordStats()
                    .build();
        } else {
            cache = Caffeine.newBuilder()
                    .initialCapacity(maxSize / 10)
                    .maximumSize(maxSize)
                    .expireAfterWrite(expireTime, TimeUnit.MINUTES)
                    .build();
        }

        return cache;
    }


}
