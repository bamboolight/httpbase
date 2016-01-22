package com.fq.httpbase.core;

import com.fq.httpbase.processer.ResponseProcessor;
import com.fq.httpbase.request.AbstractRequestMethod;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutorService;

/**
 * HttpBase项目的核心功能(负责向线程池提交请求任务, 和从缓存中读取任务值)
 *
 * @param <Value>  存入缓存的值类型
 * @param <Output> 处理完url返回值得到的类型
 * @author jifang
 * @since 16/1/22下午12:48.
 */
public class HttpCore<Value, Output> {

    private ExecutorService threadPool;

    private LoadingCache<String, Value> cache;

    public HttpCore(ExecutorService threadPool, LoadingCache<String, Value> cache) {
        this.threadPool = threadPool;
        this.cache = cache;
    }

    public synchronized Output get(String url, ResponseProcessor<Value, Output> processor) {
        return processor.handle(cache.getUnchecked(url));
    }

    public synchronized void submit(AbstractRequestMethod<Value> request) {
        request.setCache(this.cache);
        threadPool.submit(request);
    }
}
