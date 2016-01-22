package com.fq.httpbase.request;

import com.google.common.cache.LoadingCache;

/**
 * @param <Value> 存入缓存的数据类型
 * @author jifang
 * @since 16/1/22下午2:26.
 */
public abstract class RequestMethod<Value> implements Runnable {

    protected String url;
    protected LoadingCache<String, Value> cache;

    public RequestMethod(String url, LoadingCache<String, Value> cache) {
        this.url = url;
        this.cache = cache;
    }
}
