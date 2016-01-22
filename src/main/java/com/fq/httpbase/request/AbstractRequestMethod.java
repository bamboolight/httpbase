package com.fq.httpbase.request;

import com.google.common.cache.LoadingCache;

/**
 * 自定义后台线程处理(url)策略只需继承该抽象类
 * 需要实现run method {
 * 请求url;
 * 存入cache;
 * }
 *
 * @param <Value>存入缓存的数据类型
 * @author jifang
 * @since 16/1/22下午2:26.
 */
public abstract class AbstractRequestMethod<Value> implements Runnable {

    protected String url;
    protected LoadingCache<String, Value> cache;

    public AbstractRequestMethod(String url) {
        this.url = url;
    }

    public void setCache(LoadingCache<String, Value> cache) {
        this.cache = cache;
    }
}
