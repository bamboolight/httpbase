package com.fq.httpbase.factory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.io.Resources;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author jifang
 * @since 16/1/21下午11:07.
 */
public class CacheFactory {

    private static FactoryEntity<String> entity;

    private static final long DEFAULT_MAX_CACHE_SIZE = 1000L;
    private static final long DEFAULT_EXPIRE_DURATION = 10L;
    private static final CacheLoader<String, String> DEFAULT_CACHE_LOADER = new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            return Resources.toString(new URL(key), StandardCharsets.UTF_8);
        }
    };
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    public static LoadingCache<String, String> getDefaultCache() {

        if (entity == null) {
            synchronized (CacheFactory.class) {
                if (entity == null) {
                    entity = new FactoryEntity<>();
                }
            }
        }

        return entity.getSingletonCache(DEFAULT_CACHE_LOADER, DEFAULT_MAX_CACHE_SIZE, DEFAULT_EXPIRE_DURATION, DEFAULT_TIME_UNIT);
    }


    public static class FactoryEntity<Value> {

        /**
         * 以String(url)作为缓存的key
         * Value(默认是String)作为缓存的value
         */
        private LoadingCache<String, Value> cache;

        public LoadingCache<String, Value> getSingletonCache(CacheLoader<String, Value> loader, long size, long duration, TimeUnit unit) {
            if (cache == null) {
                synchronized (this) {
                    if (cache == null) {
                        cache = CacheBuilder.newBuilder()
                                .maximumSize(size)
                                .expireAfterAccess(duration, unit)
                                .build(loader);
                    }
                }
            }

            return cache;
        }
    }
}
