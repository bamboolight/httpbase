package com.fq.httpbase.core;

import com.fq.httpbase.exception.HttpBaseException;
import com.fq.httpbase.factory.ThreadPoolFactory;
import com.fq.httpbase.processer.ResponseProcessor;
import com.fq.httpbase.request.RequestMethod;
import com.fq.httpbase.util.StringUtil;
import com.fq.httpbase.util.URLUtil;
import com.google.common.cache.LoadingCache;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;

/**
 * @param <Value>  存入缓存的值类型
 * @param <Output> 处理完url返回值得到的类型
 * @author jifang
 * @since 16/1/22下午12:48.
 */
public class HttpCore<Value, Output> {

    private static ExecutorService threadPool = ThreadPoolFactory.getThreadPool();
    private LoadingCache<String, Value> cache;

    public HttpCore(LoadingCache<String, Value> cache) {
        this.cache = cache;
    }

    public synchronized Output get(String url, String encodedPart, String charset, ResponseProcessor<Value, Output> processor) {
        try {
            StringBuilder sb = new StringBuilder(URLUtil.urlAdapter(url));
            if (StringUtil.isNoneBlank(encodedPart, charset)) {
                sb.append(URLEncoder.encode(encodedPart, charset));
            }
            return processor.handle(cache.getUnchecked(sb.toString()));
        } catch (UnsupportedEncodingException e) {
            throw new HttpBaseException(e);
        }
    }

    public void submit(RequestMethod<Value> request) {
        threadPool.submit(request);
    }
}
